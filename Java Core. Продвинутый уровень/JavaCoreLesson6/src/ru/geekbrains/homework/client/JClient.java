package ru.geekbrains.homework.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by stepygin on 15.05.2018.
 */
public class JClient {

    private static Socket socket;
    private static Scanner in;

    private static DataInputStream inS;
    private static DataOutputStream outS;

    final static String SERVER_IP = "localhost";
    final static int SERVER_PORT = 8189;

    public static void main(String[] args) {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new Scanner(socket.getInputStream());

            inS = new DataInputStream(socket.getInputStream());
            outS = new DataOutputStream(socket.getOutputStream());

            Scanner console = new Scanner(System.in);

            Thread fromConsole = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if(console.hasNextLine()){
                                String str = console.nextLine();
                                if (str.equals("/end")) {
                                    break;
                                }
                                System.out.println("client: " + str);
                                outS.writeUTF(str);
                                outS.flush();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        console.close();
                    }
                }
            });
            fromConsole.start();

            Thread fromSever = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = null;
                            str = inS.readUTF();
                            System.out.println(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            fromSever.start();

            try {
                fromConsole.join();
                fromSever.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
