package com.flamexander.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene2Controller {
    @FXML
    VBox mainVBox;



    public void switchSceneTo1(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Scene1.fxml"));
            Scene scene = new Scene(root);
            ((Stage)mainVBox.getScene().getWindow()).setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
