module social {
    requires javafx.controls;
    requires javafx.fxml;

    opens social to javafx.fxml;
    exports social;
}
