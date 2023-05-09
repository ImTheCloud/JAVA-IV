package com.example.helbelectro;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class HELBElectroView extends Application {

    private final VBox areaComponent = new VBox();
    private final GridPane areaProduct = new GridPane();
    private final Timeline timelineChoiceOpti = new Timeline();
    private final ComboBox<String> optiComboBox = new ComboBox<>();
    private Button bt_changeltNb = new Button();
    private List<Label> componentLabelsList;
    public static final int number_lb_component =8;
    private final int widthScene = 776;
    private final int heightScene = 538;
    @Override
    public void start(Stage stage) {
        HBox screen = createScreen();
        Scene scene = new Scene(screen, widthScene, heightScene);
        stage.setTitle("HELBElectro");

        bt_changeltNb =button();
        VBox root = new VBox(bt_changeltNb, screen);
        root.setAlignment(Pos.TOP_LEFT);
        stage.setScene(new Scene(root, widthScene, heightScene));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    private Button button() {
        bt_changeltNb = new Button("Letter");
        return bt_changeltNb;
    }


    private HBox createScreen() {
        HBox screen = new HBox();
        screen.setAlignment(Pos.CENTER);
        screen.setPrefSize(widthScene, heightScene);
        screen.setSpacing(10.0);
        screen.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "#282F76", "white", 4));
        screen.setPadding(new Insets(20, 20, 20, 20));
        GridPane grid = gd_araProduct();
        VBox vbox = vb_areaComponent();
        screen.getChildren().addAll(grid, vbox);
        return screen;
    }


    private GridPane gd_araProduct() {
        areaProduct.setHgap(10);
        areaProduct.setVgap(10);
        areaProduct.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-background-color: %s;", "white", "#626786"));
        areaProduct.setPadding(new Insets(20, 20, 20, 20));
        initializeProductArea();
        return areaProduct;
    }

    public void initializeProductArea() {
        int size_colGrid = 3;
        for (int i = 0; i < size_colGrid; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS); // agrandir
            areaProduct.getColumnConstraints().add(column);
        }

        int size_rowGrid = 4;
        for (int i = 0; i < size_rowGrid; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            areaProduct.getRowConstraints().add(row);
        }

        // Ajout des numéros de colonne
        for (int j = 0; j < size_colGrid; j++) {
            Label label = new Label(String.valueOf(j));
            label.setStyle("-fx-font-size: 14; -fx-font-weight: bold;  -fx-text-fill: white;");
            areaProduct.add(label, j+1, 0);
        }

// Ajout des numéros de ligne
        for (int i = 0; i < size_rowGrid; i++) {
            Label label = new Label(String.valueOf(i));
            label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
            areaProduct.add(label, 0, i+1);
        }

        for (int i = 0; i < size_rowGrid; i++) {
            for (int j = 0; j < size_colGrid; j++) {
                Button button = new Button();
                int bt_product_with = 138;
                int bt_product_height = 73;
                button.setPrefSize(bt_product_with, bt_product_height);
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



    public void setButtonProduct() {
        int index = 0;
        for (Node node : areaProduct.getChildren()) { // node pour parcouri la grid
            if (node instanceof Button setButton) { // on peux donner un nom au bouton deja ici
                if (index >= HELBElectroController.productObjectList.size()) { // index out of bound si on ne verifie pas l'index
                    break;
                }
                // vu que les bouton change de valeur il faut bien faire en sorte
                // que ce soit ces bouton qui change sans en crer d'autre
                Product product = (Product) HELBElectroController.productObjectList.get(index);
                setButton.setUserData(product);
                setButton.setText(product.getnameForP());
                setButton.setStyle("-fx-background-color: " + product.getColor() + ";");
                index++;
            }
        }
        HELBElectroController.productObjectList.clear(); // on vide sinon a chaque ajoute ca recommence pour tout
    }

    private VBox vb_areaComponent() {
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
            int lb_component_with = 183;
            int lb_component_height = 42;
            label.setPrefSize(lb_component_with, lb_component_height);
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
                Parser.getInstance().parseSimulationFile();
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
        return componentLabelsList.get(index - 1);
    }

    private HBox createOptiBox() {
        HBox optiBox = new HBox();
        optiBox.setStyle(String.format("-fx-border-color: %s; -fx-font-weight: bold; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", "white", "#626786"));
        optiBox.setSpacing(10);
        Label optiLabel = new Label("Opti : ");
        optiLabel.setStyle("-fx-font-weight: bold;  -fx-text-fill: white;");
        optiComboBox.setValue("Pause");
        ObservableList<String> optiList = FXCollections.observableArrayList("Pause","Cost", "Time", "Score", "Diverse");
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
                    timelineChoiceOpti.stop();
                    timelineChoiceOpti.getKeyFrames().clear();
                    timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                        HELBElectroController.createProduct();
                        setButtonProduct();
                    }));
                    timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                    timelineChoiceOpti.play();
                }
                case "Cost" -> {
                    HELBElectroController.getSortedProductListByPrice();
                    timelineChoiceOpti.stop();
                    timelineChoiceOpti.getKeyFrames().clear();
                    timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                        HELBElectroController.createProduct();
                        setButtonProduct();
                    }));
                    timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                    timelineChoiceOpti.play();
                }
                case "Score" -> {
                    HELBElectroController.getSortedProductListByScore();
                    timelineChoiceOpti.stop();
                    timelineChoiceOpti.getKeyFrames().clear();
                    timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                        HELBElectroController.createProduct();
                        setButtonProduct();
                    }));
                    timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                    timelineChoiceOpti.play();
                }
                case "Diverse" -> {
                    timelineChoiceOpti.stop();
                    timelineChoiceOpti.getKeyFrames().clear();
                    timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                    }));
                    timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                    timelineChoiceOpti.play();
                }
                case "Pause" -> {
                    timelineChoiceOpti.stop();
                    System.out.println("Stop production produit");
                }
            }
        });
    }

    protected void onComponentClicked(ActionEvent event) {
        // Pour savoir quel bouton a été cliqué
        Button bt_productFinish = (Button) event.getSource();
        // Obtient l'indice de la ligne et de la colonne du bouton dans la grille
        int rowIndex = GridPane.getRowIndex(bt_productFinish)-1;
        int columnIndex = GridPane.getColumnIndex(bt_productFinish)-1;
        // Obtient les attributs du produit associé au bouton
        Label emplacements = new Label("Emplacements (" + rowIndex + ", " + columnIndex + ")");

        Product product = (Product) bt_productFinish.getUserData();


        // Crée une nouvelle fenêtre
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        Label type,price,ecoScore;

            if( product != null){
                // Affiche les attributs du produit
                type = new Label("Type de produit: " + product.getnameForScene());
                 price = new Label("Prix : " + product.getSellingPrice()+" euros");
                 ecoScore = new Label("Eco-Score : " + product.getEcoScore());
            }else{
                type = new Label("Emplacement vide");
                price = new Label("Pas de prix");
                ecoScore = new Label("Pas de score");
            }

        Button statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color:  #3f7ad9; -fx-text-fill: white;");
        statsButton.setOnAction(e -> {
        });

        Button sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #0b6517; -fx-text-fill: white;");

        sellButton.setOnAction(e -> {
            if( product != null){
                // singleton
                Ticket.getInstance().registerSale(product.getnameForScene(),product.getSellingPrice(),product.getEcoScore()); // Appelle la méthode de la classe Ticket
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vente enregistrée");
                alert.setHeaderText(null);
                alert.setContentText("Le produit a été vendu !");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pas de vente");
                alert.setHeaderText(null);
                alert.setContentText("Impossible de vendre un produit inexistant !");
                alert.showAndWait();
            }
            modal.close();
            bt_productFinish.setStyle("-fx-background-color: white;");
            bt_productFinish.setText("");

        });
        // UNE VBox pour ajouter les labels
        VBox vbox = new VBox(emplacements,type, price, ecoScore, statsButton, sellButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        modal.setScene(new Scene(vbox, 400, 350));
        modal.showAndWait();
    }
}