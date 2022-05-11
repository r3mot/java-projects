package social.Controllers.Home;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.Database.LocalStorage.UserData;
import social.Debug.Flag;
import social.Objects.CurrentUser;
import social.Objects.Post;

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

    private UserData userData;
    private Post userFeed;
    private int yPostion;


    /**
     * Start of Controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            userData = new UserData();
        } catch (SQLException e) { 
            Flag.DEBUG(e.getCause().toString()); 
        }
        
        displayProfile();

        CurrentUser.setName(userData.getFirstName(), userData.getLastName());
        
    }

    private void displayProfile(){

        initFeed();
        initAbout();
        initFriends();
        addPicture(CurrentUser.imageURL);
    }

    private void initFeed(){

        String name="";
        String content="";
        String imageURL="";
        String date="";

        for(int i = 0; i < userData.getNumPosts(); i++){

            name = userData.getPostName(i);
            content = userData.getPostContent(i);
            imageURL = userData.getPostImage(i);
            date = userData.getPostDate(i);

            userFeed = new Post(name, content, imageURL, date, yPostion);
            postsAnchor.getChildren().addAll(userFeed);

            yPostion += userFeed.getPrefHeight() + 2;
            
        }

        CurrentUser.setImage(imageURL);

    }

    private void initAbout(){

        // addPicture("");

        firstName(userData.getFirstName());
        lastName(userData.getLastName());
        major(userData.getMajor());
        standing(userData.getStanding());
        year(userData.getYear());
        dreamJob(userData.getDreamJob());


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

