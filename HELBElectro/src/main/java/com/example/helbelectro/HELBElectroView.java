package com.example.helbelectro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HELBElectroView {
    private static HELBElectroView instance;
    private final Stage stage;
    private final HBox screen;
    static final VBox areaComponent = new VBox();
    static  GridPane areaProduct = new GridPane();
    private HBox screenCreate = new HBox();
    static final ComboBox<String> optiComboBox = new ComboBox<>();
    private static final int widthScene = 776;
    private static final int heightScene = 538;
    static Button btLetterNumber;
    static final String labelStyle = "-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;";


    private HELBElectroView(Stage stage) {
        this.stage = stage;
        this.screen = createScreen();
    }

    // Méthode statique pour obtenir l'instance unique du singleton
    public static HELBElectroView getInstance(Stage stage) {
        if (instance == null) {
            instance = new HELBElectroView(stage);
        }
        return instance;
    }

    public void afficher() {
        stage.setTitle("HELBElectro");
        VBox root = new VBox(screen);
        root.setAlignment(Pos.TOP_LEFT);
        stage.setScene(new Scene(root, widthScene, heightScene));
        stage.show();
    }

    private HBox createScreen() {
        screenCreate.setAlignment(Pos.CENTER);
        screenCreate.setPrefSize(widthScene, heightScene);
        screenCreate.setSpacing(10.0);
        screenCreate.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "#282F76", "white", 4));
        screenCreate.setPadding(new Insets(20, 20, 20, 20));

        GridPane grid = initGridAraProduct();
        btLetterNumber = new Button("Letter");
        btLetterNumber.setPrefWidth(150);
        btLetterNumber.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "white", "white", 4));
        VBox vboxComponent = initVBoxAreaComponent();

        VBox vboxGrid = new VBox();
        vboxGrid.getChildren().addAll(btLetterNumber,grid);
        screenCreate.getChildren().addAll(vboxGrid, vboxComponent);
        return screenCreate;
    }

    private GridPane initGridAraProduct() {
        areaProduct.setHgap(10);
        areaProduct.setVgap(10);
        areaProduct.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-background-color: %s;", "white", "#626786"));
        areaProduct.setPadding(new Insets(20, 20, 20, 20));
        return areaProduct;
    }

    private VBox initVBoxAreaComponent() {
        areaComponent.setSpacing(10);
        areaComponent.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", "white", "#626786"));
        HBox optiBox = createOptiBox();
        areaComponent.getChildren().addAll(optiBox, new VBox());
        return areaComponent;
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
        return optiBox;
    }
}
