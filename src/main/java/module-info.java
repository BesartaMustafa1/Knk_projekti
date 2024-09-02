module com.example.menaxhimibiblotekes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    opens Controllers to javafx.fxml;
    opens Model to javafx.base;
    opens com.example.menaxhimibiblotekes to javafx.fxml;
    exports com.example.menaxhimibiblotekes;
    exports app;
    exports Model;

}