package social.Objects;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class FriendPane extends Pane {

    private final String STYLE_CLASS = "explorer-post-object";
    private final String ADD_BUTTON_STYLE_CLASS = "explorer-add-friend-button";
    private final String NAME_STYLE_CLASS = "explorer-name-label";
    private final String IMAGE_STYLE_CLASS = "explorer-user-icon";

    private final int PREF_WIDTH  = 723;
    private final int PREF_HEIGHT = 100; //163

    private Circle iconCircle;
    private Label nameLabel;
    private Button addButton;

    private User user;
    private String fullname;

    public FriendPane(User user) {

        this.user = user;
        this.fullname = this.user.getFirstName() + " " + this.user.getLastName();

        this.setPrefWidth(PREF_WIDTH);
        this.setPrefHeight(PREF_HEIGHT);

        setAddButton();
        setIcon();
        setName();

        this.getStyleClass().add(STYLE_CLASS);
        this.getChildren().addAll(iconCircle, nameLabel, addButton);

    }

    private void setAddButton(){

        addButton = new Button();
        addButton.setPrefWidth(62); //
        addButton.setPrefHeight(35);
        addButton.setLayoutX(582);
        addButton.setLayoutY(34); //59
        addButton.setId(this.user.getUsername());
        addButton.setText("View");
        addButton.getStyleClass().add(ADD_BUTTON_STYLE_CLASS);

    }

    private void setIcon(){

        iconCircle = new Circle();
        iconCircle.setRadius(41); //62
        iconCircle.setLayoutX(79); //96
        iconCircle.setLayoutY(49); //82
        iconCircle.setFill(new ImagePattern(new Image(this.user.getImage())));
        iconCircle.getStyleClass().add(IMAGE_STYLE_CLASS);

    }

    private void setName(){

        nameLabel = new Label();
        nameLabel.setPrefWidth(340); //287
        nameLabel.setPrefHeight(37);
        nameLabel.setLayoutX(168); //218
        nameLabel.setLayoutY(35); //46
        nameLabel.setText(this.fullname + " - " + this.user.getMajor());
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.getStyleClass().add(NAME_STYLE_CLASS);

    }

    public void setX(double x){
        setLayoutX(x);
    }

    public void setY(int y){
        setLayoutY(y);
    }

    public User getFriend(){
        return this.user;
    }

    public String getName(){
        return this.fullname;
    }

    public int getPaneHeight(){
        return this.PREF_HEIGHT;
    }

    public Button viewProfile(){
        return this.addButton;
    }

}
