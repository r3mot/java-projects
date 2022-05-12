package social.Database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import social.Database.QueryStrings.Path;
import social.Debug.Flag;


public class DatabaseHelper {

    private Connection connection;
    
    public boolean databaseExists(String dbFilePath){
        File dbFile = new File(dbFilePath);
        return dbFile.exists();
    }

    public Connection getConnection() {

        try {
            Class.forName(Path.CLASS);
        } catch (ClassNotFoundException e1) {
            Flag.DEBUG("CONNECTION ERROR");
        }

  
        Flag.PROCESS("Path to database exists");

        try {
            connection = DriverManager.getConnection(Path.DB);
            
            Flag.PROCESS("Connected to database...");

            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return null;
    }
}
