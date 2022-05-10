package social;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFormController {

    @FXML private Hyperlink forgotPasswordLink;
    @FXML private Button loginButton;
    @FXML private PasswordField password;
    @FXML private TextField username;

    /**
     * 
     * @param event
     * 
     * Goes to homescreen on successfull login
     */
    @FXML
    void login(ActionEvent event) {

        // TODO: Implement login feature

        boolean login = true;
        process(login);

    }

    /**
     * 
     * @param success
     * 
     * Validates user input
     */
    private void process(boolean success){

        if(success)
        {
            //process login
        }
    }

}