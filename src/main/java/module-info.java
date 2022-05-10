module social {
    requires javafx.controls;
    requires javafx.fxml;

    opens social.Controllers.Login to javafx.fxml;
    exports social;
}
