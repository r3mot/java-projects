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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    /**
     * 
     * @param event
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws SQLException
     * 
     * User clicked enter after entering their password
     * Attempt login
     */
    @FXML 
    void enter(KeyEvent event) throws ClassNotFoundException, IOException, SQLException {
        if(event.getCode().equals(KeyCode.ENTER)){
            login(null);
        }
    }
    
    /**
     * 
     * @param event
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * If successful login (valid credentials & non-empty text fields)
     * Execute the login process
     */
    @FXML
    void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        if(success()){
            execLogin();
        }

        showError();
    }

    /**
     * 
     * @throws IOException
     * 
     * Switch scene
     * Set current user's username to be used throughout program
     */
    private void execLogin() throws IOException{

        CurrentUser.setUsername(username.getText());
        App.setRoot("Home");

    }


    /**
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * Sanity check
     */
    private boolean success() throws ClassNotFoundException, SQLException{

        boolean result = credentials.userLogin(username.getText(), password.getText());
        if(!result){
            showError();
        }
        return result;
    }


    /**
     * Shows error TextField when user enters
     * incorrect username / password
     * or when the user does not fill out
     * the textfields 
     */
    private void showError(){
        loginError.setText("Invalid Username or Password");
    }

}