package social.Controllers.Login;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import social.Database.Newer.Database;
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
    @FXML private TextField picutreUrl;
    @FXML private Button done;

    private String url;

    private Image profilepicture;
    private List<TextField> textFields;
    private PseudoClass error;

    // private Database db;
    // private User user;
    Database db;


    /**
     * 
     * @param event
     * 
     * Create a new user account
     * @throws SQLException
     */
    @FXML
    void create(ActionEvent event) throws SQLException {

        User user;
        if(inputValid()){
            user = new User
            (
                username.getText(), 
                password.getText(),
                firstname.getText(),
                lastname.getText(), 
                major.getText(), 
                standing.getText(), 
                year.getText(), 
                dreamjob.getText(), 
                url
            );

            db.createUser(user);
            
        }
        else{
            System.out.println("Nope");
        }

        for(TextField fields : textFields){
            fields.clear();
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

        File file = db.uploadImage();
        url = file.toURI().toString();
        if (file != null) {
            this.profilepicture = new Image(file.toURI().toString()); 
        }
        
        String fileName = file.getName();
        picutreUrl.setText(fileName);
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

        db = new Database();
        textFields = Arrays.asList(firstname, lastname,major, standing, year, dreamjob, username, password, picutreUrl);

        error = PseudoClass.getPseudoClass("error");
        
        for(TextField tf : textFields){
            tf.setOnKeyTyped(e ->{
                tf.pseudoClassStateChanged(error, false);
            });
            tf.pseudoClassStateChanged(error, false);
        }
    }
}
