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
    // DESIN PATTERN MVC
    private static HELBElectroView instance;
    private final Stage stage;
    private final HBox screen;
    private final int setSpace = 10;
    private final int setPadding = 20;
    private final HBox screenCreate = new HBox();
    private final int widthScene = 776;
    private final int heightScene = 538;
    static final VBox areaComponent = new VBox();
    static GridPane areaProduct = new GridPane();
    static final ComboBox<String> optiComboBox = new ComboBox<>();
    static Button btLetterNumber;
    static final String labelStyle = "-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;";

    private HELBElectroView(Stage stage) {
        this.stage = stage;
        this.screen = createScreen();
    }

    // methode statique pour obtenir l'instance unique du singleton
    public static HELBElectroView getInstance(Stage stage) {
        if (instance == null) {
            instance = new HELBElectroView(stage);
        }
        return instance;
    }

    // methode simple pour afficher le stage dans le main
    public void display() {
        stage.setTitle("HELBElectro");
        VBox root = new VBox(screen);
        root.setAlignment(Pos.TOP_LEFT);
        stage.setScene(new Scene(root, widthScene, heightScene));
        stage.show();
    }

    // j'a diviser la class en methode pour bien diviser le code et le rendre maintelable
    // voici createScreen qui va crée l'ecran qui affichera les composants et les produits
    // dans cette methode sera appeler plusieurs petite methode avec a chaque fois un comportement
    // propre au nom de la methode
    private HBox createScreen() {
        screenCreate.setAlignment(Pos.CENTER);
        screenCreate.setPrefSize(widthScene, heightScene);
        screenCreate.setSpacing(setSpace);
        screenCreate.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "#282F76", "white", 4));
        screenCreate.setPadding(new Insets(setPadding, setPadding, setPadding, setPadding));

        GridPane grid = initializeGridAraProduct();
        btLetterNumber = new Button("Letter");
        int sizeButton =150;
        btLetterNumber.setPrefWidth(sizeButton);
        btLetterNumber.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "white", "white", 4));
        VBox vboxComponent = initializeVBoxAreaComponent();

        VBox vboxGrid = new VBox();
        vboxGrid.getChildren().addAll(btLetterNumber,grid);
        screenCreate.getChildren().addAll(vboxGrid, vboxComponent);
        return screenCreate;
    }

    private GridPane initializeGridAraProduct() {
        int gap = 10;
        areaProduct.setHgap(gap);
        areaProduct.setVgap(gap);
        areaProduct.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-background-color: %s;", "white", "#626786"));
        areaProduct.setPadding(new Insets(setPadding, setPadding, setPadding, setPadding));
        return areaProduct;
    }

    private VBox initializeVBoxAreaComponent() {
        areaComponent.setSpacing(setSpace);
        areaComponent.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", "white", "#626786"));
        HBox optiBox = createOptiBox();
        areaComponent.getChildren().addAll(optiBox, new VBox());
        return areaComponent;
    }

    // liste observable pour la liste des string des opti, afinn de generer un event
    // et ecouter si il y a un changements
    private HBox createOptiBox() {
        HBox optiBox = new HBox();
        optiBox.setStyle(String.format("-fx-border-color: %s; -fx-font-weight: bold; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-color: %s;", "white", "#626786"));
        optiBox.setSpacing(setSpace);
        Label optiLabel = new Label("Opti : ");
        optiLabel.setStyle("-fx-font-weight: bold;  -fx-text-fill: white;");
        optiComboBox.setValue("Choice");
        ObservableList<String> optiList = FXCollections.observableArrayList("Time", "Cost", "Score", "Diverse");
        optiComboBox.setItems(optiList);
        optiBox.getChildren().addAll(optiLabel, optiComboBox);
        return optiBox;
    }


}
