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
    @FXML private Button club;
    @FXML private Button user;
    @FXML private Button studentLogin;
    @FXML private Button clubLogin;

    @FXML private ImageView addIcon;
    @FXML private ImageView loginIcon;
    @FXML private Label createLabel;
    @FXML private Label welcomeLabel;

    private Pane userLoginForm;
    private Pane clubLoginForm;
    private Pane createUserForm;
    private Pane createClubForm;


     /**
     * Start of controller class.
     * Load FXML files (login / create account forms)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {   // Get FXML resources on load

            createUserForm = FXMLLoader.load(App.class.getResource("CreateUserForm.fxml"));
            userLoginForm = FXMLLoader.load(App.class.getResource("LoginForm.fxml"));

            createClubForm = FXMLLoader.load(App.class.getResource("CreateClubForm.fxml"));
            clubLoginForm = FXMLLoader.load(App.class.getResource("ClubLoginForm.fxml"));

            loginPane.getChildren().add(userLoginForm);
            createUserPane.getChildren().add(createUserForm);

            prepareTransitionPane();

        }catch(IOException e) { System.out.println(e.getCause());}
        
    }


    @FXML
    void clubLogin(ActionEvent event){

        loginPane.getChildren().clear();
        loginPane.getChildren().add(clubLoginForm);

        clubLogin.setVisible(false);
        studentLogin.setVisible(true);
    }

    @FXML
    void studentLogin(ActionEvent event){

        loginPane.getChildren().clear();
        loginPane.getChildren().add(userLoginForm);

        clubLogin.setVisible(true);
        studentLogin.setVisible(false);
    }

    /**
     * 
     * @param event display new club form
     * @throws IOException
     */
    @FXML
    void createClub(ActionEvent event) throws IOException{

        // createClubForm = FXMLLoader.load(App.class.getResource("CreateClubForm.fxml"));
        createUserPane.getChildren().clear();
        createUserPane.getChildren().add(createClubForm);
        club.setVisible(false);
        user.setVisible(true);
    }

    /**
     * 
     * @param event display new user form
     */
    @FXML
    void createUser(ActionEvent event){

        createUserPane.getChildren().clear();
        createUserPane.getChildren().add(createUserForm);

        club.setVisible(true);
        user.setVisible(false);

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
            goToCreateUser.setVisible(true);
            goToLogin.setVisible(false);
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
            club.setVisible(true);
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
        club.setVisible(false);
        user.setVisible(false);

        clubLogin.setVisible(true);

    }

    /**
     * Helper for Transition animations
     */
    private void prepareTransitionRight(){
        loginPane.setVisible(false);
        goToCreateUser.setVisible(false);
        goToLogin.setVisible(true);
        welcomeLabel.setVisible(false);
        createLabel.setVisible(true);

        clubLogin.setVisible(false);
        studentLogin.setVisible(false);
    }

    /**
     * Helper for Transition animations
     */
    private void prepareTransitionPane(){
        createUserPane.setVisible(false);
        createLabel.setVisible(false);
        club.setVisible(false);
        user.setVisible(false);
        studentLogin.setVisible(false);
    }

}