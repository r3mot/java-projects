package social.Controllers.Login;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class CreateUserController implements Initializable{

    @FXML private TextField dreamjob;
    @FXML private TextField firstname;
    @FXML private TextField lastname;
    @FXML private TextField major;
    @FXML private TextField password;
    @FXML private TextField standing;
    @FXML private TextField username;
    @FXML private TextField year;
    private Image profilepicture;


    
    /**
     * Start of controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    /**
     * 
     * @param event
     * 
     * Create a new user account
     */
    @FXML
    void create(ActionEvent event) {

        if(inputValid()){
            // create user
        }
    }


    /**
     * 
     * @param event
     * 
     * Allows user to upload profile picture on account creation
     * Currently allows jpg and png files
     */
    @FXML
    void uploadImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            this.profilepicture = new Image(file.toURI().toString()); 
        }
        
    }

    /**
     * 
     * @return valid input
     * 
     * Sanitizes user input to ensure critea is not null
     */
    private boolean inputValid(){
        return !(firstname.getText().isEmpty() || lastname.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty());
    }
}
