package library.Api.Dao;

import java.util.List;
import java.util.UUID;

import library.Api.Model.Book;

public interface BookDao {

    int insertBook(Book book);

    List<Book> selectAllBooks();

    int deleteBookById(UUID id);

}
