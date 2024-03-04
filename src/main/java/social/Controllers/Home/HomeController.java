package social.Controllers.Home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import social.App;

public class HomeController implements Initializable {

    @FXML private Button clubs;
    @FXML private Pane contentPane;
    @FXML private Button feed;
    @FXML private Button friends;
    @FXML private Pane leftPane;
    @FXML private Button profile;
    @FXML private Button notifications;
    @FXML private Button explore;

    private Pane profilePane;
    private Pane feedPane;
    private Pane clubPane;
    private Pane friendPane;
    private Pane explorePane;
    private Pane notificationPane;


    /**
     * 
     * Start of controller class
     * Initialize fxml files for dashboard
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try{
        
            profilePane = FXMLLoader.load(App.class.getResource("Profile.fxml"));
            feedPane = FXMLLoader.load(App.class.getResource("Feed.fxml"));
            explorePane = FXMLLoader.load(App.class.getResource("Explore.fxml"));
            notificationPane = FXMLLoader.load(App.class.getResource("Notifications.fxml"));
            clubPane = FXMLLoader.load(App.class.getResource("Clubs.fxml"));
            friendPane = FXMLLoader.load(App.class.getResource("Friends.fxml"));

            contentPane.getChildren().add(profilePane);

        }catch(IOException ioe) {
            System.out.println("Error ( " + ioe.getCause().getLocalizedMessage() + " )");
        }
        
    }

    /**
     * 
     * @param event user logged out
     * @throws IOException
     * 
     * Switches root to Login
     */
    @FXML
    void logout(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    /**
     * 
     * @param navButton navigation button clicked
     * 
     * Allows user to navigate the app
     * through the main dashboard
     */
    @FXML
    void navigate(ActionEvent navButton) {

        switch(sourceOf(navButton)){

            case "profile" : displayProfile(); break;
            case "feed" : displayFeed(); break;
            case "clubs" : displayClubs(); break;
            case "friends" : displayFriends(); break;
            case "explore" : displayExplorer(); break;
            case "notifications" : displayNotifications(); break;

        }

    }

    /**
     * 
     * @param event
     * @return navigation button fx:id
     */
    private String sourceOf(ActionEvent event){
        Button clicked = (Button) event.getSource();
        return clicked.getId();
    }

    private void displayNotifications(){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(notificationPane);
    }

    private void displayExplorer(){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(explorePane);
    }

    /**
     * Add profilePane to contentPane
     */
    private void displayProfile(){

        contentPane.getChildren().clear();
        contentPane.getChildren().add(profilePane);

    }

    /**
     * Add feedPane to contentPane
     */
    private void displayFeed(){

        contentPane.getChildren().clear();
        contentPane.getChildren().add(feedPane);

    }

    /**
     * Add clubsPane to contentPane
     */
    private void displayClubs(){

        contentPane.getChildren().clear();
        contentPane.getChildren().add(clubPane);

    }

    /**
     * Add friendPane to contentPane
     */
    private void displayFriends(){

        contentPane.getChildren().clear();
        contentPane.getChildren().add(friendPane);

    }
}