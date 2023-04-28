module com.example.helbelectro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.helbelectro to javafx.fxml;
    exports com.example.helbelectro;
    exports com.example.helbelectro.Component;
    opens com.example.helbelectro.Component to javafx.fxml;
    exports com.example.helbelectro.Area;
    opens com.example.helbelectro.Area to javafx.fxml;
    exports com.example.helbelectro.Factory;
    opens com.example.helbelectro.Factory to javafx.fxml;
}