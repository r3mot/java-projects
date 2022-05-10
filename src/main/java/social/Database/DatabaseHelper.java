package social.Database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import social.Database.QueryStrings.Path;


public class DatabaseHelper {
    
    public boolean databaseExists(String dbFilePath){
        File dbFile = new File(dbFilePath);
        return dbFile.exists();
    }

    public Connection getConnection() {

        try {
            Class.forName(Path.CLASS);
        } catch (ClassNotFoundException e1) {
            System.out.println("CONNECTION ERROR : / . . . .");
        }

        Connection connection;

  
        System.out.println("path exists");

        try {
            connection = DriverManager.getConnection(Path.DB);
            
            System.out.println("Connected to database...");

            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return null;
    }
}
