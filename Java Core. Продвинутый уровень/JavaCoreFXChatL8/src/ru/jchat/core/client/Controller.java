package ru.jchat.core.client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Duration;

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
    ListView<String> clientsListView;

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    private static int period = 120_000;
    private static int timeout = 0;

    final String SERVER_IP = "localhost";
    final int SERVER_PORT = 8189;

    private boolean authorized;
    private String myNick;

    private ObservableList<String> clientsList;


    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
        if (authorized){
            msgPanel.setVisible(true);
            msgPanel.setManaged(true);
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            clientsListView.setVisible(true);
            clientsListView.setManaged(true);
        } else {
            msgPanel.setVisible(false);
            msgPanel.setManaged(false);
            authPanel.setVisible(true);
            authPanel.setManaged(true);
            clientsListView.setVisible(false);
            clientsListView.setManaged(false);
            myNick = "";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAuthorized(false);
        startTimer();
    }

    public void connect(){
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            clientsList = FXCollections.observableArrayList();
            clientsListView.setItems(clientsList);

            clientsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    return new ListCell<String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!empty){
                                setText(item);
                                if (item.equals(myNick)){
                                    setStyle("-fx-font-weight: bold; -fx-background-color: #F08080");
                                }
                            } else{
                                setGraphic(null);
                                setText(null);
                            }
                        }
                    };
                }
            });
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String s = null;
                        s = in.readUTF();
                        if (s.startsWith("/authok ")){
                            setAuthorized(true);
                            myNick = s.split("\\s")[1];
                            break;
                        } else {
                            textArea.appendText(s + "\n");
                        }
                    }

                    while (true) {
                        String s = in.readUTF();
                        if (s.startsWith("/")){
                            if (s.startsWith("/clientslist ")){
                                String[] data = s.split("\\s");
                                Platform.runLater(() -> {
                                    clientsList.clear();
                                    for (int i = 1; i < data.length; i++) {
                                        clientsList.addAll(data[i]);
                                    }
                                });
                            }
                        } else {
                            textArea.appendText(s + "\n");
                        }
                    }
                } catch (IOException e) {
                    showAlert(null, "Сервер перестал отвечать");
                } finally {
                    setAuthorized(false);
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
            showAlert(null, "Не удалось подключиться к серверу. Проверьте сетевое соединение");
        }
    }

    public void sendAuthMsg(){
        if (loginField.getText().isEmpty() || passField.getText().isEmpty()){
            showAlert(null, "Указаны неполные авторизационные данные");
            return;
        }

        if (socket == null || socket.isClosed()){
            connect();
        }
        // /auth login pass
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
            loginField.clear();
            passField.clear();
        } catch (IOException e) {
            showAlert(null, "Не удалось подключиться к серверу. Проверьте сетевое соединение");
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            //e.printStackTrace();
            showAlert(null, "Не удалось отправить сообщение. Проверьте сетевое подключение.");
        }
    }

    public void showAlert(String title, String msg){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(title == null){
                alert.setTitle("Возникли проблемы");
            }else{
                alert.setTitle(title);
            }
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }

    public void clientsListClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2){
            msgField.setText("/w " + clientsListView.getSelectionModel().getSelectedItem() + " ");
            msgField.requestFocus();
            msgField.selectEnd();
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
                                //System.out.println("Socket close!");
                                try {
                                    //in.close();
                                    //out.close();

                                    showAlert("Время истекло!", "Вы не авторизовались в течении " + (period /1000)
                                            + " секунд.\n Перезапустите клиет для повторной авторизации!" );
                                    Platform.runLater(() -> {authPanel.setVisible(false);
                                        authPanel.setManaged(false);});

                                    //socket.close();
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
}
