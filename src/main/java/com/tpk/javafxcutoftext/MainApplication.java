package com.tpk.javafxcutoftext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setTitle("Cut OF Text");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setMinHeight(800);
        stage.setMinWidth(1600);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}