package library.Session;

import javafx.scene.control.TableView;
import library.Api.Model.Book;

public class SessionManager {

    private static SessionManager instance;
    private TableView<Book> table;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void startSession(TableView<Book> table) {
        this.table = table;
    }

    public TableView<Book> getTable() {
        return table;
    }

}
