package com.example.helbelectro;

import javafx.application.Application;
import javafx.stage.Stage;


public class HELBElectroMain extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        HELBElectroView view = new HELBElectroView(stage);
        view.afficher();
    }

}