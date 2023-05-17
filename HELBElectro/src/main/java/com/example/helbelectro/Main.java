package com.example.helbelectro;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        HELBElectroView view = HELBElectroView.getInstance(stage);
        HELBElectroController.getInstance();
        view.display();
    }
}