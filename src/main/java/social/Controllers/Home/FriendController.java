package social.Controllers.Home;


import java.net.URL;
import java.sql.SQLException;
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
import social.Database.Database;
import social.Debug.Flag;
import social.Objects.FriendPane;

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

    private int leftX = 100;
    private int leftY = 30;
    private int rightX = 402;
    private int rightY = 30;


    /**
     * Start of controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = new Database();

        try {
            displayFriends();
        } catch (SQLException e) {
            Flag.DEBUG(e.getCause().toString());
        }
        
    }

    /**
     * 
     * @param event return to friends list
     */
    @FXML
    void goBack(ActionEvent event) {
        split.setDividerPosition(0, 99.63);
    }

    /**
     * 
     * @throws SQLException
     * 
     * Show friends list
     */
    private void displayFriends() throws SQLException{

        friends = db.getFriends();

        for(int i = 0; i < friends.size(); i++){

            FriendPane friend = friends.get(i);
            friend.getClicked().setOnMouseClicked(e -> {
                try {
                    displayFriendProfile(friend.getUsername());
                } catch (SQLException e1) {
                    Flag.DEBUG(e1.getCause().toString());
                }
            });

            // Left Side
            if(i % 2 == 0){
                friend.setX(leftX);
                friend.setY(leftY);
                allFriendsPane.getChildren().add(friend);
                leftY += 250;
            }
            // Right Side
            else{
                friend.setX(rightX);
                friend.setY(rightY);
                allFriendsPane.getChildren().add(friend);
                rightY += 250;
            }
        }

        contentPane.getChildren().add(allFriendsPane);

    }

    /**
     * 
     * @param username of friend
     * @throws SQLException
     * 
     * Show friends profile
     */
    private void displayFriendProfile(String username) throws SQLException{
        
        split.setDividerPosition(0, 0);

        ArrayList<String> friend = db.getUser(username);
        name.setText(friend.get(0) + " " + friend.get(1));
        major.setText(friend.get(2));
        standing.setText(friend.get(3));
        year.setText(friend.get(4));
        job.setText(friend.get(5));
        picture.setFill(new ImagePattern(new Image(friend.get(6))));

    }

}