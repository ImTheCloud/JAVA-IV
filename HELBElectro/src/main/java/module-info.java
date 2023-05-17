module com.example.helbelectro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.helbelectro to javafx.fxml;
    exports com.example.helbelectro;
    exports com.example.helbelectro.product;
    opens com.example.helbelectro.product to javafx.fxml;
    exports com.example.helbelectro.conponent;
    opens com.example.helbelectro.conponent to javafx.fxml;
    exports com.example.helbelectro.strategy;
    opens com.example.helbelectro.strategy to javafx.fxml;
}
