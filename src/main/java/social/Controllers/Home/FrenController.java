package social.Controllers.Home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import social.App;
import social.Database.Database;
import social.Debug.Flag;
import social.Objects.FriendPane;

public class FrenController implements Initializable {

    @FXML private AnchorPane contentPane;
    @FXML private AnchorPane allFriendsPane;

    private Database db;
    private ArrayList<FriendPane> friends;

    @FXML private Label clubs;
    @FXML private Circle icon;
    @FXML private Label job;
    @FXML private Label major;
    @FXML private Label name;
    @FXML private Label standing;
    @FXML private Label year;

    private int leftX = 100;
    private int leftY = 30;
    private int rightX = 402;
    private int rightY = 30;

    private Pane friendPane;
    private Pane allFriendPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        db = new Database();

        try {
            displayFriends();
        } catch (SQLException e) {
            Flag.DEBUG(e.getCause().toString());
        }

        try {
            friendPane = FXMLLoader.load(App.class.getResource("FriendProfile.fxml"));
            //allFriendPane = FXMLLoader.load(App.class.getResource("AllFriends.fxml"));
        } catch (IOException e) {
            Flag.DEBUG(e.getCause().toString());
        }
        
    }


    @FXML
    void goBack(ActionEvent event) throws SQLException{
        contentPane.getChildren().clear();
        displayFriends();
    }


    private void displayFriendProfile(String username) throws SQLException{
        contentPane.getChildren().clear();

        ArrayList<String> friend = db.getUser(username);
        name.setText(friend.get(0) + " " + friend.get(1));
        major.setText(friend.get(2));
        standing.setText(friend.get(3));
        year.setText(friend.get(4));
        job.setText(friend.get(5));
        clubs.setText(friend.get(6));

        contentPane.getChildren().add(friendPane);
    }

    private void displayFriends() throws SQLException{

        friends = db.getFriends();

        for(int i = 0; i < friends.size(); i++){

            FriendPane friend = friends.get(i);
            friend.getClicked().setOnMouseClicked(e -> {
                try {
                    displayFriendProfile(friend.getUsername());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            if(i % 2 == 0){
                friend.setX(leftX);
                friend.setY(leftY);
                allFriendPane.getChildren().add(friend);
                leftY += 250;
            }
            else{
                friend.setX(rightX);
                friend.setY(rightY);
                allFriendPane.getChildren().add(friend);
                rightY += 250;
            }
        }

        contentPane.getChildren().add(allFriendPane);

    }

}
