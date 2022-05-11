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
import social.Database.LocalStorage.User.FeedData;
import social.Database.LocalStorage.User.ProfileData;
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

    private ProfileData profileData;
    private FeedData feedData;
    private int yPostion;


    /**
     * Start of Controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            profileData = new ProfileData();
            feedData = new FeedData();
        } catch (SQLException e) { 
            Flag.DEBUG(e.getCause().toString()); 
        }
        
        displayProfile();

        CurrentUser.setName(profileData.getFirstName(), profileData.getLastName());
        
    }

    /**
     * Displays all profile data
     * Calls the helper methods below
     */
    private void displayProfile(){

        initFeed();
        initAbout();
        initFriends();
        addPicture(CurrentUser.imageURL);
    }

    private void initFeed(){


        Post userFeed;

        for(int i = feedData.getNumPosts()-1; i >= 0; i--){

            String name = feedData.getPostName(i);
            String content = feedData.getPostContent(i);
            String imageURL = feedData.getPostImage(i);
            String date = feedData.getPostDate(i);

            userFeed = new Post(name, content, imageURL, date, yPostion);
            postsAnchor.getChildren().addAll(userFeed);

            yPostion += userFeed.getPrefHeight() + 2;

            CurrentUser.setImage(imageURL);
            
        }

    }

    private void initAbout(){

        this.profilePicture.setFill(new ImagePattern(new Image(CurrentUser.imageURL)));
        this.firstname.setText(profileData.getFirstName());
        this.lastname.setText(profileData.getLastName());
        this.major.setText(profileData.getMajor());
        this.standing.setText(profileData.getStanding());
        this.year.setText(profileData.getYear());
        this.job.setText(profileData.getDreamJob());


    }

    private void initFriends(){

    }

    private void addPicture(String url){
        profilePicture.setFill(new ImagePattern(new Image(url)));
    }
}

