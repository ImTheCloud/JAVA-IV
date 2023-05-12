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
    private static DisplayProductDetail instance;

    // Constructeur privé pour empêcher l'instanciation directe
    DisplayProductDetail() {
    }

    // Méthode statique pour obtenir l'instance unique du singleton
    public static DisplayProductDetail getInstance() {
        if (instance == null) {
            instance = new DisplayProductDetail();
        }
        return instance;
    }
    private  int sellCount = 0;
    private  int statNumberBattery = 0;
    private static int statNumberSensor = 0;
    private  int statNumberMotor = 0;
    private  int statNumberAlarm = 0;
    private  int statNumberCar = 0;
    private  int statNumberRobot = 0;
    private  int statNumberDrone = 0;

    public void onButtonProductClicked(ActionEvent event) {
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

    private void showEmptyProductModal(Stage modal) {
        Label statut = new Label("Statut : inoccupé");
        VBox vbox = new VBox(statut);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        modal.setScene(new Scene(vbox, 400, 350));
        modal.showAndWait();
    }

    private void showProductModal(Stage modal, Product product, Button bt_productFinish) {
        Label statut = new Label("Statut : occupé");
        Label type = new Label("Type de produit: " + product.getnameForScene());
        Label price = new Label("Prix : " + product.getSellingPrice() + " euros");
        Label ecoScore = new Label("Eco-Score : " + product.getEcoScore());
        Button statsButton = createStatsButton(product,modal);
        Button sellButton = createSellButton(product, bt_productFinish, modal);

        VBox vbox = new VBox(statsButton, statut, type, price, ecoScore);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        if (product instanceof ProductSensor) {
            statNumberSensor++;
            addSensorLabels(vbox);
        } else if (product instanceof ProductBattery) {
            statNumberBattery++;
            addBatteryLabel(vbox);
        } else if (product instanceof ProductMotor) {
            statNumberMotor++;
            addMotorLabel(vbox);
        } else if (product instanceof ProductDrone) {
            statNumberDrone++;
            addMotorLabel(vbox);
            addSensorLabels(vbox);
            addBatteryLabel(vbox);
        } else if (product instanceof ProductCar) {
            statNumberCar++;
            addMotorLabel(vbox);
            addBatteryLabel(vbox);
        } else if (product instanceof ProductAlarm) {
            statNumberAlarm++;
            addBatteryLabel(vbox);
            addSensorLabels(vbox);
        } else if (product instanceof ProductRobot) {
            statNumberRobot++;
            addMotorLabel(vbox);
            addSensorLabels(vbox);
        }
        vbox.getChildren().addAll( sellButton);


        modal.setScene(new Scene(vbox, 400, 350));
        modal.showAndWait();
    }

    private void addSensorLabels(VBox vbox) {
        Label rangeLabel = new Label("Range: " + ComponentSensor.getRange());
        Label colorSensorLabel = new Label("Color Sensor: " + ComponentSensor.getColorSensor());
        vbox.getChildren().addAll(rangeLabel, colorSensorLabel);
    }

    private void addBatteryLabel(VBox vbox) {
        Label loadLabel = new Label("Load: " + ComponentBattery.getLoad());
        vbox.getChildren().add(loadLabel);
    }

    private void addMotorLabel(VBox vbox) {
        Label powerLabel = new Label("Power: " + ComponentMotor.getPower());
        vbox.getChildren().add(powerLabel);
    }

    private Button createStatsButton(Product product, Stage modal) {
        Button statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color: #3f7ad9; -fx-text-fill: white;");

        statsButton.setOnAction(e -> {
            int totalSellCount = sellCount;
            int totalProductCount = statNumberSensor + statNumberBattery + statNumberMotor + statNumberDrone
                    + statNumberCar + statNumberAlarm + statNumberRobot;

            String contentText = "Nombre total de vente dans cet emplacement : " + totalSellCount + "\n";
            contentText += "Nombre total de produits : " + totalProductCount + "\n";

            if (statNumberSensor > 0) {
                contentText += "Nombre de produits Sensor : " + statNumberSensor + "\n";
            }
            if (statNumberBattery > 0) {
                contentText += "Nombre de produits Battery : " + statNumberBattery + "\n";
            }
            if (statNumberMotor > 0) {
                contentText += "Nombre de produits Motor : " + statNumberMotor + "\n";
            }
            if (statNumberDrone > 0) {
                contentText += "Nombre de produits Drone : " + statNumberDrone + "\n";
            }
            if (statNumberCar > 0) {
                contentText += "Nombre de produits Car : " + statNumberCar + "\n";
            }
            if (statNumberAlarm > 0) {
                contentText += "Nombre de produits Alarm : " + statNumberAlarm + "\n";
            }
            if (statNumberRobot > 0) {
                contentText += "Nombre de produits Robot : " + statNumberRobot + "\n";
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Statistiques de l'emplacement");
            alert.setHeaderText(null);
            alert.setContentText(contentText);

            alert.setOnCloseRequest(event -> {
                if (product instanceof ProductSensor) {
                    statNumberSensor--;
                } else if (product instanceof ProductBattery) {
                    statNumberBattery--;
                } else if (product instanceof ProductMotor) {
                    statNumberMotor--;
                } else if (product instanceof ProductDrone) {
                    statNumberDrone--;
                } else if (product instanceof ProductCar) {
                    statNumberCar--;
                } else if (product instanceof ProductAlarm) {
                    statNumberAlarm--;
                } else if (product instanceof ProductRobot) {
                    statNumberRobot--;
                }
            });
            alert.showAndWait();
            modal.close();
        });

        return statsButton;
    }



    private Button createSellButton(Product product, Button bt_productFinish, Stage modal) {
        Button sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #0b6517; -fx-text-fill: white;");

        sellButton.setOnAction(e -> {
            Ticket.getInstance().registerSale(product, product.getSellingPrice(), product.getEcoScore());
            HELBElectroController.productObjectList.remove(product);

            sellCount++;
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
