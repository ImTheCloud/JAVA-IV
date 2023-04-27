package com.example.helbelectro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ElectoController {
    @FXML
    private Label place;
    private Button button,sellButton,closeButton;

    @FXML
    protected void onButtonClicked(ActionEvent event) {
        button = (Button) event.getSource();
        int rowIndex = GridPane.getRowIndex(button);
        int columnIndex = GridPane.getColumnIndex(button);
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("Emplacements (" + rowIndex + ", " + columnIndex + ")");

        Button statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color: #00BCD4; -fx-text-fill: white;");

        sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        sellButton.setOnAction(e -> {
            Ticket.enregistrerVente(button.getText()); //prend la methode de Ticket
            modal.close();
        });

        closeButton = new Button("Close");
        closeButton.setOnAction(e -> modal.close());

        VBox vbox = new VBox(label, statsButton, sellButton, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));

        GridPane.setConstraints(label, 0, 1, GridPane.REMAINING, 1);
        GridPane.setConstraints(statsButton, 0, 2, GridPane.REMAINING, 1);
        GridPane.setConstraints(sellButton, 0, 3, GridPane.REMAINING, 1);

        modal.setScene(new Scene(vbox));
        modal.showAndWait();
    }












}