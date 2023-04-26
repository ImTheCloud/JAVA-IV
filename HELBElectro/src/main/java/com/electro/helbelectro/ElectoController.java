package com.electro.helbelectro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ElectoController {
    @FXML
    private Label place;

    @FXML
    protected void onButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("Place number : " + buttonText);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> modal.close());

        VBox vbox = new VBox(label, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox);
        modal.setScene(scene);
        modal.showAndWait();
    }

}