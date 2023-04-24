module com.example.helbelectro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.electro.helbelectro to javafx.fxml;
    exports com.electro.helbelectro;
    exports com.electro.helbelectro.Component;
    opens com.electro.helbelectro.Component to javafx.fxml;
    exports com.electro.helbelectro.Area;
    opens com.electro.helbelectro.Area to javafx.fxml;
}