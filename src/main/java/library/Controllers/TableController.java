package library.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import library.Api.Model.Book;
import library.Api.Service.BookService;
import library.Session.SessionManager;

public class TableController implements Initializable {

    @FXML private TextField input;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> subjectCol;
    @FXML private TableColumn<Book, Integer> yearCol;
    @FXML private TableColumn<Book, Integer> pagesCol;
    @FXML private TableColumn<Book, Double> ratingCol;
    @FXML private TableView<Book> table;

    private ObservableList<Book> booksAsList;
    private BookService bookService;

    /**
     * Uses Key event to determine whether or not a user is typing
     * On key release, assign event listener to the search field
     * Type: ObjectProperty<Predicate<? super E>>
     * the predicate matches the element in the filtered list
     * Converts the filtered list to a sorted list
     * Displays the searched values in the table (if any)
     * @param event
    */
    @FXML
    void searchCatalog(KeyEvent event) {
        FilteredList<Book> filteredData = new FilteredList<>(booksAsList, e -> true);
        input.setOnKeyReleased(e -> {
            input.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
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

    /**
     * Get all books from the database and store them in a
     * observable list
     * 
     * @return ObservableList<Book>
     */
    ObservableList<Book> getBooksAsList() {
        booksAsList = FXCollections.observableArrayList();
        List<Book> books = bookService.getAllBooks();
        for (Book b : books) {
            booksAsList.add(b);
        }
        return booksAsList;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        bookService = new BookService();
        this.table.setItems(getBooksAsList());
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        subjectCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject()));
        yearCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPubYear()).asObject());
        pagesCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNumPages()).asObject());
        ratingCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getRating()).asObject());

        SessionManager.getInstance().startSession(table);
    }
}
