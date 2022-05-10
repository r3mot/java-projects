package social.Controllers.Home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ProfileController implements Initializable {

    @FXML private AnchorPane friendAnchor;
    @FXML private AnchorPane postsAnchor;

    @FXML private Tab postTab;
    @FXML private Tab aboutTab;
    @FXML private Tab friendsTab;

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
        
        displayProfile();
        
    }

    private void displayProfile(){

        initFeed();
        initAbout();
        initFriends();
    }

    private void initFeed(){

    }

    private void initAbout(){

        // addPicture("");

        firstName("");
        lastName("");
        major("");
        standing("");
        year("");
        dreamJob("");


    }

    private void initFriends(){

    }

    private void firstName(String first){
        this.firstname.setText(first);
    }

    private void lastName(String last){
        this.lastname.setText(last);
    }

    private void major(String major){
        this.major.setText(major);
    }

    private void standing(String standing){
        this.standing.setText(standing);
    }

    private void year(String year){
        this.year.setText(year);
    }

    private void dreamJob(String job){
        this.job.setText(job);
    }

    private void addPicture(String url){
        profilePicture.setFill(new ImagePattern(new Image(url)));
    }
}

