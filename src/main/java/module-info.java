module midterm {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens library.Controllers to javafx.fxml;

    exports library;
}
