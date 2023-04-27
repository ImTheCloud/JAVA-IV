package com.example.helbelectro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HELBElectro extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HELBElectro.class.getResource("helb-electro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 470);
        stage.setTitle("HELBElectro");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}