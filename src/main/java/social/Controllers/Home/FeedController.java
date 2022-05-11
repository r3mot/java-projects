package social.Controllers.Home;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import social.Database.Database;
import social.Database.LocalStorage.GlobalFeedData;
import social.Debug.Flag;
import social.Objects.Post;

public class FeedController implements Initializable {
    
    @FXML private Button createPost;
    @FXML private AnchorPane feedPane;

    private GlobalFeedData data;
    private Post feed;
    private int yPosition;

    @FXML
    void post(ActionEvent event) {

        
    }

    /**
     * Start of controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            data = new GlobalFeedData();
        } catch (SQLException e) {
            Flag.DEBUG(e.getCause().toString());
        }
        
        initFeed();
    }

    /**
     * Displays the global feed on the dashboard
     * Updates when user submits a new post
     */
    private void initFeed(){

        for(int i = 0; i < data.getNumPosts(); i++){

            String name = data.getPostName(i);
            String content = data.getPostContent(i);
            String imageURL = data.getPostImage(i);
            String date = data.getPostDate(i);

            feed = new Post(name, content, imageURL, date, yPosition);
            feedPane.getChildren().addAll(feed);

            yPosition += feed.getPrefHeight() + 2;
        }
    }
}
