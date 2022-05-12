package social.Objects;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import social.Database.Database;

public class ClubPane extends Pane {

    private Label name_label;
    private Label purpose_label;
    private Circle club_icon;
    private Button join_club;
    private Club club;


    /**
     * 
     * @param name 
     * @param purpose
     * @param url
     * 
     * Creates a Club object, containing necessary information
     * to display a club on the home screen
     * 
     * Defines a pane with specific parameters
     * 
     * User has the ability to join the club 
     * as defined by "join_club"
     */
    public ClubPane(Club club){

        this.club = club;

        name_label = new Label();
        purpose_label = new Label();
        club_icon = new Circle();
        join_club = new Button();

        setPrefWidth(660); 
        setPrefHeight(107); 
        setId(club.getEmail());

        setName(name_label);
        setPurpose(purpose_label);
        setIcon(club_icon);
        setButton(join_club);

        getChildren().addAll(name_label, purpose_label, club_icon, join_club);
        //name_label, purpose_label, club_icon, join_club

    }

    private void setButton(Button join){
        join.prefWidth(45);
        join.prefWidth(25);
        join.setLayoutX(600);
        join.setLayoutY(10);
        join.setId(this.club.getEmail());
        join.setText("JOIN");
        join.setTextFill(Color.WHITE);
        join.setStyle("-fx-background-color: #424d57;");
        join.setOnAction(event -> {
            try {
                clicked(event);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    private void setName(Label name){
        name.prefWidth(354);
        name.prefHeight(17);
        name.setLayoutX(125);
        name.setLayoutY(22);
        name.setWrapText(true);
        name.setText(this.club.getName());
        name.setStyle("-fx-font-weight: bold");
    }

    private void setPurpose(Label purpose){
        purpose.prefWidth(50);
        purpose.prefHeight(100);
        purpose.setLayoutX(125);
        purpose.setLayoutY(55);
        purpose.setText(this.club.getPurpose());
        purpose.setWrapText(true);
        purpose.setTextAlignment(TextAlignment.JUSTIFY);
        purpose.setStyle("-fx-font-size: 9;");
    }

    private void setIcon(Circle icon){
        icon.setLayoutX(64);
        icon.setLayoutY(55);
        icon.setRadius(25);
        icon.setStroke(Color.BLACK);
        icon.setStrokeWidth(2);

        icon.setFill(new ImagePattern(new Image(this.club.getIcon())));
    }

    public Pane getClub(){
        return this;
    }

    public void clicked(ActionEvent event) throws SQLException{
        Database db = new Database();
        db.addClub(this.getId(), CurrentUser.username);
    }


    public Button getJoin(){
        return this.join_club;
    }

    public void setY(int y){
        setLayoutY(y);
    }

}