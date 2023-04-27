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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ElectoController {
    @FXML
    private Label place;

    @FXML
    protected void onButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        int rowIndex = GridPane.getRowIndex(button);
        int columnIndex = GridPane.getColumnIndex(button);
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("Emplacements (" + rowIndex + ", " + columnIndex + ")");

        Button statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color: #00BCD4; -fx-text-fill: white;");
        statsButton.setOnAction(e -> System.out.println("Affichage des statistiques de l'emplacement " + button.getText()));

        Button sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        sellButton.setOnAction(e -> {
            enregistrerVente(button.getText());
            System.out.println("Vente du produit de l'emplacement " + button.getText());
        });


        Button closeButton = new Button("Close");
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

    private void enregistrerVente(String emplacement) {
        try {
            // Emplacement du fichier dans le dossier ticket
            File fileInTicket = new File("ticket/ventes.txt");
            FileWriter writer = new FileWriter(fileInTicket, true);
            writer.write("Vente du produit de l'emplacement " + emplacement + "\n");
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }






}