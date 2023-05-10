package com.example.helbelectro;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HELBElectroView {
    private final Stage stage;
    private final HBox screen;
    private Label lb_numberCol;
    private Label lb_numberRow;
    private final List<Label> listeLabelRow= new ArrayList<>();
    private final List<Label> listeLabelCol= new ArrayList<>();
    private Button bt_letter_number;
    private final VBox areaComponent = new VBox();
    private final GridPane areaProduct = new GridPane();
    private final Timeline timelineChoiceOpti = new Timeline();
    private final ComboBox<String> optiComboBox = new ComboBox<>();
    private final int size_colGrid = 3;
    private final int size_rowGrid = 4;
    private final int numberButton = (size_colGrid*size_rowGrid)-1;
    private List<Label> componentLabelsList;
    private List<Button> productButtonList;
    public static final int number_lb_component =8;
    private final int widthScene = 776;
    private final int heightScene = 538;

    public HELBElectroView(Stage stage) {
        this.stage = stage;
        this.screen = createScreen();
    }

    public void afficher() {
        stage.setTitle("HELBElectro");
        VBox root = new VBox(screen);
        root.setAlignment(Pos.TOP_LEFT);
        stage.setScene(new Scene(root, widthScene, heightScene));
        stage.show();
    }

    private HBox createScreen() {
        HBox screen = new HBox();
        screen.setAlignment(Pos.CENTER);
        screen.setPrefSize(widthScene, heightScene);
        screen.setSpacing(10.0);
        screen.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "#282F76", "white", 4));
        screen.setPadding(new Insets(20, 20, 20, 20));
        GridPane grid = initGridAraProduct();

        bt_letter_number = new Button("Letter");
        bt_letter_number.setPrefWidth(150);
        bt_letter_number.setOnAction(this::changeNumberLetter);
        bt_letter_number.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "white", "white", 4));
        VBox vboxComponent = initVBoxAreaComponent();

        VBox vboxGrid = new VBox();
        vboxGrid.getChildren().addAll(bt_letter_number,grid);
        screen.getChildren().addAll(vboxGrid, vboxComponent);
        return screen;
    }

    private void changeNumberLetter(ActionEvent actionEvent) {
        areaProduct.getChildren().removeAll(listeLabelCol);
        areaProduct.getChildren().removeAll(listeLabelRow);
        if(bt_letter_number.getText().equals("Letter")){
            for (int j = 0; j < size_colGrid; j++) {
                lb_numberCol = new Label(String.valueOf((char) ('A' + j)));
                lb_numberCol.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
                areaProduct.add(lb_numberCol, j+1, 0);
                listeLabelCol.add(lb_numberCol);
            }
            for (int i = 0; i < size_rowGrid; i++) {
                lb_numberRow = new Label(String.valueOf((char) ('A' + i)));
                lb_numberRow.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
                areaProduct.add(lb_numberRow, 0, i+1);
                listeLabelRow.add(lb_numberRow);
            }
            bt_letter_number.setText("Number");
        }else{
            bt_letter_number.setText("Letter");
            // Ajout des numéros de colonne
            inializeGridWithNumber();
        }
    }


    private GridPane initGridAraProduct() {
        areaProduct.setHgap(10);
        areaProduct.setVgap(10);
        areaProduct.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-background-color: %s;", "white", "#626786"));
        areaProduct.setPadding(new Insets(20, 20, 20, 20));
        initializeProductArea();
        return areaProduct;
    }

    public void initializeProductArea() {
        for (int i = 0; i < size_colGrid; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS); // agrandir
            areaProduct.getColumnConstraints().add(column);
        }
        for (int i = 0; i < size_rowGrid; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            areaProduct.getRowConstraints().add(row);
        }

        inializeGridWithNumber();

        for (int i = 0; i < size_rowGrid; i++) {
            for (int j = 0; j < size_colGrid; j++) {
                Button button = new Button();
                int btProductWith = 138;
                int btProductHeight = 73;
                button.setPrefSize(btProductWith, btProductHeight);
                button.setStyle("-fx-background-color: white;");
                button.setOnAction(this::onComponentClicked);
                if (i == size_rowGrid -1 && j == size_colGrid -1) {
                    // derniere case de la grid pas de bouton
                    // comme dans l'interface du prof
                    continue;
                }
                areaProduct.add(button, j+1, i+1);
            }
        }
    }

    public void inializeGridWithNumber(){
        // Ajout des numéros de colonne
        for (int j = 0; j < size_colGrid; j++) {
            lb_numberCol = new Label(String.valueOf(j));
            lb_numberCol.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
            areaProduct.add(lb_numberCol, j+1, 0);
            listeLabelCol.add(lb_numberCol);
        }

        // Ajout des numéros de ligne
        for (int i = 0; i < size_rowGrid; i++) {
            lb_numberRow = new Label(String.valueOf(i));
            lb_numberRow.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
            areaProduct.add(lb_numberRow, 0, i+1);
            listeLabelRow.add(lb_numberRow);
        }
    }
    public void setButtonProduct() {
        int index = 0;
        int compteur = 0;
        productButtonList = new ArrayList<>(); // Création de la liste de boutons

        for (Node node : areaProduct.getChildren()) {
            if (node instanceof Button setButton) {
                if (index >= HELBElectroController.productObjectList.size()) {
                    break;
                }

                Product product = (Product) HELBElectroController.productObjectList.get(index);
                setButton.setUserData(product);
                setButton.setText(product.getnameForP());
                setButton.setStyle("-fx-background-color: " + product.getColor() + ";");

                productButtonList.add(setButton); // Ajout du bouton à la liste

                index++;
                compteur++;
                if (compteur == numberButton) {
                    inializeAlertForAreaProductFull();
                    break;
                }
            }
        }
    }


    private void inializeAlertForAreaProductFull() {
            System.out.println("stop production");
            stopTimeline();
        // runlater psq ya le thread java fx en cours
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Entrepôt des produits");
                alert.setHeaderText(null);
                alert.setContentText("L'entrepôt est complet, veuillez le vider !");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    HELBElectroController.productObjectList.clear();
                    clearProductLabels();
                    System.out.println("Entrepôt vidé !");
                }
            });
    }

    private void clearProductLabels() {
        for (Button button : productButtonList) {
            button.setStyle("-fx-background-color: #FFFFFF;");
            button.setText("");
        }
    }

    private VBox initVBoxAreaComponent() {
        areaComponent.setSpacing(10);
        areaComponent.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", "white", "#626786"));
        HBox optiBox = createOptiBox();
        areaComponent.getChildren().addAll(optiBox, new VBox());
        initializeComponentArea();
        return areaComponent;
    }

    public void initializeComponentArea() {
        componentLabelsList = new ArrayList<>();
        for (int i = 0; i < number_lb_component; i++) {
            Label label = new Label();
            label.setPrefSize(183, 42);
            label.setId("component" + i);
            label.setAlignment(Pos.CENTER);
            componentLabelsList.add(label);
            label.setStyle("-fx-background-color: white;");
            areaComponent.getChildren().add(label);
        }
        setLabelComponents();
    }

    public void setLabelComponents() {
        Timeline timelineComponent = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            try {
                Parser.parseSimulationFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<Object> componentList = HELBElectroController.componentObjectList;
            if (componentList.size() != componentLabelsList.size()) {
                clearComponentLabels();
            }
            updateComponentLabels(componentList);
        }));
        timelineComponent.setCycleCount(Animation.INDEFINITE);
        timelineComponent.play();
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
            if (component instanceof Component) {
                Component currentComponent = (Component) component;
                Label componentLabel = getComponentLabel(index);
                componentLabel.setText(currentComponent.getName());
                componentLabel.setStyle("-fx-background-color: " + currentComponent.getColor());
                index++;
            }
        }
    }


    private Label getComponentLabel(int index) {
        return componentLabelsList.get(index - 1);
    }

    private HBox createOptiBox() {
        HBox optiBox = new HBox();
        optiBox.setStyle(String.format("-fx-border-color: %s; -fx-font-weight: bold; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", "white", "#626786"));
        optiBox.setSpacing(10);
        Label optiLabel = new Label("Opti : ");
        optiLabel.setStyle("-fx-font-weight: bold;  -fx-text-fill: white;");
        optiComboBox.setValue("Choice");
        ObservableList<String> optiList = FXCollections.observableArrayList("Time", "Cost", "Score", "Diverse");
        optiComboBox.setItems(optiList);

        optiBox.getChildren().addAll(optiLabel, optiComboBox);
        getChoiceOpti();
        return optiBox;
    }

    public void getChoiceOpti() {
        optiComboBox.setOnAction(event -> {
            String selectedItem = optiComboBox.getSelectionModel().getSelectedItem();

            switch (selectedItem) {
                case "Time" -> {
                    HELBElectroController.getSortedProductListByTime();
                    startTimeline();
                }
                case "Cost" -> {
                    HELBElectroController.getSortedProductListByPrice();
                    startTimeline();
                }
                case "Score" -> {
                    HELBElectroController.getSortedProductListByScore();
                    startTimeline();
                }
                case "Diverse" ->{
                    startTimeline();
                    HELBElectroController.getSortedProductListByDiverse();
                }


            }
        });
    }

    private void startTimeline() {
        timelineChoiceOpti.stop();
        timelineChoiceOpti.getKeyFrames().clear();
        timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            HELBElectroController.createProduct();
            setButtonProduct();
        }));
        timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
        timelineChoiceOpti.play();
    }

    private void stopTimeline() {
        timelineChoiceOpti.stop();
        timelineChoiceOpti.getKeyFrames().clear();
        timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {}));
        timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
        timelineChoiceOpti.play();
    }


    protected void onComponentClicked(ActionEvent event) {
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



    private Button createStatsButton() {
        Button statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color:  #3f7ad9; -fx-text-fill: white;");
        statsButton.setOnAction(e -> {

        });
        return statsButton;
    }

    private Button createSellButton(Product product, Button bt_productFinish, Stage modal) {
        Button sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #0b6517; -fx-text-fill: white;");

        sellButton.setOnAction(e -> {
            Ticket.registerSale(product.getnameForScene(), product.getSellingPrice(),
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
