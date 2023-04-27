module com.example.helbelectro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.helbelectro to javafx.fxml;
    exports com.example.helbelectro;
}