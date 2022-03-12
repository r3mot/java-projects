package midterm.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import midterm.Backend.Book;
import midterm.Backend.Library;
import midterm.Backend.Sort.Filter;

public class HomeController implements Initializable{

    // Objs
    Library library = new Library();
    Filter filter;

    @FXML private Pane pane;
    @FXML private Pane leftPane;
    @FXML private AnchorPane anchor;
    @FXML private Button addBookBtn;
    @FXML private Button hiddenBtn;
    @FXML private RadioButton pagesFilterBtn;
    @FXML private RadioButton ratingFilterBtn;
    @FXML private RadioButton subjectFilterBtn;
    @FXML private RadioButton yearFilterBtn;
    @FXML private TableView<Book> table;
    @FXML private TableColumn<Book, Integer> pagesCol;
    @FXML private TableColumn<Book, Integer> ratingCol;
    @FXML private TableColumn<Book, Integer> yearCol;
    @FXML private TableColumn<Book, String> subjectCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TextField pagesInput;
    @FXML private TextField ratingInput;
    @FXML private TextField searchField;
    @FXML private TextField subjectInput;
    @FXML private TextField titleInput;
    @FXML private TextField yearInput;
    @FXML  private Label addBookLabel;
    @FXML private ToggleGroup filters;
    private ObservableList<Book> booksAsList;
    private Book[] booksCopy;

    @FXML
    void addBookOnClick(ActionEvent event) throws IOException {

        if(textFieldEmpty())
            triggerWarning(event);
        
        Book newBook = library.addBook
        (
            titleInput.getText(),
            subjectInput.getText(),
            Integer.parseInt(yearInput.getText()),
            Integer.parseInt(pagesInput.getText()),
            Integer.parseInt(ratingInput.getText())
        );

        addToTable(newBook);
        triggerSuccessDialog(event);
        updateFilters();
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
    void searchCatalog(KeyEvent event) {
        checkForUpdates();
        FilteredList<Book> filteredData = new FilteredList<>(booksAsList, e->true);
        searchField.setOnKeyReleased(e->{
            searchField.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Book>) book-> {
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if(book.getTitle().toLowerCase().contains(lower)){
                        return true;
                    }else if(book.getSubject().toLowerCase().contains(lower)){
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        subjectCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject()));
        yearCol.setCellValueFactory (data -> new SimpleIntegerProperty(data.getValue().getPubYear()).asObject());
        pagesCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNumPages()).asObject());
        ratingCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getRating()).asObject());
        start();
    }


// ==================================================================================================


    private void start(){
        library.initialize();
        this.booksCopy = library.getCatalog();
        this.filter = new Filter(booksCopy);
        this.table.setItems(bookList(booksCopy));
    }

    private void addToTable(Book newBook){
        table.getItems().add(newBook);
    }

    private void updateFilters(){
        booksCopy = library.getCatalog();
        filter = new Filter(booksCopy);
    }
    private boolean textFieldEmpty(){
        if( titleInput.getText().isEmpty()      ||
            subjectInput.getText().isEmpty()    ||
            pagesInput.getText().isEmpty()      ||
            yearInput.getText().isEmpty()       ||
            ratingInput.getText().isEmpty())
                return true;
        return false;
    }

    private void checkForUpdates(){
        booksCopy = library.getCatalog();
        bookList(booksCopy);
    }
    private void clearTextFields(){
        titleInput.clear(); 
        subjectInput.clear();
        pagesInput.clear();
        yearInput.clear();
        ratingInput.clear();
        
    }

    ObservableList<Book> bookList(Book[] book){
        booksAsList = FXCollections.observableArrayList();

        for(Book b : book){
            if(b.getRating() == -1)
                continue;
                booksAsList.add(new Book
                (
                    b.getTitle(), 
                    b.getSubject(), 
                    b.getPubYear(), 
                    b.getNumPages(), 
                    b.getRating())
                );
        }
        return booksAsList;
    }


    private void triggerWarning(ActionEvent event){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Add Book Error");
        alert.setHeaderText("Please make sure all fields are fill out");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
        }
    }

    private void triggerSuccessDialog(ActionEvent event) throws IOException { 
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText("Your book has been successfully added!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            clearTextFields();
        }
    } 

}
