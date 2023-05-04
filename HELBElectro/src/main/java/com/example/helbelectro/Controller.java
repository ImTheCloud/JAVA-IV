package com.example.helbelectro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

//    private Controller() {
//        // empecher la creation d'instance
//    }
    @FXML
    private Button bt_productFInish,sellButton,statsButton;
    @FXML
    private Label progressComponent;
    @FXML
    private ComboBox<String> cb_opti;
    @FXML
    private GridPane areaProduct;
    @FXML
    private VBox areaComponent;
    private List<Label> componentLabelsList;
    private  final int size_row = 4;
    private  final int size_col = 3;
    private final int bt_product_height = 73;
    private final int bt_product_with = 138;
    private final int number_lb_component =8;
    private final int lb_component_height = 42;
    private final int lb_component_with = 183;



    public void initialize() {
        initializeProductArea();
        initializeComponentArea();

        setLabelComponents();
        getChoiceOPti();
        Factory.getSortedProductListByTime();

    }

    public void getChoiceOPti(){
        cb_opti.setOnAction(event -> {
            String selectedItem = cb_opti.getSelectionModel().getSelectedItem();
            if(selectedItem.equals("Time")){
                Factory.getOptiTime();
            }
        });
    }

    public void initializeComponentArea() {
        componentLabelsList = new ArrayList<>();
        for (int i = 0; i < number_lb_component; i++) {
            Label label = new Label();
            label.setPrefSize(lb_component_with, lb_component_height);
            label.setId("component" + i);
            label.setAlignment(Pos.CENTER);
            componentLabelsList.add(label);
            label.setStyle("-fx-background-color: white;");
            areaComponent.getChildren().add(label);
        }
    }


    public void initializeProductArea() {
        for (int i = 0; i < size_col; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS); // agrandir
            areaProduct.getColumnConstraints().add(column);
        }
        for (int i = 0; i < size_row; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            areaProduct.getRowConstraints().add(row);
        }
        for (int i = 0; i < size_row; i++) {
            for (int j = 0; j < size_col; j++) {
                Button button = new Button();
                button.setPrefSize(bt_product_with, bt_product_height);
                button.setStyle("-fx-background-color: white;");
                button.setOnAction(this::onComponentClicked);
                if (i == size_row-1 && j == size_col-1) {
                    // derniere case de la grid pas de bouton
                    // comme dans l'interface du prof
                    continue;
                }
                areaProduct.add(button, j, i);
            }
        }
    }

    public void setLabelComponents(){
        //  executor  utilise un thread pour la lecture
        // c'est un thread unique, il le faut sinon ca ne fonctionnait
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // on va donc le donner a tester ensuite pour que ça fonctionne
        // avant on faisait directement le try catch
        // nouveau Thread car sinon ça bloque l'interface quand je run
        executor.submit(() -> {
            try {
                Parser.getInstance().parseSimulationFile(); // utilisation singleton ( pas de new )
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // faut mettre a jour psq les composant rentre un a un dans la list
        new Thread(() -> {
            while (true) {
                List<String> componentNames = Factory.componentNames; // recupere la liste
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
        if (componentNames.size() <= number_lb_component) {  // modifier les label que si il sont plus petit que le nbr de label
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
        }else {
            progressComponent.setText("Component area is full !");
        }
    }

    private Label getComponentLabel(int index) {
        if (index < 1 || index > componentLabelsList.size()) {
        }
        return componentLabelsList.get(index - 1);
    }


    

    @FXML
    protected void onComponentClicked(ActionEvent event) {
        // Pour savoir quel bouton a été cliqué
        bt_productFInish = (Button) event.getSource();
        // Obtient l'indice de la ligne et de la colonne du bouton dans la grille
        int rowIndex = GridPane.getRowIndex(bt_productFInish);
        int columnIndex = GridPane.getColumnIndex(bt_productFInish);

        // Crée une nouvelle fenêtre
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("Emplacements (" + rowIndex + ", " + columnIndex + ")");

        statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color:  #3f7ad9; -fx-text-fill: white;");

        sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #0b6517; -fx-text-fill: white;");
        sellButton.setOnAction(e -> {

            // singleton
            Ticket.getInstance().registerSale(bt_productFInish.getText()); // Appelle la méthode de la classe Ticket

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


}