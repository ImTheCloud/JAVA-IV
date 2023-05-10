package com.example.helbelectro;

import javafx.event.ActionEvent;
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

public class DisplayProductDetail {

    public static void onComponentClicked(ActionEvent event) {
        Button bt_productFinish = (Button) event.getSource();
        int rowIndex = GridPane.getRowIndex(bt_productFinish) - 1;
        int columnIndex = GridPane.getColumnIndex(bt_productFinish) - 1;
        String emplacements = "Emplacements (" + rowIndex + ", " + columnIndex + ")";
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setTitle(emplacements);
        Product product = (Product) bt_productFinish.getUserData();

        if (product == null) {
            showEmptyProductModal(modal);
        } else {
            showProductModal(modal, product, bt_productFinish);
        }
    }

    private static void showEmptyProductModal(Stage modal) {
        Label statut = new Label("Statut : inoccupé");
        VBox vbox = new VBox(statut);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        modal.setScene(new Scene(vbox, 400, 350));
        modal.showAndWait();
    }

    private static void showProductModal(Stage modal, Product product, Button bt_productFinish) {
        Label statut = new Label("Statut : occupé");
        Label type = new Label("Type de produit: " + product.getnameForScene());
        Label price = new Label("Prix : " + product.getSellingPrice() + " euros");
        Label ecoScore = new Label("Eco-Score : " + product.getEcoScore());
        Button statsButton = createStatsButton();
        Button sellButton = createSellButton(product, bt_productFinish, modal);

        VBox vbox = new VBox(statsButton, statut, type, price, ecoScore);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        if (product instanceof ProductSensor) {
            addSensorLabels(vbox);
        } else if (product instanceof ProductBattery) {
            addBatteryLabel(vbox);
        } else if (product instanceof ProductMotor) {
            addMotorLabel(vbox);
        } else if (product instanceof ProductDrone) {
            addMotorLabel(vbox);
            addSensorLabels(vbox);
            addBatteryLabel(vbox);
        } else if (product instanceof ProductCar) {
            addMotorLabel(vbox);
            addBatteryLabel(vbox);
        } else if (product instanceof ProductAlarm) {
            addBatteryLabel(vbox);
            addSensorLabels(vbox);
        } else if (product instanceof ProductRobot) {
            addMotorLabel(vbox);
            addSensorLabels(vbox);
        }
        vbox.getChildren().addAll( sellButton);


        modal.setScene(new Scene(vbox, 400, 350));
        modal.showAndWait();
    }

    private static void addSensorLabels(VBox vbox) {
        Label rangeLabel = new Label("Range: " + ComponentSensor.getRange());
        Label colorSensorLabel = new Label("Color Sensor: " + ComponentSensor.getColorSensor());
        vbox.getChildren().addAll(rangeLabel, colorSensorLabel);
    }

    private static void addBatteryLabel(VBox vbox) {
        Label loadLabel = new Label("Load: " + ComponentBattery.getLoad());
        vbox.getChildren().add(loadLabel);
    }

    private static void addMotorLabel(VBox vbox) {
        Label powerLabel = new Label("Power: " + ComponentMotor.getPower());
        vbox.getChildren().add(powerLabel);
    }



    private static Button createStatsButton() {
        Button statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color:  #3f7ad9; -fx-text-fill: white;");
        statsButton.setOnAction(e -> {

        });
        return statsButton;
    }

    private static Button createSellButton(Product product, Button bt_productFinish, Stage modal) {
        Button sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #0b6517; -fx-text-fill: white;");

        sellButton.setOnAction(e -> {
            Ticket.registerSale(product, product.getSellingPrice(),
                    product.getEcoScore());
            HELBElectroController.productObjectList.remove(product);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vente enregistrée");
            alert.setHeaderText(null);
            alert.setContentText("Le produit a été vendu !");
            alert.showAndWait();
            modal.close();


            bt_productFinish.setStyle("-fx-background-color: white;");
            bt_productFinish.setText("");
        });

        return sellButton;
    }
}
