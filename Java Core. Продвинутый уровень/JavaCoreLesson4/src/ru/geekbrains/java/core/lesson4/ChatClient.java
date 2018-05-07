package ru.geekbrains.java.core.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 Создать окно для клиентской части чата: большое текстовое поле для отображения переписки
 в центре окна. Однострочное текстовое поле для ввода сообщений и кнопка для отсылки
 сообщений на нижней панели. Сообщение должно отсылаться либо по нажатию кнопки на
 форме, либо по нажатию кнопки Enter. При «отсылке» сообщение перекидывается из нижнего
 поля в центральное.
 */

public class ChatClient extends JFrame {
    JTextField jtf;
    JTextArea jta;


    public ChatClient(){
        setTitle("Client chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setBounds(400, 400, 600, 500);
        setLayout(new BorderLayout());


        JButton button = new JButton("Отправить");
        button.addActionListener(e -> sendMessage());

        jtf = new JTextField();
        jtf.setColumns(40);
        jtf.addActionListener(e -> sendMessage());


        jta = new JTextArea();
        jta.setEditable(false);
        jta.setFont(new Font("Areal", Font.BOLD, 15 ));
        JScrollPane scroll = new JScrollPane(jta);

        //JList<String> userList = new JList<>(new String[]{"User 1", "User 2", "User 3", "User 4"});
        JList<String> userList = new JList<>(new DefaultListModel<>());
        userList.setPreferredSize(new Dimension(95, 1));
        ((DefaultListModel)userList.getModel()).addElement("User 1");
        ((DefaultListModel)userList.getModel()).addElement("User 2");
        ((DefaultListModel)userList.getModel()).addElement("User 3");
        ((DefaultListModel)userList.getModel()).addElement("User 4");


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(jtf, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);

        add(userList, BorderLayout.EAST);
        add(scroll,BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }


    private void sendMessage(){
        jta.append(jtf.getText() + "\n");
        jtf.setText("");
    }
}
