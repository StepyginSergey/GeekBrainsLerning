package ru.geekbrains.javacore.home;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stepygin on 04.05.2018.
 *
 Создать окно для клиентской части чата: большое текстовое поле для отображения переписки
 в центре окна. Однострочное текстовое поле для ввода сообщений и кнопка для отсылки
 сообщений на нижней панели. Сообщение должно отсылаться либо по нажатию кнопки на
 форме, либо по нажатию кнопки Enter. При «отсылке» сообщение перекидывается из нижнего
 поля в центральное.
 */
public class ChatClient extends JFrame {

    public ChatClient(){
        setTitle("Client chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setBounds(300, 300, 600, 500);
        setLayout(new BorderLayout());
        JButton button = new JButton("Отправить");
        JTextField jtf = new JTextField();
        JTextArea jta = new JTextArea();
        JScrollBar scrollBar = new JScrollBar();

        add(jtf, BorderLayout.SOUTH);


        setVisible(true);
    }

    public static void main(String[] args) {

        ChatClient chat = new ChatClient();
    }
}
