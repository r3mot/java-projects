package social.Objects;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Post extends Pane {
    
    private int PREF_WIDTH = 763;
    private int PREF_HEIGHT = 250;

    private Label dateLabel;
    private Label nameLabel;
    private Circle picture;
    private TextArea contentTA;

    private String name;
    private String content;
    private String imageURL;
    private String date;
    private int current_y;

    public Post(String name, String content, String imageURL, String date, int current_y){

        this.name = name;
        this.content = content;
        this.imageURL = imageURL;
        this.date = date;
        this.current_y = current_y;

        createPost();
    }

    private void createPost(){

        this.setPrefWidth(PREF_WIDTH);
        this.setPrefHeight(PREF_HEIGHT);
        this.setLayoutY(current_y);
    
        setImage();
        setName();
        setDate();
        setContent();

        this.getChildren().addAll(dateLabel, nameLabel, picture, contentTA);
    }

    private void setContent(){

        contentTA = new TextArea();
        contentTA.setId("post-text-area");
        contentTA.setPrefWidth(622);
        contentTA.setPrefHeight(74);
        contentTA.setLayoutX(23);
        contentTA.setLayoutY(85);
        contentTA.setDisable(true);
        contentTA.setWrapText(true);
        contentTA.setText(this.content);

    }

    private void setDate(){

        dateLabel = new Label();
        dateLabel.setId("post-date-label");
        dateLabel.setPrefWidth(160);
        dateLabel.setPrefHeight(17);
        dateLabel.setLayoutX(83);
        dateLabel.setLayoutY(40);
        dateLabel.setText(this.date);

    }

    private void setName(){

        nameLabel = new Label();
        nameLabel.setId("post-name-label");
        nameLabel.setPrefWidth(126);
        nameLabel.setPrefHeight(17);
        nameLabel.setLayoutX(83);
        nameLabel.setLayoutY(19);
        nameLabel.setText(this.name);
    }

    private void setImage(){
        
       picture = new Circle();
       picture.setRadius(27);
       picture.setLayoutX(45);
       picture.setLayoutY(47);
       picture.setFill(new ImagePattern(new Image(this.imageURL)));

    }
}
