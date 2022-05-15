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
import social.Database.Newer.LoginManager;

public class ClubLoginFormController {

    @FXML private TextField email;
    @FXML private Hyperlink forgotPasswordLink;
    @FXML private Button loginButton;
    @FXML private Label loginError;
    @FXML private PasswordField password;
    

    private LoginManager loginManager = new LoginManager();

    @FXML
    void enter(KeyEvent event) throws ClassNotFoundException, SQLException, IOException {
        if(event.getCode().equals(KeyCode.ENTER)){
            login(null);
        }
    }

    @FXML
    void login(ActionEvent event) throws IOException {

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
    private boolean success() {

        boolean result = loginManager.clubLogin(email.getText(), password.getText());
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