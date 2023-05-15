module com.example.helbelectro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.helbelectro to javafx.fxml;
    exports com.example.helbelectro;
}
