package social.Database.Newer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates a connection to SQLite Database
 */
public class ConnectionManager {

    public static final String PATH = "jdbc:sqlite:C:/sqlite3/db.social.db";
    public static final String CLASS = "org.sqlite.JDBC";

    private Connection con;

    public Connection getConnection() {
        try {
            Class.forName(CLASS);
            try {
                con = DriverManager.getConnection(PATH);
            } catch (SQLException ex) {}

        } catch (ClassNotFoundException ex) {}

        return con;
    }

    public void closeConnection(){
        try {
            con.close();
        } 
        catch (SQLException e) {}
    }
    
}
