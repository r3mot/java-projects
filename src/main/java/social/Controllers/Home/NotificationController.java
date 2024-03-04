package social.Controllers.Home;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import social.Database.Newer.FriendshipManager;
import social.Objects.NotificationPane;
import social.Objects.User;

public class NotificationController implements Initializable {

    @FXML private AnchorPane notificationPane;
    @FXML private Button refresh;

    private ArrayList<NotificationPane> friendRequests;
    private FriendshipManager friendshipManager;

    private NotificationPane notification;
    private User user;

    private int layoutY;
    private int layoutX;

    @FXML
    void refreshNotifications(ActionEvent event) {
  
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        friendshipManager = new FriendshipManager();
        friendRequests = friendshipManager.getFriendRequests();

        loadNotifications();
        
    }

    private void loadNotifications(){

        layoutY = 20;
        layoutX = 20;

        for(int index = 0; index < friendRequests.size(); index++){

            notification = friendRequests.get(index);
            user = notification.getUser();

            createNotification();
            setButtonAction(index);
            addToAnchor();

        }
    }

    private void createNotification(){

        notification = new NotificationPane(user);
        notification.setY(layoutY);
        notification.setX(layoutX);

    }


    private void setButtonAction(int index){

        notification.addUserButton().setOnAction(e -> {

            friendshipManager.acceptFriendRequest(user.getUsername());
            friendRequests.remove(index);

            notificationPane.getChildren().clear();
            loadNotifications();

        });
        
    }

    private void addToAnchor(){

        notificationPane.getChildren().addAll(notification);
        layoutY += notification.getPaneHeight() + 20;

    }

}
