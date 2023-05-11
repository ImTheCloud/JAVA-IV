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
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HELBElectroView {
    private final Stage stage;
    private final HBox screen;
    private Label lbNumberCol;
    private Label lbNumberRow;
    private final List<Label> listeLabelRow= new ArrayList<>();
    private final List<Label> listeLabelCol= new ArrayList<>();
    private Button btLetterNumber;
    private final VBox areaComponent = new VBox();
    private final GridPane areaProduct = new GridPane();
    private final Timeline timelineChoiceOpti = new Timeline();
    private final ComboBox<String> optiComboBox = new ComboBox<>();
    private final int sizeColGrid = 3;
    private final int sizeRowGrid = 4;
    private final int numberButton = (sizeColGrid*sizeRowGrid)-1;
    private List<Label> componentLabelsList;
    private List<Button> productButtonList;
    static final int numberLBComponent =8;
    private final int widthScene = 776;
    private final int heightScene = 538;
    private final String setNameButtonToLetter = "Letter";
    private final String labelStyle = "-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;";

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

        btLetterNumber = new Button(setNameButtonToLetter);
        btLetterNumber.setPrefWidth(150);
        btLetterNumber.setOnAction(this::changeNumberLetter);
        btLetterNumber.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: %dpx;", "white", "white", 4));
        VBox vboxComponent = initVBoxAreaComponent();

        VBox vboxGrid = new VBox();
        vboxGrid.getChildren().addAll(btLetterNumber,grid);
        screen.getChildren().addAll(vboxGrid, vboxComponent);
        return screen;
    }

    private void changeNumberLetter(ActionEvent actionEvent) {
        areaProduct.getChildren().removeAll(listeLabelCol);
        areaProduct.getChildren().removeAll(listeLabelRow);
        if(btLetterNumber.getText().equals(setNameButtonToLetter)){
            for (int j = 0; j < sizeColGrid; j++) {
                lbNumberCol = new Label(String.valueOf((char) ('A' + j)));
                lbNumberCol.setStyle(labelStyle);
                areaProduct.add(lbNumberCol, j+1, 0);
                listeLabelCol.add(lbNumberCol);
            }
            for (int i = 0; i < sizeRowGrid; i++) {
                lbNumberRow = new Label(String.valueOf((char) ('A' + i)));
                lbNumberRow.setStyle(labelStyle);
                areaProduct.add(lbNumberRow, 0, i+1);
                listeLabelRow.add(lbNumberRow);
            }
            btLetterNumber.setText("Number");
        }else{
            btLetterNumber.setText(setNameButtonToLetter);
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
        for (int i = 0; i < sizeColGrid; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS); // agrandir
            areaProduct.getColumnConstraints().add(column);
        }
        for (int i = 0; i < sizeRowGrid; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            areaProduct.getRowConstraints().add(row);
        }

        inializeGridWithNumber();

        for (int i = 0; i < sizeRowGrid; i++) {
            for (int j = 0; j < sizeColGrid; j++) {
                Button button = new Button();
                int btProductWith = 138;
                int btProductHeight = 73;
                button.setPrefSize(btProductWith, btProductHeight);
                button.setStyle("-fx-background-color: white;");
                button.setOnAction(DisplayProductDetail::onButtonProductClicked);
                if (i == sizeRowGrid -1 && j == sizeColGrid -1) {
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
        for (int j = 0; j < sizeColGrid; j++) {
            lbNumberCol = new Label(String.valueOf(j));
            lbNumberCol.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
            areaProduct.add(lbNumberCol, j+1, 0);
            listeLabelCol.add(lbNumberCol);
        }

        // Ajout des numéros de ligne
        for (int i = 0; i < sizeRowGrid; i++) {
            lbNumberRow = new Label(String.valueOf(i));
            lbNumberRow.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
            areaProduct.add(lbNumberRow, 0, i+1);
            listeLabelRow.add(lbNumberRow);
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
                    HELBElectroController.clearListProduct();
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
        for (int i = 0; i < numberLBComponent; i++) {
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
            if (component instanceof Component currentComponent) {
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
}
