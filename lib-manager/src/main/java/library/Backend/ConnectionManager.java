package library.Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String PATH = "jdbc:sqlite:C:/sqlite3/db.library.db";
    private static final String CLASS = "org.sqlite.JDBC";

    private Connection con;

    public Connection open() {
        try {
            Class.forName(CLASS);
            try {
                con = DriverManager.getConnection(PATH);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return con;
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
