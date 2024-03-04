package social.Controllers.Home;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.Database.Newer.Database;
import social.Objects.CurrentUser;
import social.Objects.FeedPane;

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
    @FXML private Button refresh;

    private Database db;
    private int yPostion;


    /**
     * Start of Controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        db = new Database();
        displayProfile();
        
    }


    @FXML
    void refreshPosts(ActionEvent event){
        postsAnchor.getChildren().clear();
        yPostion = 0;
        initFeed();
    }


    /**
     * Displays all profile data
     * Calls the helper methods below
     */
    private void displayProfile(){

        initFeed();
        initAbout();

    }

    /**
     * Display all user's posts
     */
    private void initFeed(){


        ArrayList<FeedPane> feed = db.getUserFeed();
        FeedPane post;

        for(int i = feed.size()-1; i >= 0; i--){

            post = feed.get(i);
            post.setLayoutY(yPostion);
            post.changeWidth(680);

            postsAnchor.getChildren().addAll(post);
            yPostion += post.getPaneHeight() + 2;

        }

    }

    /**
     * Display about me page
     */
    private void initAbout(){

        this.profilePicture.setFill(new ImagePattern(new Image(CurrentUser.getPictureUrl())));
        this.firstname.setText(CurrentUser.getFirstName());
        this.lastname.setText(CurrentUser.getLastName());
        this.major.setText(CurrentUser.getMajor());
        this.standing.setText(CurrentUser.getStanding());
        this.year.setText(CurrentUser.getGradYear());
        this.job.setText(CurrentUser.getDreamJob());

    
    }

}

