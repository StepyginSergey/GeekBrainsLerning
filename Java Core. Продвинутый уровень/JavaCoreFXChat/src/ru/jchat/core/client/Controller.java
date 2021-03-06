package ru.jchat.core.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    TextArea textArea;
    @FXML
    TextField msgField;
    @FXML
    HBox authPanel;
    @FXML
    HBox msgPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passField;
    @FXML
    Label infoLabel;

    private static Socket socket;
    private  DataOutputStream out;
    private  DataInputStream in;

    private static int period = 30_000;
    private static int timeout = 0;

    final String SERVER_IP = "localhost";
    final int SERVER_PORT = 8189;

    private static boolean authorized;

    public void setAuthorized(boolean authorized, String nick) {
        this.authorized = authorized;
        if (authorized){
            msgPanel.setVisible(true);
            msgPanel.setManaged(true);
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            Platform.runLater(() -> { infoLabel.setText("Вы авторизированны, как " + nick + "!"); });
        } else {
            msgPanel.setVisible(false);
            msgPanel.setManaged(false);
            authPanel.setVisible(true);
            authPanel.setManaged(true);
        }
    }

    private void startTimer(){

        Timer timer = new Timer();

        timer.schedule(
                new TimerTask() {

                    @Override
                    public void run() {
                        if(timeout == period){
                            if(!authorized){
                                System.out.println("Socket close!");
                                try {
                                    in.close();
                                    out.close();
                                    Platform.runLater(() -> {textArea.appendText( "Вы не авторизовались. соединение сброшено." + "\n");});

                                    socket.close();
                                    this.cancel();
                                    timer.cancel();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            }
                        }else {
                            timeout = period;
                        }

                        if(authorized){
                            System.out.println("Timer canceled!");
                            this.cancel();
                            timer.cancel();
                        }
                    }
                }, 0, period);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false, null);

            startTimer();

            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String s = null;
                        s = in.readUTF();
                        if (s.startsWith("/authok")){
                            String[] data = s.split("\\s");
                            setAuthorized(true, data[1]);
                            break;
                        } else {
                            textArea.appendText(s + "\n");
                        }
                    }

                    while (true) {
                        String s = null;
                        s = in.readUTF();
                        textArea.appendText(s + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    setAuthorized(false, null);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAuthMsg(){
        // /auth login pass
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
            loginField.clear();
            passField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAlert(String msg){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Возникли проблемы");
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }
}
