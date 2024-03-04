package social.Controllers.Home;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import social.Database.Newer.FriendshipManager;
import social.Objects.ExploreClub;
import social.Objects.ExploreUser;

public class ExploreController implements Initializable {

    @FXML private AnchorPane contentPane;
    @FXML private AnchorPane contentPane1;
    @FXML private SplitPane split;
    @FXML private Pane studentPane;
    @FXML private Pane clubPane;
    @FXML private Button students;

    private int layoutY;

    private FriendshipManager friendshipManager;

    private ArrayList<ExploreUser> users;
    private ArrayList<ExploreClub> clubs;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        friendshipManager = new FriendshipManager();

        users = friendshipManager.getExploreStudents();
        clubs = friendshipManager.getExploreClubs();

        loadUsers();
        loadClubs();
        
    }

    private void loadUsers(){

        layoutY = 20;

        System.out.println(users.size());
        for(int index = 0; index < users.size(); index++) {

            ExploreUser user = users.get(index);
            user.setY(layoutY);

            setUserAddClicked(user, index);
            
            studentPane.getChildren().addAll(user);
            layoutY += user.getPaneHeight() + 20;

        }


    }

    private void loadClubs(){

        layoutY = 20;

        for(int index = 0; index < clubs.size(); index++) {

            ExploreClub club = clubs.get(index);
            club.setY(layoutY);

            setClubJoinClicked(club, index);

            clubPane.getChildren().addAll(club);
            layoutY += club.getPaneHeight() + 20;

        }

    }


    private void setUserAddClicked(ExploreUser user, int index){
        user.addUserButton().setOnAction(e -> {
            friendshipManager.sendFriendRequest(user.getUser().getUsername());
            layoutY = 20;
            users.remove(index);
            studentPane.getChildren().clear();
            loadUsers();

        });
    }

    private void setClubJoinClicked(ExploreClub club, int index){
        club.joinClub().setOnAction(e -> {
            friendshipManager.joinClub(club.getEmail());
            layoutY = 20;
            clubs.remove(index);
            clubPane.getChildren().clear();
            loadClubs();
        });
    }

}