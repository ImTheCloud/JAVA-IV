package com.electro.helbelectro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ElectoController {
    @FXML
    private Label place;

    @FXML
    protected void onButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
        place.setText("Place number : "+buttonText);
    }
}