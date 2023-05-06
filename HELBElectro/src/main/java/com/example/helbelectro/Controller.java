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
    private Timeline timeline = new Timeline();
    private Timeline timelineBt = new Timeline();




    public void initialize() {
        initializeProductArea();
        initializeComponentArea();

        setLabelComponents();
        getChoiceOpti();


        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            setButtonProduct();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    public void setButtonProduct(){
        List<Product> productList = new ArrayList<>();
        for (Object obj : Factory.productObjectList) {
            if (obj instanceof Product) {
                productList.add((Product) obj);
            }
        }

        int index = 0;
        for (int i = 0; i < size_row; i++) {
            for (int j = 0; j < size_col; j++) {
                if (index >= productList.size()) {
                    break;
                }
                Product product = productList.get(index);
                Button button = new Button(product.getName());
                button.setPrefSize(bt_product_with, bt_product_height);
                button.setStyle("-fx-background-color: " + product.getColor() + ";");
                button.setOnAction(this::onComponentClicked);
                areaProduct.add(button, j, i);
                index++;
            }
        }
    }


    public void getChoiceOpti() {
        cb_opti.setOnAction(event -> {
            String selectedItem = cb_opti.getSelectionModel().getSelectedItem();
            if (selectedItem.equals("Time")) {
                Factory.getSortedProductListByTime();
                timeline.stop();
                timeline.getKeyFrames().clear();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
                    Factory.createProduct();
                }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            } else if (selectedItem.equals("Cost")) {
                Factory.getSortedProductListByPrice();
                timeline.stop();
                timeline.getKeyFrames().clear();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
                    Factory.createProduct();
                }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            } else if (selectedItem.equals("Score")) {
                Factory.getSortedProductListByScore();
                timeline.stop();
                timeline.getKeyFrames().clear();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
                    Factory.createProduct();
                }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            } else if (selectedItem.equals("Diverse")) {
                //Factory.getSortedProductListByScore();
                timeline.stop();
                timeline.getKeyFrames().clear();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
                    //Factory.createProduct();
                }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
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

    public void setLabelComponents() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {
                Parser.getInstance().parseSimulationFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<Object> componentList = Factory.componentObjectList;
            if (componentList.size() != componentLabelsList.size()) {
                clearComponentLabels();
            }
            updateComponentLabels(componentList);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void clearComponentLabels() {
        for (Label label : componentLabelsList) {
            label.setStyle("-fx-background-color: #FFFFFF;");
            label.setText("");
        }
    }

    // ici methode pour maj les composants
    private void updateComponentLabels(List<Object> componentList) {
        int index = 1;
        for (Object component : componentList) {
            String componentName = "";
            Object componentColor = null;
            if (component instanceof ComponentBattery) {
                componentName = ((ComponentBattery) component).getName();
                componentColor = ((ComponentBattery) component).getColor();
            } else if (component instanceof ComponentSensor) {
                componentName = ((ComponentSensor) component).getName();
                componentColor = ((ComponentSensor) component).getColor();
            } else if (component instanceof ComponentMotor) {
                componentName = ((ComponentMotor) component).getName();
                componentColor = ((ComponentMotor) component).getColor();
            }
            Label componentLabel = getComponentLabel(index);
            componentLabel.setText(componentName);
            componentLabel.setStyle("-fx-background-color: "+componentColor);
            index++;
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