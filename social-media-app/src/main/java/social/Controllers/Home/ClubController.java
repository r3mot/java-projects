package social.Controllers.Home;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.Database.Newer.Database;
import social.Objects.Club;
import social.Objects.ClubPane;

public class ClubController implements Initializable {

    @FXML private AnchorPane allClubsPane;
    @FXML private AnchorPane clubProfilePane;
    @FXML private Label contact;
    @FXML private AnchorPane contentPane;
    @FXML private Label email;
    @FXML private Label name;
    @FXML private Circle picture;
    @FXML private Label purpose;
    @FXML private SplitPane split;
    @FXML private Label website;
    @FXML private Button refresh;

    private Database db;
    private ArrayList<ClubPane> clubs;
    private int layoutY;
    private int layoutX;

    @FXML
    void goBack(ActionEvent event) {
        split.setDividerPosition(0, 99.63);
    }

    @FXML
    void refreshClubs(ActionEvent event){
        displayClubs();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        db = new Database();

        displayClubs();
        split.setDividerPosition(0, 99.63);

    }

    private void displayClubs(){

        layoutY = 20;
        layoutX = 20;

        clubs = db.getUserClubs();
        
        for(int i = 0; i < clubs.size(); i++){

            ClubPane club = clubs.get(i);

            setButtonAction(club);

            club.setY(layoutY);
            club.setX(layoutX);
            contentPane.getChildren().addAll(club);
            layoutY += 135;
            
        }
    }

    private void setButtonAction(ClubPane club){
        club.viewClubProfile().setOnAction(e ->{
            displayClubProfile(club.getClub());
        });
    }

    private void displayClubProfile(Club club){

        split.setDividerPosition(0, 0);

        name.setText(club.getName());
        purpose.setText(club.getPurpose());
        contact.setText(club.getMainContact());
        email.setText(club.getEmail());
        website.setText(club.getWebsite());
        picture.setFill(new ImagePattern(new Image(club.getIcon())));
    }

}