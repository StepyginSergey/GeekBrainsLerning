package ru.geekbrains.homework.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class JServerEcho {
    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;

        try {
            serv = new ServerSocket(8189);
            System.out.println("Serevr running. Wait connect...");
            sock = serv.accept();
            System.out.println("Client connected!");
            Scanner sc = new Scanner(sock.getInputStream());
            PrintWriter pw = new PrintWriter(sock.getOutputStream());

            while(true){
                String str = sc.nextLine();
                if(str.equals("/end")){
                    break;
                }

                pw.println("Echo:"  + str);
                pw.flush();
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
