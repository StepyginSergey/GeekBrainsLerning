package com.flamexander.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        System.out.println(controller);
        // Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("JavaFX CookBook");
        Scene scene = new Scene(root, 800, 800);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
