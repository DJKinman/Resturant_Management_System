module com.example.resturant_management_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.resturant_management_system to javafx.fxml;
    exports com.example.resturant_management_system;
}