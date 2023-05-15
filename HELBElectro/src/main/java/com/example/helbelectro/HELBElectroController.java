package com.example.helbelectro;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.helbelectro.HELBElectroView.*;

public class HELBElectroController implements Optimization {
    private static HELBElectroController instance;
    private final Timeline timelineChoiceOpti = new Timeline();
    private List<Label> listeLabelRow= new ArrayList<>();
    private List<Label> listeLabelCol= new ArrayList<>();
    private Label lbNumberCol;
    private Label lbNumberRow;
    private final int numberLBComponent =8; //  par defaut pour le projet
    private final int sizeColGrid = 3;//  par defaut pour le projet
    private final int sizeRowGrid = 4;//  par defaut pour le projet
    private final int numberButton = (sizeColGrid*sizeRowGrid)-1;
    private List<Label> componentLabelsList = FXCollections.observableArrayList();
    private List<Button> productButtonList;
    private List<Product> productObjectListSorted = new ArrayList<>();
    private List<Object> componentObjectList = new ArrayList<>();
    private AtomicBoolean isBusy = new AtomicBoolean(false);
    List<Object> productObjectList = new ArrayList<>();

    private MySubjectObserved subjet;
    private Observer observer;
    private HELBElectroController() {
        initialize();
        subjet = new MySubjectObserved();
        observer = new MyObserver();
        subjet.ajouterObservateur(observer);
        subjet.notifierObservateurs();
    }

    // methode statique pour obtenir l'instance unique du singleton
    public static HELBElectroController getInstance() {
        if (instance == null) {
            instance = new HELBElectroController();
        }
        return instance;
    }

    // simple methode de creation des composants
    public void createComponent(String componentName, String[] values) {
        // Vérifier si le nombre maximal de labels a été atteint
        if (componentObjectList.size() < numberLBComponent) {
            Component component = Factory.getInstance().createComponent(componentName, values);
            componentObjectList.add(component);
        } else {
            // petite alerte pour indique a l'utilisateur que la zone est pleine
            String message = "Nombre maximal de composant atteint";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Zone de stocakge des composants");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();
        }
    }


    // la creation de produit selon les composants disponible
    // pour mieux expliquer je vais prendre comme exemple le choix opti
    // de cout et le 1er sera donc drone
    public void createProduct() {
        // verifie si le processus de fabrication est deja en cours
        if (isBusy.get()) {
            return;
        }
        // d'abord parcour la liste des produit trié selon le choix de l'opti
        // si c'est par cout le 1rer de la liste sera Drone
        for (Product product : productObjectListSorted) {
            // ensuite il faut verifier si il y a tous les composants neccesaire a la creation du drone
            boolean hasAllComponents = hasAllNecessaryComponents(product);
            if (hasAllComponents && !isBusy.get()) {
                int manufacturingDuration = product.getManufacturingDuration();
                isBusy.set(true);     // si c'est en cours de fabrication alors attendre et pas faire una ture produit en mm temps

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(manufacturingDuration), e -> {
                    Product newProduct = Factory.getInstance().createNewProduct(product);

                    if (newProduct != null) {
                        productObjectList.add(newProduct);
                        removeUsedComponents(product);
                    }
                    isBusy.set(false);   // une fois le produit crée, le suivant peux se crée
                }));
                timeline.play();
                break; // arete la boucle pour fabrique les produit 1 a 1
            }
        }
    }

    private void removeUsedComponents(Product product) {
        // Récuper la liste  des composants necessaire pour le produit
        List<Object> componentNames = product.getComponentListNecessary();
        for (Object componentName : componentNames) {
            // Recherche le premier composant correspondant dans la liste des composants
            // important de faire ainsi sinon ca peux suprimer d'autre composants a la place
            Component componentToRemove = (Component) componentObjectList.stream()
                    .filter(component -> component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName()))
                    .findFirst()
                    .orElse(null);

            if (componentToRemove != null) {
                // Supprime le composant de la liste des composants
                componentObjectList.remove(componentToRemove);
            }
        }
    }

    private boolean hasAllNecessaryComponents(Product product) {
        // le getComponentListNecessary provient de ma class product et chaque class
        // fille hirite de cette liste et chaque produit aura ses propre composant neccesaire a sa creation
        for (Object componentName : product.getComponentListNecessary()) {
            // methode stram permet la recherche des composants
            // ca permet de effectuer une opération de flux sur la liste
            boolean hasComponent = componentObjectList.stream()
                    // any match verifie si au moin composant a le meme nom que le composant disponible
                    .anyMatch(component -> component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName()));
            if (!hasComponent) {
                return false;
            }
        }
        return true;
    }


    // methode pour juste ajouter les produits a la liste trier
    // ensuite je vais trier les listess, j'ajoute avec de parametre vide car c'est pas important d'en ajouter
    public  void addProductList() {
        productObjectListSorted.add(new ProductBattery(""));
        productObjectListSorted.add(new ProductMotionSensor("",""));
        productObjectListSorted.add(new ProductElectricMotor(""));
        productObjectListSorted.add(new ProductRemoteCar("",""));
        productObjectListSorted.add(new ProductSecurityAlarm("","",""));
        productObjectListSorted.add(new ProductMonitoringDrone("","","",""));
        productObjectListSorted.add(new ProductTrackingRobot("","",""));
    }

    // trie par temps
    public  void getSortedProductListByTime() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getManufacturingDuration));
    }
    // trie par score
    public  void getSortedProductListByScore() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getEcoScore));
    }
    // trie par cout
    public  void getSortedProductListByPrice() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getSellingPrice));
        Collections.reverse(productObjectListSorted);
    }
    // trie par diverse
    // la frequence va trier selon le moin frquent au plus frequent
    public  void getSortedProductListByDiverse() {
        productObjectListSorted.sort(Comparator.comparingInt(p -> Collections.frequency(productObjectList, p)));
        Collections.reverse(productObjectListSorted);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // logique metier

    private void onOptiClicked(){
        optiComboBox.setOnAction(event -> {
            String selectedItem =optiComboBox.getSelectionModel().getSelectedItem();
            onOptiChoiceSelected(selectedItem);
        });
    }

    @Override
    public void onOptiChoiceSelected(String selectedItem) {
        switch (selectedItem) {
// trie les listes en fonction de l'optimisation
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
            case "Pause" -> {
                stopTimeline();
            }
        }
    }

    // timeline qui apel la la methode de creation des produits en fonction de l'opti
    //  etles ajoute dans les boutons a chaque fois que ya un nouveau produit dans la liste de produit
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

    // arret de la timeline seulement quand on choisi l'opti pause
    // ou alors quand la zone des produits est pleine
    private void stopTimeline() {
        timelineChoiceOpti.stop();
        timelineChoiceOpti.getKeyFrames().clear();
        timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {}));
        timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
        timelineChoiceOpti.play();
    }

    // ajoute les produits au bouton
    public void setButtonProduct() {
        int index = 0; // index pour acceder aux produits dans la liste productObjectList
        int nbBtAdded = 0;
        productButtonList = new ArrayList<>();

        for (Node node : areaProduct.getChildren()) {
            // verifie si le nœud est un bouton et l'assigne a setButton
            if (node instanceof Button setButton) {
                if (index >= productObjectList.size()) {
                    // verif si tous les produits ont été traités
                    // sinon gros bug sans cette verif
                    break;
                }

                Product product = (Product) productObjectList.get(index); //index produit actuel
                setButton.setUserData(product);
                setButton.setText(product.getnameForP());
                setButton.setStyle("-fx-background-color: " + product.getColor() + ";");
                productButtonList.add(setButton);

                index++;
                nbBtAdded++;
                if (nbBtAdded == numberButton) {
                    inializeAlertForAreaProductFull();
                    break;
                }
            }
        }
    }

    private void inializeAlertForAreaProductFull() {
        // arret de la production
        stopTimeline();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Entrepôt des produits");
        alert.setHeaderText(null);
        alert.setContentText("L'entrepôt est complet, veuillez le vider !");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        // utilisation du stage car le showandwait genere des bugs
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setOnShown(event -> {
            Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
            okBtn.setOnAction(e -> {
                productObjectList.clear();
                clearProductBt();
                stage.close();
            });
        });

        alert.show();
    }



    public void clearProductBt() {
        for (Button button : productButtonList) {
            button.setStyle("-fx-background-color: #FFFFFF;");
            button.setText("");
            button.setUserData(null);
        }
    }

    public void initializeProductArea() {
        for (int i = 0; i < sizeColGrid; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS); // agrandir la collone si ya 500 ca aggrandit
            areaProduct.getColumnConstraints().add(column); // ajout de al contrainte
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
                button.setOnAction(ProductDetail.getInstance()::onButtonProductClicked);
                if (i == sizeRowGrid -1 && j == sizeColGrid -1) {
                    // derniere case de la grid pas de bouton
                    // comme dans l'interface du prof
                    continue;
                }
                areaProduct.add(button, j+1, i+1);
            }
        }
    }
    // method ee changement des nombre en lettre et l'inverse
    private void changeNumberLetter(ActionEvent actionEvent) {
        // supprimer les labels de colonnes et de ligne existants car sinon ca reecrit dessus
        areaProduct.getChildren().removeAll(listeLabelCol);
        areaProduct.getChildren().removeAll(listeLabelRow);
        if(btLetterNumber.getText().equals("Lettres")){
            for (int j = 0; j < sizeColGrid; j++) {
                // cree un nouveau label avec la lettre correspondante a l'indice actuel de la boucle
                lbNumberCol = new Label(String.valueOf((char) ('A' + j)));
                // la lettre est obtenue en ajoutant la valeur ASCII de 'A' à l'indice actuel
                lbNumberCol.setStyle(labelStyle);
                areaProduct.add(lbNumberCol, j+1, 0); // pour ajouter a la bonne ligne etcolonne
                listeLabelCol.add(lbNumberCol);
            }
            for (int i = 0; i < sizeRowGrid; i++) {
               lbNumberRow = new Label(String.valueOf((char) ('A' + i)));
                lbNumberRow.setStyle(labelStyle);
                areaProduct.add(lbNumberRow, 0, i+1);
                listeLabelRow.add(lbNumberRow);
            }
            btLetterNumber.setText("Nombres");
        }else{
            btLetterNumber.setText("Lettres");
             inializeGridWithNumber();
        }
    }

    public void inializeGridWithNumber(){
        // inialiser plus simple que avec des nombres
        btLetterNumber.setOnAction(this::changeNumberLetter);
        for (int j = 0; j < sizeColGrid; j++) {
            lbNumberCol = new Label(String.valueOf(j));
             lbNumberCol.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
            areaProduct.add(lbNumberCol, j+1, 0);
            listeLabelCol.add(lbNumberCol);
        }
        for (int i = 0; i < sizeRowGrid; i++) {
            lbNumberRow = new Label(String.valueOf(i));
            lbNumberRow.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
            areaProduct.add(lbNumberRow, 0, i+1);
            listeLabelRow.add(lbNumberRow);
        }
    }

    // inialiser la zone des composants avec les composant a chaque fois qu'il vienne
    public void initializeComponentArea() {
        componentLabelsList = new ArrayList<>();
        for (int i = 0; i < numberLBComponent; i++) {
            // ajoute les 8 label par defaut du prof
            Label label = new Label();
            label.setPrefSize(183, 42);
            label.setId("component" + i);
            label.setStyle("-fx-background-color: white;");
            label.setAlignment(Pos.CENTER);
            componentLabelsList.add(label);
            areaComponent.getChildren().add(label);
        }
        setLabelComponents();
    }

    // modifie les labels en fonction du composant qui arrive
    private void setLabelComponents() {
        Timeline timelineComponent = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            try {
                // appelle la metjode du parer pour le fichier de simulation
                Parser.getInstance().parseSimulationFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            // recuperer la liste des composant actuel
            List<Object> componentList = componentObjectList;
            if (componentList.size() != componentLabelsList.size()) {
                // ca c'est pour effacer les composant qui ont ete utiliser a la creation d'un produit
                clearComponentLabels();
            }
            // modifie les label en composants
            updateComponentLabels(componentList);
        }));
        timelineComponent.setCycleCount(Animation.INDEFINITE);
        timelineComponent.play();
    }

    // remetre le design par defaut du label
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
                // prendre le bon composant avec l'index
                Label componentLabel = getComponentLabel(index);
                // modifie le label avec la couleur et le nom du composants
                componentLabel.setText(currentComponent.getName());
                componentLabel.setStyle("-fx-background-color: " + currentComponent.getColor());
                index++;
            }
        }
    }
    // avoir l'index du composant en cours
    private Label getComponentLabel(int index) {
        return componentLabelsList.get(index - 1);
    }

    // methode initialize qui appelle les autre methode
    public void initialize(){
        inializeGridWithNumber();
        initializeProductArea();
        initializeComponentArea();
        onOptiClicked();
    }

}