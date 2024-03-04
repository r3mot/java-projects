package library.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import library.App;
import library.Api.Model.Book;
import library.Api.Service.BookService;
import library.Session.SessionManager;
import library.Utility.Animation;

public class HomeController implements Initializable {
    
    @FXML private AnchorPane anchor;
    @FXML private Button addBookButton;
    @FXML private Button animateAddBook;
    @FXML private Pane addBookPane;
    @FXML private Pane tablePane;
    @FXML private Pane navbar;
    @FXML private Pane newBookPane;
    @FXML private Pane leftPaneInitial;
    @FXML private ImageView close;
    @FXML private ImageView minimize;
    @FXML private Label ratingWarning;
    @FXML private Label pagesWarning;
    @FXML private Label yearWarning;
    @FXML private TextField pages;
    @FXML private TextField rating;
    @FXML private TextField subject;
    @FXML private TextField title;
    @FXML private TextField year;

    private Pane privateTablePane;
    BookService bookService; //db service

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        newBookPane.setVisible(false);
        bookService = new BookService();
        
        try {
            // Import table pane from fxml file
            privateTablePane = FXMLLoader.load(App.class.getResource("Table.fxml"));
            tablePane.getChildren().add(privateTablePane);
        } catch (Exception e) { }
    }

    
    @FXML
    /* Close Application */
    void exitApplication(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    /* Minimize Application */
    void minimizeApplication(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Add a new book to the database
     * Trigger animation to hide the newBookPane
     * Update the table with the added book
     * 
     * @param event add book clicked
     */
    @FXML
    void addBook(ActionEvent event) {
        Book newBook = getBookFromInput();
        bookService.addBook(newBook);
        Animation.animationSlideRight(newBookPane, leftPaneInitial);
        animateAddBook.setVisible(true);
        SessionManager.getInstance().getTable().getItems().add(newBook);
    }

    /**
     * Animates the newBookPane to the left
     */
    @FXML
    void startAnimation(ActionEvent event) {
        Animation.animationSlideLeft(newBookPane, leftPaneInitial);
        animateAddBook.setVisible(false);
    }

    /**
     * Cancels the add book animation and hides the newBookPane
     * User can still add a book by clicking the add book button
     */
    @FXML
    void cancel(ActionEvent event) {
        Animation.animationSlideRight(newBookPane, leftPaneInitial);
        animateAddBook.setVisible(true);
    }

    /**
     * Check if the input is valid
     * 
     * @param event key released
     */
    @FXML
    void sanitizeYear(KeyEvent event) {
        handler(year, yearWarning);
    }

    /**
     * Check if the input is valid
     * 
     * @param event key released
     */
    @FXML
    void sanitizePages(KeyEvent event) {
        handler(pages, pagesWarning);

    }

    /**
     * Check if the input is valid
     * 
     * @param event key released
     */
    @FXML
    void sanitizeRating(KeyEvent event) {
        handler(rating, ratingWarning);
    }

    /**
     * Reusable method to check if the input is valid
     * Sets the warning label to visible if the input is invalid
     * If the input is valid, the warning label is hidden
     * 
     * @param tf textfield
     * @param lb label
     */
    private void handler(TextField tf, Label lb) {
        tf.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (tf.getText().matches("[0-9]*")) {
                lb.setVisible(false);
            } else {
                lb.setVisible(true);
            }
        });

    }

    /**
     * Collects infromation from all the textfields
     * Creates a new book with the input
     * 
     * @return book
     */
    private Book getBookFromInput() {
        Book book = new Book(
                UUID.randomUUID().toString(),
                title.getText(),
                subject.getText(),
                Integer.parseInt(year.getText()),
                Integer.parseInt(pages.getText()),
                Integer.parseInt(rating.getText()));

        return book;
    }
}
