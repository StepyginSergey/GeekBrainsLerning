package ru.geekbrains.homework.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by stepygin on 15.05.2018.
 */
public class JServer {

    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;

        try {
            serv = new ServerSocket(8189);
            System.out.println("Serevr running. Wait connect...");
            sock = serv.accept();
            System.out.println("Client connected!");

            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());

            Scanner sc = new Scanner(sock.getInputStream());
            Scanner scanConsole = new Scanner(System.in);

            Thread threadConsole = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            if(scanConsole.hasNextLine()){
                                String str = scanConsole.nextLine();
                                if(str.equals("/end")) {
                                    break;
                                }
                                System.out.println("server: " + str);
                                    out.writeUTF("From server:"  + str);
                                    out.flush();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        scanConsole.close();
                    }
                }
            });
            threadConsole.start();

            Thread threadClient = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            String str = null;
                                str = in.readUTF();
                            if (str.equals("/end")) {
                                break;
                            }
                            System.out.println("From client: " + str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        sc.close();
                    }
                }
            });
            threadClient.start();

            try {
                threadConsole.join();
                threadClient.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e){
            System.out.println("Error initialization server");
        }finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
