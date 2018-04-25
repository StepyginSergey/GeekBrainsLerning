package ru.geekbrains.java2.lesson1.l1.lesson1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("CLICKED");
    }
}
