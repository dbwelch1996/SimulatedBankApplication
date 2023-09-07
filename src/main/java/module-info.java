module com.example.bankgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.bankgui to javafx.fxml;
    exports com.example.bankgui;
}