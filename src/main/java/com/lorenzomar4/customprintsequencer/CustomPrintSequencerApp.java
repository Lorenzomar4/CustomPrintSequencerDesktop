package com.lorenzomar4.customprintsequencer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class CustomPrintSequencerApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomPrintSequencerApp.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CustomPrintSequencer");

        stage.setMaximized(false);
        stage.setMaxWidth(700d);
        stage.setMinHeight(530d);
        stage.setMinWidth(330d);
        stage.setScene(scene);


        stage.setFullScreenExitHint("");




        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}