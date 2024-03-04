module library {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens library.Controllers to javafx.fxml;

    exports library;
}
