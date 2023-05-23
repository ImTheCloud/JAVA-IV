module com.example.helbelectro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.helbelectro to javafx.fxml;
    exports com.example.helbelectro;
    exports com.example.helbelectro.product;
    opens com.example.helbelectro.product to javafx.fxml;
    exports com.example.helbelectro.component;
    opens com.example.helbelectro.component to javafx.fxml;
    exports com.example.helbelectro.product.strategyProduct;
    opens com.example.helbelectro.product.strategyProduct to javafx.fxml;
}
