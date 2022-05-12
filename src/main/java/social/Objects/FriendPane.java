package social.Objects;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class FriendPane extends Pane {

    private Circle icon;
    private Label name_label;
    private Button click;
    private String usernameReturn;
    
    public FriendPane(String name, String username, String url) {

        usernameReturn = username;
        setPrefWidth(255);
        setPrefHeight(133);

        setIcon(url, username);
        setName(name);

        setId("friendBG");
        setStyle("-fx-background-color: #4b5763");

        getChildren().addAll(icon, name_label);

    }

    public void setX(int x){
        setLayoutX(x);
    }

    public void setY(int y){
        setLayoutY(y);
    }

    private void setIcon(String url, String username){
        icon = new Circle();
        icon.setRadius(47);
        icon.setLayoutX(61);
        icon.setLayoutY(67);
        icon.setFill(new ImagePattern(new Image(url)));
        icon.getStyleClass().add("friendImage");
        icon.setId(username);

    }

    private void setName(String name){
        name_label = new Label();
        name_label.setPrefWidth(107);
        name_label.setPrefHeight(17);
        name_label.setLayoutX(128);
        name_label.setLayoutY(52);
        name_label.setText(name);
        name_label.setTextFill(Color.WHITE);
        name_label.setStyle("-fx-font-size: 20;");
    }

    public Circle getClicked(){
        return this.icon;
    }

    public String getUsername(){
        return usernameReturn;
    }
}
