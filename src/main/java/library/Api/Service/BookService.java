package library.Api.Service;

import java.util.List;
import java.util.UUID;

import library.Api.Dal.BookDataAccess;
import library.Api.Model.Book;

public class BookService {

    private final BookDataAccess bookDataAccess;

    public BookService(BookDataAccess bookDataAccess) {
        this.bookDataAccess = bookDataAccess;
    }

    public int addBook(Book book) {
        return bookDataAccess.insertBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDataAccess.selectAllBooks();
    }

    public int deleteBook(UUID id) {
        return bookDataAccess.deleteBookById(id);
    }

}
