module social {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens social.Controllers.Login to javafx.fxml;
    opens social.Controllers.Home to javafx.fxml;
    exports social;
}
