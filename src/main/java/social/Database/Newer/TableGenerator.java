package social.Database.Newer;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TableGenerator {
    
    private final String USERS_TABLE = "CREATE TABLE Users(" +
        "Username VARCHAR PRIMARY KEY," +
        "Password VARCHAR NOT NULL)";
        
    private final String USER_PROFILE_TABLE = "CREATE TABLE UserProfile(" +
        "Username VARCHAR PRIMARY KEY," +
        "FirstName VARCHAR PRIMARY KEY," +
        "LastName VARCHAR NOT NULL," +
        "Major VARCHAR NOT NULL," +
        "Standing VARCHAR NOT NULL," +
        "GradYear VARCHAR NOT NULL," +
        "DreamJob VARCHAR NOT NULL," +
        "Picture VARCHAR NOT NULL," +
        "FOREIGN KEY(Username) REFERENCES Users(Username))";

    private final String CLUBS_TABLE = "CREATE TABLE Clubs(" +
        "Email VARCHAR PRIMARY KEY," +
        "Password VARCHAR NOT NULL)";

    private final String CLUB_PROFILE_TABLE = "CREATE TABLE ClubProfile(" +
        "Name VARCHAR NOT NULL," +
        "Purpose VARCHAR NOT NULL," +
        "MainContact VARCHAR NOT NULL," +
        "Website VARCHAR NOT NULL," +
        "Picture VARCHAR NOT NULL," +
        "FOREIGN KEY(Email) REFERENCES Clubs)";

    private final String FRIENDSHIP_TABLE = "CREATE TABLE IF NOT EXISTS Friendship(" +
        "FriendshipID INTEGER NOT NULL," +
        "Username1 VARCHAR NOT NULL," +
        "Username2 VARCHAR NOT NULL," +
        "Status INTEGER NOT NULL," +
        "PRIMARY KEY(FriendshipId, Username1, Username2))";

    public void addUserTable(){
        ConnectionManager connManager = new ConnectionManager();

        try {
            PreparedStatement ps; 
            ps = connManager.getConnection().prepareStatement(USERS_TABLE);
            ps.executeUpdate();

        }
        catch(SQLException e){
            e.getSQLState();
        }
    }

    public void addClubsTable(){
        ConnectionManager connManager = new ConnectionManager();

        try {
            PreparedStatement ps; 
            ps = connManager.getConnection().prepareStatement(CLUBS_TABLE);
            ps.executeUpdate();
    
        }
        catch(SQLException e){
            e.getSQLState();
        }
    }

    
    public void addUserProfileTable(){
        ConnectionManager connManager = new ConnectionManager();

        
        try {
            PreparedStatement ps; 
            ps = connManager.getConnection().prepareStatement(USER_PROFILE_TABLE);
            ps.executeUpdate();

        }
        catch(SQLException e){
            e.getSQLState();
        }
    }


    public void addClubProfileTable(){
        ConnectionManager connManager = new ConnectionManager();

        try {
            PreparedStatement ps; 
            ps = connManager.getConnection().prepareStatement(CLUB_PROFILE_TABLE);
            ps.executeUpdate();
   
        }
        catch(SQLException e){
            e.getSQLState();
        }
    }



}
