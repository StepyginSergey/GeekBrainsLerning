package ru.geekbrains.java.core.lesson4;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClient());
	    //ChatClient chat = new ChatClient();
    }
}
