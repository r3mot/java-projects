package social.Controllers.Home;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import social.Database.Database;
import social.Database.LocalStorage.GlobalFeedData;
import social.Debug.Flag;
import social.Objects.CurrentUser;
import social.Objects.Post;

public class FeedController implements Initializable {
    
    @FXML private Button createPost;
    @FXML private AnchorPane feedPane;

    private GlobalFeedData data;
    private Post feed;
    private int yPosition;

    private boolean maxMet;
    private String content;

    @FXML
    void post(ActionEvent event) {

        displayPopup();

        data.addPost(new Post(CurrentUser.name, 
                                content, 
                                CurrentUser.imageURL, 
                                getDate(), 
                                0));

        initFeed();                        
        
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

        yPosition = 0;
        feedPane.getChildren().clear();

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


    private void displayPopup(){

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Create A Post");
        alert.setHeaderText("Share Your Thoughts!");

        Label maxCharsMet = new Label();
        maxCharsMet.setText("Max Characters is 120!");
        maxCharsMet.setTextFill(Color.RED);
        maxCharsMet.setVisible(false);

        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // add max character event handler
        textArea.addEventFilter(KeyEvent.KEY_TYPED, maxChars(120));

        if(maxMet){
            maxCharsMet.setVisible(true);
        }
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.getDialogPane().setExpanded(true);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            content = textArea.getText();
            return;
        }

    }

    private EventHandler<KeyEvent> maxChars(final Integer i){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent k){
                TextArea tx = (TextArea) k.getSource();
                if(tx.getText().length() >= i){
                    maxMet = true;
                    k.consume();
                }
            }
        };
    }

    private String getDate(){
        String format = "MM-dd-yyy";
        SimpleDateFormat simpledate = new SimpleDateFormat(format);
        return simpledate.format(new Date());
    }
    
}
