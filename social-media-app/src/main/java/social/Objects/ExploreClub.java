package social.Objects;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ExploreClub extends Pane {

    private final String STYLE_CLASS = "explorer-post-object";
    private final String ADD_BUTTON_STYLE_CLASS = "explorer-add-friend-button";
    private final String NAME_STYLE_CLASS = "explorer-name-label";
    private final String IMAGE_STYLE_CLASS = "explorer-user-icon";

    private final int PREF_WIDTH  = 335;
    private final int PREF_HEIGHT = 115; //163

    private Circle iconCircle;
    private Label nameLabel;
    private Button addButton;

    private Club club;
    
    public ExploreClub(Club club) {

        this.club = club;

        this.setPrefWidth(PREF_WIDTH);
        this.setPrefHeight(PREF_HEIGHT);
        this.setLayoutX(10);

        setAddButton();
        setIcon();
        setName();

        this.getStyleClass().add(STYLE_CLASS);
        this.getChildren().addAll(iconCircle, nameLabel, addButton);

    }

    private void setAddButton(){

        addButton = new Button();
        addButton.setPrefWidth(161);
        addButton.setPrefHeight(25);
        addButton.setLayoutX(137);
        addButton.setLayoutY(58); //59
        addButton.setId(this.club.getEmail());
        addButton.setText("Join");
        addButton.getStyleClass().add(ADD_BUTTON_STYLE_CLASS);

    }

    private void setIcon(){

        iconCircle = new Circle();
        iconCircle.setRadius(42); //62
        iconCircle.setLayoutX(62); //96
        iconCircle.setLayoutY(58); //82
        iconCircle.setFill(new ImagePattern(new Image(this.club.getIcon())));
        iconCircle.getStyleClass().add(IMAGE_STYLE_CLASS);

    }

    private void setName(){

        nameLabel = new Label();
        nameLabel.setPrefWidth(135);
        nameLabel.setPrefHeight(17);
        nameLabel.setLayoutX(150);
        nameLabel.setLayoutY(23); //46
        nameLabel.setText(this.club.getName());
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.getStyleClass().add(NAME_STYLE_CLASS);

    }

    public String getName(){
        return this.club.getName();
    }

    public String getEmail(){
        return this.club.getEmail();
    }

    public String getPurpose(){
        return this.club.getPurpose();
    }

    public String getMainContaction(){
        return this.club.getMainContact();
    }

    public String getWebsite(){
        return this.club.getWebsite();
    }

    public String getIcon(){
        return this.club.getIcon();
    }

    public Club getClub(){
        return this.club;
    }

    public void setY(int y){
        setLayoutY(y);
    }

    public int getPaneHeight(){
        return this.PREF_HEIGHT;
    }

    public Button joinClub(){
        return this.addButton;
    }

}