package social.Controllers.Home;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.Database.Newer.Database;
import social.Objects.FriendPane;
import social.Objects.User;

public class FriendController implements Initializable {

    @FXML private AnchorPane allFriendsPane;
    @FXML private AnchorPane friendProfilePane;
    @FXML private AnchorPane contentPane;
    @FXML private Label clubs;
    @FXML private Label job;
    @FXML private Label major;
    @FXML private Label name;
    @FXML private Label standing;
    @FXML private Label year;
    @FXML private Circle picture;
    @FXML private SplitPane split;

    private Database db;
    private ArrayList<FriendPane> friends;

    private int layoutY;
    private int layoutX;


    /**
     * Start of controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // db = new Database();

        db = new Database();

        displayFriends();
   
        split.setDividerPosition(0, 99.63);
        
    }

    /**
     * 
     * @param event return to friends list
     */
    @FXML
    void goBack(ActionEvent event) {
        split.setDividerPosition(0, 99.63);
    }

    @FXML
    void refreshFriendsList(ActionEvent event){
        allFriendsPane.getChildren().clear();
        displayFriends();
    }

    private void displayFriends(){

        layoutY = 20;
        layoutX = 20;

        friends = db.getUserFriends();
        
        for(int index = 0; index < friends.size(); index++){
            
            FriendPane friend = friends.get(index);
            friend.setX(layoutX);
            friend.setY(layoutY);

            setButtonAction(friend);

            allFriendsPane.getChildren().addAll(friend);
            layoutY += friend.getPaneHeight() + 20;

        }

    }


    /**
     * 
     * @param username of friend
     * 
     * Show friends profile
     */
    private void displayFriendProfile(User friend) {
        
        split.setDividerPosition(0, 0);
        
        name.setText(friend.getFullName());
        major.setText(friend.getMajor());
        standing.setText(friend.getStanding());
        year.setText(friend.getYear());
        job.setText(friend.getDreamJob());
        picture.setFill(new ImagePattern(new Image(friend.getImage())));

    }

    private void setButtonAction(FriendPane friendPane){
        friendPane.viewProfile().setOnAction(e ->{
            displayFriendProfile(friendPane.getFriend());
        });
    }

}