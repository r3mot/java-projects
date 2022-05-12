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
import javafx.scene.image.Image;
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

    @FXML private ImageView icon;
    @FXML private Label label;

    private Pane userLoginForm;
    private Pane clubLoginForm;
    private Pane createUserForm;
    private Pane createClubForm;

    private final String STUDENT_LOGIN = "Student Login";
    private final String STUDENT_CREATE = "Add New Student";
    private final String CLUB_LOGIN = "Club Login";
    private final String CLUB_CREATE = "Add New Club";
    private final String LOGIN_URL = "file:final/src/main/resources/Images/UI/password.png";
    private final String ADD_URL = "file:final/src/main/resources/Images/UI/add-friend.png";


     /**
     * Start of controller class.
     * Load FXML files (login / create account forms)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {  

            createUserForm = FXMLLoader.load(App.class.getResource("CreateUserForm.fxml"));
            userLoginForm = FXMLLoader.load(App.class.getResource("LoginForm.fxml"));

            createClubForm = FXMLLoader.load(App.class.getResource("CreateClubForm.fxml"));
            clubLoginForm = FXMLLoader.load(App.class.getResource("ClubLoginForm.fxml"));

            loginPane.getChildren().add(userLoginForm);
            createUserPane.getChildren().add(createUserForm);

            prepareTransitionPane();

        }catch(IOException e) { System.out.println(e.getCause());}
        
    }


    /**
     * 
     * @param event display club login form
     */
    @FXML
    void clubLogin(ActionEvent event){

        loginPane.getChildren().clear();
        loginPane.getChildren().add(clubLoginForm);

        clubLogin.setVisible(false);
        studentLogin.setVisible(true);

        label.setText(CLUB_LOGIN);
    }

    /**
     * 
     * @param event display student login form
     */
    @FXML
    void studentLogin(ActionEvent event){

        loginPane.getChildren().clear();
        loginPane.getChildren().add(userLoginForm);

        clubLogin.setVisible(true);
        studentLogin.setVisible(false);

        label.setText(STUDENT_LOGIN);
    }

    /**
     * 
     * @param event display new club form
     * @throws IOException
     */
    @FXML
    void createClub(ActionEvent event) throws IOException{

        createUserPane.getChildren().clear();
        createUserPane.getChildren().add(createClubForm);
        club.setVisible(false);
        user.setVisible(true);
        label.setText(CLUB_CREATE);
        
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

        label.setText(STUDENT_CREATE);
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
            icon.setImage(new Image(LOGIN_URL));
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
            icon.setImage(new Image(ADD_URL));
        }));
    }
    
    /**
     * Helper for Transition animations
     */
    private void prepareTransitionLeft(){
        createUserPane.setVisible(false);
        goToCreateUser.setVisible(false);
        goToLogin.setVisible(true);
        club.setVisible(false);
        user.setVisible(false);

        clubLogin.setVisible(true);
        label.setText(STUDENT_LOGIN);

    }

    /**
     * Helper for Transition animations
     */
    private void prepareTransitionRight(){
        loginPane.setVisible(false);
        goToCreateUser.setVisible(false);
        goToLogin.setVisible(true);

        clubLogin.setVisible(false);
        studentLogin.setVisible(false);

        label.setText(STUDENT_CREATE);
    }

    /**
     * Helper for Transition animations
     */
    private void prepareTransitionPane(){
        createUserPane.setVisible(false);
        club.setVisible(false);
        user.setVisible(false);
        studentLogin.setVisible(false);
        label.setText(STUDENT_LOGIN);
        icon.setImage(new Image(LOGIN_URL));
    }

}