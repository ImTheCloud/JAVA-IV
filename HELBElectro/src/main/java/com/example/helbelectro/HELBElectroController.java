package com.example.helbelectro;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.helbelectro.HELBElectroView.*;

public class HELBElectroController implements Optimization {
    private static HELBElectroController instance;
    private static final Timeline timelineChoiceOpti = new Timeline();
    private final int numberLBComponent =8;
    private List<Label> listeLabelRow= new ArrayList<>();
    private List<Label> listeLabelCol= new ArrayList<>();
    private final int sizeColGrid = 3;
    private final int sizeRowGrid = 4;
    private final int numberButton = (sizeColGrid*sizeRowGrid)-1;
    private Label lbNumberCol;
    private Label lbNumberRow;
    private List<Label> componentLabelsList = FXCollections.observableArrayList();
    private List<Button> productButtonList;

    private HELBElectroController() {

        onOptiClicked();
        inialize();

    }

    // Méthode statique pour obtenir l'instance unique du singleton
    public static HELBElectroController getInstance() {
        if (instance == null) {
            instance = new HELBElectroController();
        }
        return instance;
    }

    // Autres méthodes de la classe
     static List<Object> productObjectList = new ArrayList<>();
     static List<Product> productObjectListSorted = new ArrayList<>();
     static List<Object> componentObjectList = new ArrayList<>();
     static AtomicBoolean isBusy = new AtomicBoolean(false);

    public  void createComponent(String componentName, String[] values) {
        // Vérifier si le nombre maximal de labels a été atteint
        if (componentObjectList.size() >= numberLBComponent) {
            System.out.println("Nombre maximal de composant atteint");
        } else {
            // Créer le composant en utilisant la Factory
            Component component = Factory.getInstance().createComponent(componentName, values);
            componentObjectList.add(component);
            System.out.println("Component " + componentName + " créé");
        }
    }

    public  void createProduct() {
        if (isBusy.get()) {
            return;
        }
        for (Product product : productObjectListSorted) {
            boolean hasAllComponents = hasAllNecessaryComponents(product);
            if (hasAllComponents && !isBusy.get()) {
                int manufacturingDuration = product.getManufacturingDuration();
                System.out.println("Attente de " + manufacturingDuration + " secondes avant de fabriquer " + product.getClass().getSimpleName());
                isBusy.set(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(manufacturingDuration), e -> {
                    Product newProduct = Factory.getInstance().createNewProduct(product);

                    if (newProduct != null) {
                        productObjectList.add(newProduct);
                        System.out.println(newProduct.getClass().getSimpleName() + " créé ");
                        removeUsedComponents(product);
                    }

                    isBusy.set(false);
                }));
                timeline.play(); // lancer la timeline pour attendre la durée de fabrication du produit
                break; // arrêter la boucle pour ne fabriquer qu'un seul produit à la fois
            }
        }
    }

    private  void removeUsedComponents(Product product) {
        List<Object> componentNames = product.getComponentListNecessary();
        for (Object componentName : componentNames) {
            // Recherche le premier composant qui correspont dans la liste des composants
            Component componentToRemove = (Component) componentObjectList.stream()
                    .filter(component -> component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName()))
                    .findFirst()
                    .orElse(null);

            if (componentToRemove != null) {
                componentObjectList.remove(componentToRemove);
            }
        }
    }



    private  boolean hasAllNecessaryComponents(Product product) {
        for (Object componentName : product.getComponentListNecessary()) {
            boolean hasComponent = componentObjectList.stream()
                    .anyMatch(component -> component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName()));
            if (!hasComponent) {
                return false;
            }
        }
        return true;
    }
        public  void addProductList() {
        productObjectListSorted.add(new ProductBattery(""));
        productObjectListSorted.add(new ProductSensor("",""));
        productObjectListSorted.add(new ProductMotor(""));
        productObjectListSorted.add(new ProductCar("",""));
        productObjectListSorted.add(new ProductAlarm("","",""));
        productObjectListSorted.add(new ProductDrone("","","",""));
        productObjectListSorted.add(new ProductRobot("","",""));
    }
    public  void getSortedProductListByTime() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getManufacturingDuration));
       // productObjectListSorted.forEach(System.out::println);

    }
    public  void getSortedProductListByScore() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getEcoScore));
    }
    public  void getSortedProductListByPrice() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getSellingPrice));
        Collections.reverse(productObjectListSorted);
    }

    public  void getSortedProductListByDiverse() {
        addProductList();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            productObjectListSorted.sort(Comparator.comparingInt(p -> Collections.frequency(productObjectList, p)));
            Collections.reverse(productObjectListSorted);
        }));
         productObjectListSorted.forEach(System.out::println);

        timeline.play();
    }

    private void onOptiClicked(){
        optiComboBox.setOnAction(event -> {
            String selectedItem =optiComboBox.getSelectionModel().getSelectedItem();
            onOptiChoiceSelected(selectedItem);
        });
    }
    @Override
    public void onOptiChoiceSelected(String selectedItem) {
        switch (selectedItem) {
            case "Time" -> {
                getSortedProductListByTime();
                startTimeline();
            }
            case "Cost" -> {
                getSortedProductListByPrice();
                startTimeline();
            }
            case "Score" -> {
                getSortedProductListByScore();
                startTimeline();
            }
            case "Diverse" -> {
                getSortedProductListByDiverse();
                startTimeline();
            }
        }
    }

    private void startTimeline() {

        timelineChoiceOpti.stop();
        timelineChoiceOpti.getKeyFrames().clear();
        timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            createProduct();
            setButtonProduct();
        }));
        timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
        timelineChoiceOpti.play();
    }

    public  void setButtonProduct() {
        int index = 0;
        int compteur = 0;
        productButtonList = new ArrayList<>(); // Création de la liste de boutons

        for (Node node : areaProduct.getChildren()) {
            if (node instanceof Button setButton) {
                if (index >= productObjectList.size()) {
                    break;
                }

                Product product = (Product) productObjectList.get(index);
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

    private  void inializeAlertForAreaProductFull() {
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
                productObjectList.clear();
                clearProductLabels();
                System.out.println("Entrepôt vidé !");
            }
        });
    }

    private void stopTimeline() {
        timelineChoiceOpti.stop();
        timelineChoiceOpti.getKeyFrames().clear();
        timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {}));
        timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
        timelineChoiceOpti.play();
    }

    public void inialize(){
        inializeGridWithNumber();
        btLetterNumber.setOnAction(this::changeNumberLetter);
        initializeProductArea();
        initializeComponentArea();
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

        for (int i = 0; i < sizeRowGrid; i++) {
            for (int j = 0; j < sizeColGrid; j++) {
                Button button = new Button();
                int btProductWith = 138;
                int btProductHeight = 73;
                button.setPrefSize(btProductWith, btProductHeight);
                button.setStyle("-fx-background-color: white;");
                button.setOnAction(DisplayProductDetail.getInstance()::onButtonProductClicked);
                if (i == sizeRowGrid -1 && j == sizeColGrid -1) {
                    // derniere case de la grid pas de bouton
                    // comme dans l'interface du prof
                    continue;
                }
                areaProduct.add(button, j+1, i+1);
            }
        }
    }
    private void changeNumberLetter(ActionEvent actionEvent) {
        areaProduct.getChildren().removeAll(listeLabelCol);
        areaProduct.getChildren().removeAll(listeLabelRow);
        if(btLetterNumber.getText().equals("Letter")){
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
            btLetterNumber.setText("Letter");
            // Ajout des numéros de colonne
             inializeGridWithNumber();
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

    public void clearProductLabels() {
        for (Button button : productButtonList) {
            button.setStyle("-fx-background-color: #FFFFFF;");
            button.setText("");
        }
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


    private void setLabelComponents() {
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



}