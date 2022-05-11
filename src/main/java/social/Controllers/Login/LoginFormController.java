package social.Controllers.Login;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import social.App;
import social.Database.CredentialManager;
import social.Objects.CurrentUser;

public class LoginFormController {

    @FXML private Hyperlink forgotPasswordLink;
    @FXML private Button loginButton;
    @FXML private PasswordField password;
    @FXML private TextField username;
    @FXML private Label loginError;

    private CredentialManager credentials = new CredentialManager();


    @FXML
    void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        if(success()){
            execLogin();
        }

        showError();
    }

    private void execLogin() throws IOException{

        CurrentUser.setUsername(username.getText());
        App.setRoot("Home");

    }


    private boolean success() throws ClassNotFoundException, SQLException{

        boolean result = credentials.login(username.getText(), password.getText());
        if(!result){
            showError();
        }
        return result;
    }


    private void showError(){
        loginError.setText("Invalid Username or Password");
    }

}