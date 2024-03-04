package social.Database.Newer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import social.Objects.CurrentUser;
import social.Objects.User;

public class LoginManager {

    /**
     * 
     * @param email
     * @param password
     * @return login successful 
     */
    public boolean clubLogin(String email, String password){
        return validClubLogin(email, password);
    }

    /**
     * 
     * @param username
     * @param password
     * @return login successful
     */
    public boolean userLogin(String username, String password){
        return validUserLogin(username, password);
    }

    /**
     * 
     * @param username
     * @param password
     * @return valid user credentials
     */
    private boolean validUserLogin(String username, String password){

        boolean loginResult = false;
        ConnectionManager connection = new ConnectionManager();
        PreparedStatement ps;
        ResultSet rs;

        String query = "SELECT Username, Password FROM USERS WHERE Username=? AND Password=?";

        try{

            ps = connection.getConnection().prepareStatement(query);
            
            String hashedPassword = hash(password);
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            rs = ps.executeQuery();

            while(rs.next()){
                String userUsername = rs.getString("Username");
                String userPass = rs.getString("Password");


                if(userUsername.equals(username) && userPass.equals(hashedPassword)){
                    loginResult = true;
                    break;
                }
            }

            ps.close();
            rs.close();
            connection.closeConnection();

        } catch(Exception e){}

        return loginResult;
    }

    /**
     * 
     * @param email
     * @param password
     * @return valid club credentials
     */
    private boolean validClubLogin(String email, String password){

        boolean loginResult = false;
        ConnectionManager connection = new ConnectionManager();
        PreparedStatement ps;
        ResultSet rs;

        try{
        ps = connection.getConnection().prepareStatement("SELECT Email, Password FROM CLUBS WHERE Email=?");
        String hashedPassword = hash(password);
        ps.setString(1, email);
        ps.setString(2, hashedPassword);
        rs = ps.executeQuery();

        while(rs.next()){
            String clubemail = rs.getString("Email");
            String clubPass = rs.getString("Password");

            if(clubemail.equals(email) && clubPass.equals(password)){
                loginResult = true;
                break;
            }
        }

        ps.close();
        rs.close();
        connection.closeConnection();

        }
        catch(Exception e){

        }

        return loginResult;
    }

    /**
     * 
     * @param username
     * 
     * Sets the current user that has logged in
     * Stores information in local storage and is
     * used to query the database, fill profile, and 
     * in the friendship manager
     */
    public void setCurrentUser(String username){


        ConnectionManager connection = new ConnectionManager();
        PreparedStatement ps;
        ResultSet rs;
        User user = null;

        try {
            String query = "SELECT * FROM UserProfile WHERE Username=?";
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while(rs.next()){

                int userId = rs.getInt("UserId");
                String firstname = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String major = rs.getString("Major");
                String standing = rs.getString("Standing");
                String gradYear = rs.getString("GradYear");
                String dreamJob = rs.getString("DreamJob");
                String picture = rs.getString("Picture");

                user = new User(username, "", firstname, lastName, major, standing, gradYear, dreamJob, picture);
                CurrentUser.setUser(user);
                CurrentUser.setUserId(userId);

            }
        }
        catch(SQLException e){
            e.getSQLState();
        }
    }

    /**
     * 
     * @param passwordToHash
     * @return hashed password
     */
    private String hash(String passwordToHash){

        MessageDigest md;
        StringBuilder sb;

        String generatedPassword = null;

        try {
            
            md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            md.update(bytes);
            sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {}

        return generatedPassword;
    } 
    
}
