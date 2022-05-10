package social.Controllers.Login;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import social.App;

public class LoginController implements Initializable {
    
    @FXML private Pane loginPane;
    @FXML private Pane createUserPane;
    @FXML private Pane formsPane;
    @FXML private Pane transitionPane;

    @FXML private Button goToCreateUser;
    @FXML private Button goToLogin;

    @FXML private ImageView addIcon;
    @FXML private ImageView loginIcon;
    @FXML private Label createLabel;
    @FXML private Label welcomeLabel;

    private Pane loginForm;
    private Pane createUserForm;


     /**
     * Start of controller class.
     * Load FXML files (login / create account forms)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {   // Get FXML resources on load

            createUserForm = FXMLLoader.load(App.class.getResource("CreateUserForm.fxml"));
            loginForm = FXMLLoader.load(App.class.getResource("LoginForm.fxml"));
            loginPane.getChildren().add(loginForm);
            createUserPane.getChildren().add(createUserForm);

            prepareTransitionPane();

        }catch(IOException e) { System.out.println(e.getCause());}
        
    }

    /**
     * 
     * @param event transition animation
     */
    @FXML
    void slideLeft(ActionEvent event) {

        prepareTransitionLeft();

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(transitionPane);
        slide.setToX(0);
        slide.play();
        slide.setOnFinished((e->{
            loginPane.setVisible(true);
        }));
        
    }

    /**
     * 
     * @param event transition animation
     */
    @FXML
    void slideRight(ActionEvent event) {

        prepareTransitionRight();

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(transitionPane);
        slide.setToX(450);
        slide.play();
        slide.setOnFinished((e->{
            createUserPane.setVisible(true);
        }));
    }
    
    /**
     * Helper for Transition animations
     */
    private void prepareTransitionLeft(){
        createUserPane.setVisible(false);
        goToCreateUser.setVisible(false);
        goToLogin.setVisible(true);
        welcomeLabel.setVisible(true);
        createLabel.setVisible(false);
    }

    /**
     * Helper for Transition animations
     */
    private void prepareTransitionRight(){
        loginPane.setVisible(false);
        goToCreateUser.setVisible(true);
        goToLogin.setVisible(false);
        welcomeLabel.setVisible(false);
        createLabel.setVisible(true);
    }

    /**
     * Helper for Transition animations
     */
    private void prepareTransitionPane(){
        createUserPane.setVisible(false);
        createLabel.setVisible(false);
    }

}