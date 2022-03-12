package midterm.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import midterm.Backend.Book;
import midterm.Backend.Library;
import midterm.Backend.Sort.Filter;

public class HomeController implements Initializable {

    // Objs
    Library library = new Library();
    Filter filter;

    @FXML private Pane pane;
    @FXML private Pane leftPane, leftPaneInitial;
    @FXML private AnchorPane anchor;
    @FXML private Button addBookBtn, cancelBtn;
    @FXML private Button hiddenBtn, b1;
    @FXML private RadioButton pagesFilterBtn;
    @FXML private RadioButton ratingFilterBtn;
    @FXML private RadioButton subjectFilterBtn;
    @FXML private RadioButton yearFilterBtn;
    @FXML private TableView < Book > table;
    @FXML private TableColumn < Book, Integer > pagesCol;
    @FXML private TableColumn < Book, Double > ratingCol;
    @FXML private TableColumn < Book, Integer > yearCol;
    @FXML private TableColumn < Book, String > subjectCol;
    @FXML private TableColumn < Book, String > titleCol;
    @FXML private TextField pagesInput;
    @FXML private TextField ratingInput;
    @FXML private TextField searchField;
    @FXML private TextField subjectInput;
    @FXML private TextField titleInput;
    @FXML private TextField yearInput;
    @FXML private Label addBookLabel;
    @FXML private Label warning1, warning2, warning3;
    @FXML private ImageView image;
    @FXML private ToggleGroup filters;
    private ObservableList < Book > booksAsList;
    private Book[] booksCopy;

    @FXML private ImageView close;
    @FXML private ImageView minimize;
    @FXML private Pane navbar;

   

    @FXML
    void addBookOnClick(ActionEvent event) throws IOException {

        checkInputTypes();

        if (textFieldEmpty())
            triggerWarning(event);

        Book newBook = library.addBook(
            titleInput.getText(),
            subjectInput.getText(),
            Integer.parseInt(yearInput.getText()),
            Integer.parseInt(pagesInput.getText()),
            Double.parseDouble(ratingInput.getText())
        );

        addToTable(newBook);
        triggerSuccessDialog(event);
        slideRight();
        update();
    }

    @FXML
    void pagesFilterOnClick(ActionEvent event) {
        filters.getSelectedToggle();
        table.setItems(bookList(filter.filterPages()));
    }

    @FXML
    void ratingFilterOnClick(ActionEvent event) {
        filters.getSelectedToggle();
        table.setItems(bookList(filter.filterRating()));
    }

    @FXML
    void yearFilterOnClick(ActionEvent event) {
        filters.getSelectedToggle();
        table.setItems(bookList(filter.filterYear()));
    }

    @FXML
    void closeOnClick(MouseEvent event) {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.close();
    }

    @FXML
    void minimizeOnClick(MouseEvent event) {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setIconified(true);
    }

    @FXML
    private void b1OnClick(ActionEvent event){
        slideLeft();

     }

     @FXML
    private void cancelOnClick(ActionEvent event){
        slideRight();
     }
    
    /* - Uses Key event to determine whether or not a user is typing
        - On key release, assign event listener to the search field
        - Type: ObjectProperty<Predicate<? super E>>
        - the predicate matches the element in the filtered list
        - Converts the filtered list to a sorted list
        - Displays the searched values in the table (if any)
    */
    @FXML
    void searchCatalog(KeyEvent event) {

        update(); // Custom even listener
        

        FilteredList < Book > filteredData = new FilteredList < > (booksAsList, e-> true);
        searchField.setOnKeyReleased(e-> {
            searchField.textProperty().addListener((ObservableValue, oldValue, newValue)-> {
                filteredData.setPredicate((Predicate <? super Book> ) book-> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (book.getTitle().toLowerCase().contains(lower)) {
                        return true;
                    } else if (book.getSubject().toLowerCase().contains(lower)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList < Book > sortedData = new SortedList < > (filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        subjectCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject()));
        yearCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPubYear()).asObject());
        pagesCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNumPages()).asObject());
        ratingCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getRating()).asObject());
        beforeSlide();
        start();
    }


    /* End of Action Events for GUI */
    // ==================================================================================================


   /* Sets all necessary data in library and GUI table */
    private void start() {
        library.initialize();
        this.booksCopy = library.getCatalog();
        this.filter = new Filter(booksCopy);
        this.table.setItems(bookList(booksCopy));
        clearWarnings();
    }

    private void addToTable(Book newBook) {
        table.getItems().add(newBook);
    }

    private boolean textFieldEmpty() {
        if (titleInput.getText().isEmpty() ||
            subjectInput.getText().isEmpty() ||
            pagesInput.getText().isEmpty() ||
            yearInput.getText().isEmpty() ||
            ratingInput.getText().isEmpty())
            return true;
        return false;
    }

    private void checkInputTypes(){
        if(!yearInput.getText().matches("[0-9]*")){
            warning1.setVisible(true);
        }
        if(!pagesInput.getText().matches("[0-9]*")){
            warning2.setVisible(true);
        }
        if(!ratingInput.getText().matches("[0-9]*")){
            warning3.setVisible(true);
        }
    }

    private void clearWarnings(){
        warning1.setVisible(false);
        warning2.setVisible(false);
        warning3.setVisible(false);
    }

    /* Ensures table and library are in sync */
    private void update() {
        booksCopy = library.getCatalog();
        filter = new Filter(booksCopy);
        bookList(booksCopy);
    }

    private void clearTextFields() {
        titleInput.clear();
        subjectInput.clear();
        pagesInput.clear();
        yearInput.clear();
        ratingInput.clear();

    }

    /* List that allows for tracking of changes - used to populate table */
    ObservableList < Book > bookList(Book[] book) {
        booksAsList = FXCollections.observableArrayList();

        for (Book b: book) {
            if (b.getRating() == -1) // Refer to Generator Class: line 89
                continue;
            booksAsList.add(new Book(
                b.getTitle(),
                b.getSubject(),
                b.getPubYear(),
                b.getNumPages(),
                b.getRating()));
        }
        return booksAsList;
    }


    /* User did not fill out all text fields */
    private void triggerWarning(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Add Book Error");
        alert.setHeaderText("Please make sure all fields are fill out");
        Optional < ButtonType > result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {}
    }

    /* User successfully added a book to the library */
    private void triggerSuccessDialog(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText("Your book has been successfully added!");
        Optional < ButtonType > result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            clearTextFields();
        }
    }

    /* The following are related to the slide transition animation */
    private void slideLeft(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(leftPane);
        slide.setToX(-961);
        slide.play();
        leftPaneInitial.setTranslateX(0);
        afterSlide();
        slide.setOnFinished((e->{
        }));
    }

    private void slideRight(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(leftPane);
        slide.setToX(0);
        slide.play();
        leftPaneInitial.setTranslateX(0);
        beforeSlide();
        slide.setOnFinished((e->{
        }));
    }

    private void beforeSlide(){
        leftPane.setVisible(false);
        titleInput.setVisible(false);
        subjectInput.setVisible(false);
        pagesInput.setVisible(false);
        yearInput.setVisible(false);
        ratingInput.setVisible(false);
        addBookBtn.setVisible(false);
        leftPaneInitial.setVisible(true);
        b1.setVisible(true);
    }

    private void afterSlide(){
        leftPane.setVisible(true);
        titleInput.setVisible(true);
        subjectInput.setVisible(true);
        pagesInput.setVisible(true);
        yearInput.setVisible(true);
        ratingInput.setVisible(true);
        addBookBtn.setVisible(true);
        leftPaneInitial.setVisible(false);
        b1.setVisible(false);
    }

}