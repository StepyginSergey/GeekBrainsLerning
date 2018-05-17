package ru.geekbrains.homework.learn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    ServerSocket serverSocket;
    static ArrayList<Socket> clientList = new ArrayList<>();
    final int PORT;
    Thread thread;
    DataInputStream in;
    DataOutputStream out;

    public ServerMain(int Port){
        this.PORT = Port;
        startServer();
    }

    public ServerMain(){
        this.PORT = 8189;
        startServer();
    }

    synchronized private void sendAllMsg(String msg){
        for (Socket socket : clientList){
            try {
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    synchronized private void delUser(Socket socket){
        clientList.remove(socket);
    }

    public void startServer(){
        try {
            serverSocket = new ServerSocket(this.PORT);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            clientList.add(socket);
            in = new DataInputStream(socket.getInputStream());
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!thread.isInterrupted()){
                            String msg = in.readUTF();
                            System.out.println(msg);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    } finally {
                        delUser(socket);
                    }
                }
            });
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}