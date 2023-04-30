package com.example.helbelectro;

import com.example.helbelectro.Component.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.helbelectro.Factory.Ticket.enregistrerVente;

public class Controller {
    private Button button,sellButton,statsButton;

    @FXML
    protected void onComponentClicked(ActionEvent event) {
        // Pour savoir quel bouton a été cliqué
        button = (Button) event.getSource();
        // Obtient l'indice de la ligne et de la colonne du bouton dans la grille
        int rowIndex = GridPane.getRowIndex(button);
        int columnIndex = GridPane.getColumnIndex(button);

        // Crée une nouvelle fenêtre
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("Emplacements (" + rowIndex + ", " + columnIndex + ")");

        statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color:  #3f7ad9; -fx-text-fill: white;");

        sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #0b6517; -fx-text-fill: white;");
        sellButton.setOnAction(e -> {
            enregistrerVente(button.getText()); // Appelle la méthode de la classe Ticket

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vente enregistrée");
            alert.setHeaderText(null);
            alert.setContentText("Le produit a été vendu !");
            alert.showAndWait();
            modal.close();
        });

        // UNE VBox pour ajouter les boutons
        VBox vbox = new VBox(label, statsButton, sellButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));

        modal.setScene(new Scene(vbox, 400, 250));
        modal.showAndWait();
    }


    @FXML
    public void initialize() {
        // Creation d'un noubeau Thread car sinon ca bloque l'interface
        ExecutorService executor = Executors.newSingleThreadExecutor();
       // on va donc le donner a tester ensuite pour que ca fonctionner
        // avant on faisait directement le try catch
        executor.submit(() -> {
            try {
                Parser.parseSimulationFile();
            } catch (FileNotFoundException | InterruptedException e) { //on test les 2 exeption pour pas avoir de bug
                System.err.println("Exception" + e.getMessage());
            }
        });
        // quand le fichier se finit alors executorService doit se terminer
        executor.shutdown();
    }


}