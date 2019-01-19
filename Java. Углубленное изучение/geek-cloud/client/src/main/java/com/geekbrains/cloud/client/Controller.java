package com.geekbrains.cloud.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextField msgField;

    @FXML
    ListView<String> dataList;

    @FXML
    VBox root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] arr = {"A","B","C","D"};
        dataList.getItems().addAll(arr);

        Button btn = new Button("Hello");
        root.getChildren().add(btn);
    }

    public void sendMsg(ActionEvent actionEvent) {
        textArea.appendText(msgField.getText() + '\n');
        msgField.clear();
        msgField.requestFocus();
    }

    public void checkListViewElement(ActionEvent actionEvent) {
        System.out.println(dataList.getSelectionModel().getSelectedItem());
    }
}