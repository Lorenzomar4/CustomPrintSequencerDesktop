package com.lorenzomar4.customprintsequencer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                new File("src/main/java/com/lorenzomar4/customprintsequencer/view/main.fxml").toURI().toURL()
        );
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CustomPrintSequencer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}