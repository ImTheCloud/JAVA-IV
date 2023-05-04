package com.example.helbelectro;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public static final int number_lb_component =8;
    private final int lb_component_height = 42;
    private final int lb_component_with = 183;



    public void initialize() {
        initializeProductArea();
        initializeComponentArea();

        setLabelComponents();
        getChoiceOPti();
        Factory.getSortedProductListByTime();

    }

    public void getChoiceOPti() {
        cb_opti.setOnAction(event -> updateOpti());
    }

    private void updateOpti() {
        String selectedItem = cb_opti.getSelectionModel().getSelectedItem();
        if (selectedItem.equals("Time")) {
            Factory.getOptiTime();
        }
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

    public void setLabelComponents() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                Parser.getInstance().parseSimulationFile();
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        new Thread(() -> {
            int previousSize = -1;
            while (true) {
                List<Object> componentList = Factory.componentObjectList;
                int currentSize = componentList.size();
                if (currentSize < previousSize) {
                    Platform.runLater(this::clearComponentLabels);
                }
                if (currentSize != previousSize) {
                    Platform.runLater(() -> updateComponentLabels(componentList));
                }
                previousSize = currentSize;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void clearComponentLabels() {
        for (Label label : componentLabelsList) {
            label.setStyle("-fx-background-color: #FFFFFF;");
            label.setText("");
        }
    }



    // ici methode pour maj les composants
        private void updateComponentLabels(List<Object> componentList) {
            for (int i = 0; i < componentList.size(); i++) {
                Object component = componentList.get(i);
                String componentName = "";
                if (component instanceof ComponentBattery) {
                    componentName = "Batterie";
                    getComponentLabel(i + 1).setStyle("-fx-background-color: #00BCD4;");
                } else if (component instanceof ComponentSensor) {
                    componentName = "Capteur";
                    getComponentLabel(i + 1).setStyle("-fx-background-color: #4CAF50;");
                } else if (component instanceof ComponentMotor) {
                    componentName = "Moteur";
                    getComponentLabel(i + 1).setStyle("-fx-background-color: #A9287D9A;");
                }

                Label componentLabel = getComponentLabel(i + 1);
                componentLabel.setText(componentName); // + "-C-Type-" + (i+1)
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