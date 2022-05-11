package social.Controllers.Login;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import social.Database.Database;
import social.Objects.User;

public class CreateUserController implements Initializable {

    @FXML private TextField dreamjob;
    @FXML private TextField firstname;
    @FXML private TextField lastname;
    @FXML private TextField major;
    @FXML private TextField password;
    @FXML private TextField standing;
    @FXML private TextField username;
    @FXML private TextField year;
    @FXML private Button done;

    private Image profilepicture;
    private List<TextField> textFields;
    private PseudoClass error;

    private Database db;
    private User user;


    /**
     * 
     * @param event
     * 
     * Create a new user account
     * @throws SQLException
     */
    @FXML
    void create(ActionEvent event) throws SQLException {

        db = new Database();
        if(inputValid()){
            this.user = new User
            (
                firstname.getText(),
                lastname.getText(), 
                major.getText(), 
                standing.getText(), 
                year.getText(), 
                dreamjob.getText(), 
                profilepicture, 
                username.getText(), 
                password.getText()
            );

            db.createUser(this.user);
        }
        else{
            System.out.println("Nope");
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

        boolean valid = true;

        for(TextField tf : textFields){
            if(tf.getText().isEmpty()){
                valid = false;
                tf.pseudoClassStateChanged(error, true);
            }
        }

        return valid;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textFields = Arrays.asList(firstname, lastname,major, standing, year, dreamjob, username, password);

        error = PseudoClass.getPseudoClass("error");
        
        for(TextField tf : textFields){
            tf.setOnKeyTyped(e ->{
                tf.pseudoClassStateChanged(error, false);
            });
            tf.pseudoClassStateChanged(error, false);
        }
    }
}
