module com.example.stockproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.stockproject to javafx.fxml;
    exports com.example.stockproject;
}