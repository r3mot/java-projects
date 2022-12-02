package library.Controllers;

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
import javafx.stage.Stage;
import javafx.util.Duration;
import library.Backend.Book;
import library.Backend.Library;
import library.Backend.Sort.Filter;

public class HomeController implements Initializable {

    // Objs
    Library library = new Library();
    Filter filter;

    @FXML
    private Pane pane;
    @FXML
    private Pane leftPane, leftPaneInitial;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button addBookBtn, cancelBtn, b1;
    @FXML
    private RadioButton pagesFilterBtn;
    @FXML
    private RadioButton ratingFilterBtn;
    @FXML
    private RadioButton subjectFilterBtn;
    @FXML
    private RadioButton yearFilterBtn;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, Integer> pagesCol;
    @FXML
    private TableColumn<Book, Double> ratingCol;
    @FXML
    private TableColumn<Book, Integer> yearCol;
    @FXML
    private TableColumn<Book, String> subjectCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TextField pagesInput;
    @FXML
    private TextField ratingInput;
    @FXML
    private TextField searchField;
    @FXML
    private TextField subjectInput;
    @FXML
    private TextField titleInput;
    @FXML
    private TextField yearInput;
    @FXML
    private Label yearWarning, pagesWarning, ratingWarning;
    @FXML
    private ImageView image;
    @FXML
    private ToggleGroup filters;
    @FXML
    private ImageView close;
    @FXML
    private ImageView minimize;
    @FXML
    private Pane navbar;
    private ObservableList<Book> booksAsList;
    private Book[] booksCopy;

    // ============================================ Mouse Event Controllers
    // =====================================================

    @FXML
    void addBookOnClick(ActionEvent event) throws IOException {

        if (textFieldEmpty())
            triggerWarning(event);

        Book newBook = library.addBook(
                titleInput.getText(),
                subjectInput.getText(),
                Integer.parseInt(yearInput.getText()),
                Integer.parseInt(pagesInput.getText()),
                Double.parseDouble(ratingInput.getText()));

        addToTable(newBook);
        triggerSuccessDialog(event);
        animationSlideRight();
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
    /* Closes the program */
    void closeOnClick(MouseEvent event) {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.close();
    }

    @FXML
    /* Minimizes the program */
    void minimizeOnClick(MouseEvent event) {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setIconified(true);
    }

    @FXML
    /* Triggers animation when ADD BOOK btn clicked */
    private void b1OnClick(ActionEvent event) {
        animationSlideLeft();
    }

    @FXML
    private void cancelOnClick(ActionEvent event) {
        animationSlideRight();
    }

    // ============================================ Keyboard Event Controllers
    // ==================================================

    @FXML
    /* Checks input type in text field - populates error messages */
    void checkYearInputType(KeyEvent event) {
        yearInput.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (yearInput.getText().matches("[0-9]*")) {
                yearWarning.setVisible(false);
            } else {
                yearWarning.setVisible(true);
            }
        });
    }

    @FXML
    void checkPagesInputType(KeyEvent event) {
        pagesInput.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (pagesInput.getText().matches("[0-9]*")) {
                pagesWarning.setVisible(false);
            } else {
                pagesWarning.setVisible(true);
            }
        });
    }

    @FXML
    void checkRatingInputType(KeyEvent event) {
        ratingInput.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (ratingInput.getText().matches("[0-9]*")) {
                ratingWarning.setVisible(false);
            } else {
                ratingWarning.setVisible(true);
            }
        });
    }

    @FXML
    void searchCatalog(KeyEvent event) {
        /*
         * - Uses Key event to determine whether or not a user is typing
         * - On key release, assign event listener to the search field
         * - Type: ObjectProperty<Predicate<? super E>>
         * - the predicate matches the element in the filtered list
         * - Converts the filtered list to a sorted list
         * - Displays the searched values in the table (if any)
         */
        update(); // Custom event listener

        FilteredList<Book> filteredData = new FilteredList<>(booksAsList, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Book>) book -> {
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
            SortedList<Book> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }

    // ============================================ Initializers && Listeners
    // ===================================================

    @Override
    /* Initialize table */
    public void initialize(URL arg0, ResourceBundle arg1) {
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        subjectCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject()));
        yearCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPubYear()).asObject());
        pagesCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNumPages()).asObject());
        ratingCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getRating()).asObject());
        start();
    }

    /* Sets all necessary data in library and GUI table */
    private void start() {
        library.initialize();
        this.booksCopy = library.getCatalog();
        this.filter = new Filter(booksCopy);
        this.table.setItems(bookList(booksCopy));
        setVisibility(false);
    }

    /* Ensures table and library are in sync */
    private void update() {
        booksCopy = library.getCatalog();
        filter = new Filter(booksCopy);
        bookList(booksCopy);
    }

    private void addToTable(Book newBook) {
        table.getItems().add(newBook);
    }

    /* Sanity checks input fields */
    private boolean textFieldEmpty() {
        if (titleInput.getText().isEmpty() ||
                subjectInput.getText().isEmpty() ||
                pagesInput.getText().isEmpty() ||
                yearInput.getText().isEmpty() ||
                ratingInput.getText().isEmpty())
            return true;
        return false;
    }

    private void clearTextFields() {
        titleInput.clear();
        subjectInput.clear();
        pagesInput.clear();
        yearInput.clear();
        ratingInput.clear();

    }

    /* List that allows for tracking of changes - used to populate table */
    ObservableList<Book> bookList(Book[] book) {
        booksAsList = FXCollections.observableArrayList();

        for (Book b : book) {
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

    // ============================================ Dialog Popups
    // ===============================================================

    /* User did not fill out all text fields */
    private void triggerWarning(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Add Book Error");
        alert.setHeaderText("Please make sure all fields are fill out");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        }
    }

    /* User successfully added a book to the library */
    private void triggerSuccessDialog(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText("Your book has been successfully added!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            clearTextFields();
        }
    }

    // ============================================ Animations
    // ==================================================================

    private void animationSlideLeft() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(leftPane);
        slide.setToX(-961);
        slide.play();
        leftPaneInitial.setTranslateX(0);
        setVisibility(true);
        slide.setOnFinished((e -> {
        }));
    }

    private void animationSlideRight() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(leftPane);
        slide.setToX(0);
        slide.play();
        leftPaneInitial.setTranslateX(0);
        setVisibility(false);
        slide.setOnFinished((e -> {
        }));
    }

    /* Visibility of textfields / Scene panes */
    private void setVisibility(boolean update) {
        leftPane.setVisible(update);
        titleInput.setVisible(update);
        subjectInput.setVisible(update);
        pagesInput.setVisible(update);
        yearInput.setVisible(update);
        ratingInput.setVisible(update);
        addBookBtn.setVisible(update);
        leftPaneInitial.setVisible(!update);
        b1.setVisible(!update);
    }
}