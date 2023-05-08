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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class HELBElectro extends Application {

    private final VBox areaComponent = new VBox();
    private final GridPane areaProduct = new GridPane();
    private Button bt_productFInish = null,sellButton = null,statsButton = null;
    private Timeline timelineComponent;
    private Timeline timelineChoiceOpti = new Timeline();
    private ComboBox<String> optiComboBox = new ComboBox<>();
    private List<Label> componentLabelsList;
    public static final int number_lb_component =8;
    private final int lb_component_height = 42;
    private final int lb_component_with = 183;
    private final int size_row = 4;
    private final int size_col = 3;
    private final int bt_product_height = 73;
    private final int bt_product_with = 138;

    private final int WIDTH = 776;
    private final int HEIGHT = 538;
    private final String BACKGROUND_COLOR = "#282F76";
    private final String BORDER_COLOR = "white";
    private final int BORDER_WIDTH = 4;
    private final String GRID_BACKGROUND_COLOR = "#626786";
    private final int GRID_PADDING = 20;
    private final int COMPONENT_SPACING = 10;
    private final int OPTI_SPACING = 10;
    private final String OPTI_BOX_BACKGROUND_COLOR = "#626786";
    private final String OPTI_LABEL_TEXT = "Opti : ";
    private final int OPTI_LABEL_FONT_SIZE = 20;
    private final Color OPTI_LABEL_COLOR = Color.WHITE;
    private final String DEFAULT_OPTI_VALUE = "Diverse";
    private final ObservableList<String> OPTI_LIST = FXCollections.observableArrayList("Cost", "Time", "Score", "Diverse");

    @Override
    public void start(Stage stage) {
        HBox root = createRoot();
        Scene scene = new Scene(root, WIDTH + 74, HEIGHT + 42);
        stage.setTitle("HELBElectro");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    private HBox createRoot() {
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(WIDTH, HEIGHT);
        root.setSpacing(20.0);
        root.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", BACKGROUND_COLOR, BORDER_COLOR, BORDER_WIDTH));
        root.setPadding(new Insets(20, 20, 20, 20));
        GridPane grid = gd_araProduct();
        VBox vbox = vb_areaComponent();
        root.getChildren().addAll(grid, vbox);

        return root;
    }

    private GridPane gd_araProduct() {
        areaProduct.setId("areaProduct");
        areaProduct.setHgap(20);
        areaProduct.setVgap(20);
        areaProduct.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-background-color: %s;", BORDER_COLOR, GRID_BACKGROUND_COLOR));
        areaProduct.setPadding(new Insets(GRID_PADDING, GRID_PADDING, GRID_PADDING, GRID_PADDING));
        Label label = new Label();
        label.setId("place");
        areaProduct.add(label, 0, 1, 3, 1);
        initializeProductArea();
        return areaProduct;
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

    public void setButtonProduct() {
        List<Product> productList = new ArrayList<>();
        for (Object obj : Factory.productObjectList) {
            if (obj instanceof Product) {
                productList.add((Product) obj);
            }
        }

        int index = 0;
        for (Node node : areaProduct.getChildren()) {
            if (node instanceof Button) {
                if (index >= productList.size()) {
                    break;
                }
                Product product = productList.get(index);
                Button button = (Button) node;
                button.setUserData(product);
                button.setText(product.getnameForP());
                button.setStyle("-fx-background-color: " + product.getColor() + ";");
                index++;
            }
        }
        Factory.productObjectList.clear();
    }

    private VBox vb_areaComponent() {
        areaComponent.setSpacing(COMPONENT_SPACING);
        areaComponent.setId("areaComponent");
        areaComponent.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", BORDER_COLOR, GRID_BACKGROUND_COLOR));
        HBox optiBox = createOptiBox();
        areaComponent.getChildren().addAll(optiBox, new VBox());
        initializeComponentArea();
        return areaComponent;
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
        setLabelComponents();
    }

    public void setLabelComponents() {
        timelineComponent = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
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
        if (index < 1 || index > componentLabelsList.size()) {
        }
        return componentLabelsList.get(index - 1);
    }

    private HBox createOptiBox() {
        HBox optiBox = new HBox();
        optiBox.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", BORDER_COLOR, OPTI_BOX_BACKGROUND_COLOR));
        optiBox.setSpacing(OPTI_SPACING);
        Label optiLabel = new Label(OPTI_LABEL_TEXT);
        optiLabel.setTextFill(OPTI_LABEL_COLOR);


        optiComboBox.setId("cb_opti");
        optiComboBox.setValue("Diverse");
        ObservableList<String> optiList = FXCollections.observableArrayList("Cost", "Time", "Score", "Diverse");
        optiComboBox.setItems(optiList);

        optiBox.getChildren().addAll(optiLabel, optiComboBox);
       getChoiceOpti();
        return optiBox;
    }

    public void getChoiceOpti() {
        optiComboBox.setOnAction(event -> {
            String selectedItem = optiComboBox.getSelectionModel().getSelectedItem();
            if (selectedItem.equals("Time")) {
                Factory.getSortedProductListByTime();
                timelineChoiceOpti.stop();
                timelineChoiceOpti.getKeyFrames().clear();
                timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                    Factory.createProduct();
                     setButtonProduct();
                }));
                timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                timelineChoiceOpti.play();
            } else if (selectedItem.equals("Cost")) {
                Factory.getSortedProductListByPrice();
                timelineChoiceOpti.stop();
                timelineChoiceOpti.getKeyFrames().clear();
                timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                    Factory.createProduct();
                     setButtonProduct();
                }));
                timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                timelineChoiceOpti.play();
            } else if (selectedItem.equals("Score")) {
                Factory.getSortedProductListByScore();
                timelineChoiceOpti.stop();
                timelineChoiceOpti.getKeyFrames().clear();
                timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                    Factory.createProduct();
                       setButtonProduct();
                }));
                timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                timelineChoiceOpti.play();
            } else if (selectedItem.equals("Diverse")) {
                //Factory.getSortedProductListByScore();
                //setButtonProduct();
                timelineChoiceOpti.stop();
                timelineChoiceOpti.getKeyFrames().clear();
                timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
                    //Factory.createProduct();
                }));
                timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
                timelineChoiceOpti.play();
            }
        });
    }


    protected void onComponentClicked(ActionEvent event) {
        // Pour savoir quel bouton a été cliqué
        bt_productFInish = (Button) event.getSource();
        // Obtient l'indice de la ligne et de la colonne du bouton dans la grille
        int rowIndex = GridPane.getRowIndex(bt_productFInish);
        int columnIndex = GridPane.getColumnIndex(bt_productFInish);
        // Obtient les attributs du produit associé au bouton
        Label emplacements = new Label("Emplacements (" + rowIndex + ", " + columnIndex + ")");

        Product product = (Product) bt_productFInish.getUserData();


        // Crée une nouvelle fenêtre
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);

        // Affiche les attributs du produit
        Label type = new Label("Type de produit: " + product.getnameForScene());
        Label price = new Label("Prix : " + product.getSellingPrice()+" euros");
        Label ecoScore = new Label("Eco-Score : " + product.getEcoScore());

        statsButton = new Button("Voir les statistiques de cet emplacement");
        statsButton.setStyle("-fx-background-color:  #3f7ad9; -fx-text-fill: white;");
        statsButton.setOnAction(e -> {
        });

        sellButton = new Button("Vendre produit");
        sellButton.setStyle("-fx-background-color: #0b6517; -fx-text-fill: white;");
        sellButton.setOnAction(e -> {

            // singleton
            Ticket.getInstance().registerSale(product.getnameForScene(),product.getSellingPrice(),product.getEcoScore()); // Appelle la méthode de la classe Ticket

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vente enregistrée");
            alert.setHeaderText(null);
            alert.setContentText("Le produit a été vendu !");
            alert.showAndWait();
            modal.close();
            bt_productFInish.setStyle("-fx-background-color: white;");
            bt_productFInish.setText("");
        });

        // UNE VBox pour ajouter les labels
        VBox vbox = new VBox(emplacements,type, price, ecoScore, statsButton, sellButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        modal.setScene(new Scene(vbox, 400, 350));
        modal.showAndWait();
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}