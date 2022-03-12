module midterm {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens midterm.Controllers to javafx.fxml;
    exports midterm;
}
