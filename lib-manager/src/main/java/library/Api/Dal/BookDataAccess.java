package library.Api.Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import library.Api.Dao.BookDao;
import library.Api.Model.Book;
import library.Backend.ConnectionManager;

public class BookDataAccess implements BookDao {

    private final ConnectionManager connectionManager;

    public BookDataAccess() {
        this.connectionManager = new ConnectionManager();
    }

    @Override
    public int insertBook(Book book) {
        PreparedStatement ps;
        String sql = "INSERT INTO Book(BookId, Title, Subject, Publish_year, Number_pages, Rating) VALUES(?,?,?,?,?,?)";

        try {
            ps = connectionManager.open().prepareStatement(sql);
            ps.setString(1, book.getId().toString());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getSubject());
            ps.setInt(4, book.getPubYear());
            ps.setInt(5, book.getNumPages());
            ps.setDouble(6, book.getRating());
            ps.executeUpdate();
            ps.close();
            connectionManager.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Book> selectAllBooks() {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM Book";
        List<Book> books = new ArrayList<>();

        try {
            ps = connectionManager.open().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("BookId"),
                        rs.getString("Title"),
                        rs.getString("Subject"),
                        rs.getInt("Publish_year"),
                        rs.getInt("Number_pages"),
                        rs.getDouble("Rating"));
                books.add(book);
            }
            rs.close();
            ps.close();
            connectionManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int deleteBookById(UUID id) {
        PreparedStatement ps;
        String sql = "DELETE FROM Book WHERE BookId = ?";

        try {
            ps = connectionManager.open().prepareStatement(sql);
            ps.setObject(1, id);
            ps.executeUpdate();
            ps.close();
            connectionManager.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
