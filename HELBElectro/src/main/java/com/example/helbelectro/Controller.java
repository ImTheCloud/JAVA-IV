package com.example.helbelectro;

import com.example.helbelectro.Parser;
import javafx.application.Platform;
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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

import static com.example.helbelectro.Parser.componentNames;
import static com.example.helbelectro.Ticket.enregistrerVente;

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
    private Label component1, component2, component3, component4, component5, component6, component7, component8;

    public void initialize() {
        //  executor  utilise un thread pour la lecture
        // c'est un thread unique, il le faut sinon ca ne fonctionnait
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // on va donc le donner a tester ensuite pour que ça fonctionne
        // avant on faisait directement le try catch
        // nouveau Thread car sinon ça bloque l'interface quand je run
        executor.submit(() -> {
            try {
                Parser.parseSimulationFile();
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // faut mettre a jour psq les composant rentre un a un dans la list
        new Thread(() -> {
            while (true) {
                List<String> componentNames = Parser.componentNames; // recupere la liste
                // utilisation de runLater de la class Platform pour maj les noms des composant
                Platform.runLater(() -> updateComponentLabels(componentNames));
                // obliger d'attendre un certain temps pour ne pas generer d'erreurs pendant les maj
                try {
                    // Attends 100 millisecondes avant de vérifier si la liste de noms de composants a changé
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    // ici methode pour maj les composants
    private void updateComponentLabels(List<String> componentNames) {
        for (int i = 0; i < componentNames.size(); i++) {
            String componentName = componentNames.get(i);
            // pour changer le fond du label
            if(componentName.equals("C-Type-1")){
                getComponentLabel(i + 1).setStyle("-fx-background-color: #00BCD4;");
            }else if(componentName.equals("C-Type-2")){
                getComponentLabel(i + 1).setStyle("-fx-background-color: #4CAF50;");
            }else if(componentName.equals("C-Type-3")){
                getComponentLabel(i + 1).setStyle("-fx-background-color: #A9287D9A;");
            }
            // pour prendre le bon label ou faut changer le nom
            Label componentLabel = getComponentLabel(i + 1);
            // change le label avec le nom du composant C-Type-
            componentLabel.setText(componentName);
        }
        isComponentsFull();
    }

    public void isComponentsFull(){
        if (componentNames.size() == 8) {
            componentNames.clear();
            component1.setText("Empty");
            component2.setText("Empty");
            component3.setText("Empty");
            component4.setText("Empty");
            component5.setText("Empty");
            component6.setText("Empty");
            component7.setText("Empty");
            component8.setText("Empty");
            component1.setStyle("-fx-background-color: white ;");
            component2.setStyle("-fx-background-color: white;");
            component3.setStyle("-fx-background-color: white;");
            component4.setStyle("-fx-background-color: white;");
            component5.setStyle("-fx-background-color: white;");
            component6.setStyle("-fx-background-color: white;");
            component7.setStyle("-fx-background-color: white;");
            component8.setStyle("-fx-background-color: white;");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Area full !");
            alert.setHeaderText(null);
            alert.setContentText("The component area is filled, all components are moved !");
            alert.showAndWait();
        }

    }
    private Label getComponentLabel(int index) {
        switch (index) {
            case 1:
                return component1;
            case 2:
                return component2;
            case 3:
                return component3;
            case 4:
                return component4;
            case 5:
                return component5;
            case 6:
                return component6;
            case 7:
                return component7;
            case 8:
                return component8;
            default:
                throw new IllegalArgumentException("erreur");
        }
    }

}