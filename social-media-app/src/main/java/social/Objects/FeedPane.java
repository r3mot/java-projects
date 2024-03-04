package social.Objects;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class FeedPane extends Pane {

    private final String STYLE_CLASS = "explorer-post-object";
    private final String CONTENT_STYLE_CLASS = "new-post-content-label";
    private final String NAME_STYLE_CLASS = "explorer-name-label";
    private final String DATE_STYLE_CLASS = "new-post-date-label";
    private final String IMAGE_STYLE_CLASS = "explorer-user-icon";
    
    private final int PREF_WIDTH = 723;
    private final int PREF_HEIGHT = 150; //163
    private final int LAYOUT_X = 20;
    
    private Circle picture;
    private Label nameLabel;
    private Label dateLabel;
    private Label contentLabel;

    private Post post;

    public FeedPane(Post post){

        this.post = post;

        createPost();
    }

    private void createPost(){

        this.setPrefWidth(PREF_WIDTH);
        this.setPrefHeight(PREF_HEIGHT);
        this.setLayoutX(LAYOUT_X);
    
        setContent();
        setImage();
        setName();
        setDate();

        this.getStyleClass().add(STYLE_CLASS);
        this.getChildren().addAll(dateLabel, nameLabel, picture, contentLabel);
    }

    private void setContent(){

        contentLabel = new Label();
        contentLabel.setPrefWidth(433);
        contentLabel.setPrefHeight(87);
        contentLabel.setLayoutX(277);
        contentLabel.setLayoutY(29);
        contentLabel.setWrapText(true);
        contentLabel.setText(this.post.getContent());
        contentLabel.getStyleClass().add(CONTENT_STYLE_CLASS);

    }

    private void setDate(){

        dateLabel = new Label();
        dateLabel.setPrefWidth(100);
        dateLabel.setPrefHeight(17);
        dateLabel.setLayoutX(141);
        dateLabel.setLayoutY(90);
        dateLabel.setText(this.post.getDate());
        dateLabel.getStyleClass().add(DATE_STYLE_CLASS);

    }

    private void setName(){

        nameLabel = new Label();
        nameLabel.setPrefWidth(136);
        nameLabel.setPrefHeight(20);
        nameLabel.setLayoutX(141);
        nameLabel.setLayoutY(63);
        nameLabel.setText(this.post.getCreatorName());
        nameLabel.getStyleClass().add(NAME_STYLE_CLASS);
    }

    private void setImage(){
        
        picture = new Circle();
        picture.setRadius(41);
        picture.setLayoutX(79);
        picture.setLayoutY(83);
        picture.setFill(new ImagePattern(new Image(this.post.getImageURL())));
        picture.getStyleClass().add(IMAGE_STYLE_CLASS);

    }

    public Post getPost(){
        return this.post;
    }

    public int getPaneHeight(){
        return this.PREF_HEIGHT;
    }

    public void changeWidth(int byWhat){
        this.setPrefWidth(byWhat);
    }

}
