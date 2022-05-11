package social.Objects;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Post extends Pane {
    
    private int PREF_WIDTH = 738;
    private int PREF_HEIGHT = 163;

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
        contentTA.setPrefWidth(433);
        contentTA.setPrefHeight(87);
        contentTA.setLayoutX(277);
        contentTA.setLayoutY(55);
        contentTA.setDisable(true);
        contentTA.setWrapText(true);
        contentTA.setText(this.content);

    }

    private void setDate(){

        dateLabel = new Label();
        dateLabel.setId("post-date-label");
        dateLabel.setPrefWidth(100);
        dateLabel.setPrefHeight(17);
        dateLabel.setLayoutX(141);
        dateLabel.setLayoutY(90);
        dateLabel.setText(this.date);

    }

    private void setName(){

        nameLabel = new Label();
        nameLabel.setId("post-name-label");
        nameLabel.setPrefWidth(136);
        nameLabel.setPrefHeight(20);
        nameLabel.setLayoutX(141);
        nameLabel.setLayoutY(63);
        nameLabel.setText(this.name);
    }

    private void setImage(){
        
       picture = new Circle();
       picture.setRadius(41);
       picture.setLayoutX(79);
       picture.setLayoutY(83);
       picture.setFill(new ImagePattern(new Image(this.imageURL)));

    }

    public String getPostName(){
        return this.name;
    }

    public String getPostContent(){
        return this.content;
    }

    public String getPostURL(){
        return this.imageURL;
    }

    public String getPostDate(){
        return this.date;
    }
}
