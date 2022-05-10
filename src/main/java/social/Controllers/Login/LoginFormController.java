package social.Controllers.Login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import social.App;
import social.Objects.CurrentUser;

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
     * @throws IOException
     */
    @FXML
    void login(ActionEvent event) throws IOException {

        boolean login = true;
        process(login);

    }

    /**
     * 
     * @param success
     * 
     * Validates user input
     * @throws IOException
     */
    private void process(boolean success) throws IOException{

        if(success)
        {
            CurrentUser.setUsername(username.getText());
            App.setRoot("Home");
        }
    }

}