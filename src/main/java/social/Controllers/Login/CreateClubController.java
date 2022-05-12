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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import social.Database.Database;
import social.Objects.Club;

public class CreateClubController  implements Initializable {

    @FXML private Button done;
    @FXML private TextField email;
    @FXML private TextField mainContact;
    @FXML private TextField name;
    @FXML private TextField password;
    @FXML private TextField purpose;
    @FXML private TextField website;
    @FXML private TextField logo;

    private Image clubIcon;
    private List<TextField> textFields;
    private PseudoClass error;

    private Database db;
    private Club club;

    @FXML
    void create(ActionEvent event) throws SQLException {

        db = new Database();
        if(inputValid()){
            this.club = new Club
            (
                name.getText(),
                purpose.getText(),
                mainContact.getText(),
                website.getText(),
                email.getText(),
                password.getText(),
                clubIcon.getUrl()
            );
            // db.createClub(this.club);
        }

        for(TextField fields : textFields){
            fields.clear();
        }
    }

    @FXML
    void uploadImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            this.clubIcon = new Image(file.toURI().toString()); 
        }

        String fileName = file.getName();
        logo.setText(fileName);
        
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

            textFields = Arrays.asList(name, purpose, mainContact, website, email, password);

            error = PseudoClass.getPseudoClass("error");
            
            for(TextField tf : textFields){
                tf.setOnKeyTyped(e ->{
                    tf.pseudoClassStateChanged(error, false);
                });
                tf.pseudoClassStateChanged(error, false);
            }
            
        }



}