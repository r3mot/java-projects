package social.Controllers.Home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class ProfileController implements Initializable {

    @FXML private AnchorPane friendAnchor;
    @FXML private AnchorPane postsAnchor;

    @FXML private Label clubs;
    @FXML private Label firstname;
    @FXML private Label job;
    @FXML private Label lastname;
    @FXML private Label major;
    @FXML private Label standing;
    @FXML private Label year;

    @FXML private Circle profilePicture;


    /**
     * Start of Controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

    @FXML
    void displayAbout(ActionEvent event) {

    }

    @FXML
    void displayFriends(ActionEvent event) {

    }

    @FXML
    void displayPosts(ActionEvent event) {

    }




}
