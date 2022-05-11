package social.Controllers.Home;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import social.Database.Database;
import social.Objects.Friend;
import social.Objects.User;

public class FriendController implements Initializable {

    @FXML private AnchorPane contentPane;

    private Database db;
    private ArrayList<User> friends;
    private int currentY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // db = new Database();

        // try {
        //     displayFriends();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        
    }


    private void displayFriends() throws SQLException{

        friends = db.getFriends();
        Friend friend;

        for(int i = 0; i < friends.size(); i++){

            String name = friends.get(i).getFirstName() + " " + friends.get(i).getLastName();
            String username = friends.get(i).getUsername();
            String url = friends.get(i).getImage().getUrl();
            friend = new Friend(name, username, url,0 , currentY);

            contentPane.getChildren().add(friend);
            currentY += 135;
        }

    }
}
