module com.example.stockproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
	requires javafx.graphics;
	requires javafx.base;

    opens com.example.stockproject to javafx.fxml;
    exports com.example.stockproject;
    exports com.example.stockproject.controller;
    opens com.example.stockproject.controller to javafx.fxml;
}