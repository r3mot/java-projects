package social.Database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import social.Database.QueryStrings.Query;
import social.Database.QueryStrings.Insert;
import social.Objects.CurrentUser;
import social.Objects.User;
import social.Debug.Flag;

public class Database {

    private DatabaseHelper dbHelper = new DatabaseHelper();
    private Connection connection;


    /**
     * 
     * @return user profile data
     * @throws SQLException
     * 
     * Gathers all user data and returns an ArrayList of strings
     * to store in local storage for the scope of the program
     */
    public ArrayList<String> getUserData() throws SQLException{

        ResultSet rs;
        PreparedStatement ps;
        ArrayList<String> data = new ArrayList<>();

        connection = dbHelper.getConnection();

        ps = connection.prepareStatement(Query.ALL_COLUMNS_BY_USERNAME);
        ps.setString(1, CurrentUser.username);
        rs = ps.executeQuery();

        while(rs.next()){
            data.add(rs.getString(Query.GET_USERNAME));
            data.add(rs.getString(Query.GET_FIRST_NAME));
            data.add(rs.getString(Query.GET_LAST_NAME));
            data.add(rs.getString(Query.GET_MAJOR));
            data.add(rs.getString(Query.GET_STANDING));
            data.add(rs.getString(Query.GET_YEAR));
            data.add(rs.getString(Query.GET_DREAM_JOB));
            data.add(rs.getString(Query.GET_IMAGE));
            data.add(rs.getString(Query.GET_CLUBS));
        }

        ps.close();
        rs.close();
        connection.close();

        return data;
    }

    /**
     * 
     * @param user Object containing user data
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * Adds newly created user to the database
     */
    public void createUser(User user) throws SQLException{

        PreparedStatement ps;

        String hashedPassword = hash(user.getPassword());
        connection = dbHelper.getConnection();

        ps = connection.prepareStatement(Insert.NEW_USER);
        ps.setString(1, user.getUsername());
        ps.setString(2, hashedPassword);
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setString(5, user.getMajor());
        ps.setString(6, user.getStanding());
        ps.setString(7, user.getYear());
        ps.setString(8, user.getDreamJob());
        ps.setString(9, user.getImage().getUrl());
        ps.executeUpdate();
        ps.close();

        connection.close();

    }

    /**
     * 
     * @param username to be checked against db
     * @return user exists
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * When a new user creates an account, we must
     * ensure the username is unique
     */
    public boolean userExists(String username) throws ClassNotFoundException, SQLException{

        connection = dbHelper.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        boolean exists = false;

        ps = connection.prepareStatement(Query.ALL_COLUMNS_BY_USERNAME);
        ps.setString(1, username);
        rs = ps.executeQuery();

        while(rs.next()){
            if(username.equals(rs.getString(Query.GET_USERNAME))){
                exists = true;
                break;
            }
        }

        ps.close();
        rs.close();
        connection.close();

        return exists;
    }


     /**
     * 
     * @param content user wishes to post
     * @param date of post
     * @throws SQLException
     * 
     * Adds new post to the database
     */
    public void addPost(String content, String date) throws SQLException{

        PreparedStatement ps;
        connection = dbHelper.getConnection();
        ps = connection.prepareStatement(Insert.NEW_POST);

        ps.setString(1, CurrentUser.username);
        ps.setString(2, CurrentUser.name);
        ps.setString(3, content);
        ps.setString(4, date);
        ps.setString(5, CurrentUser.imageURL);
        ps.setInt(6, 0);
        ps.executeUpdate();
        ps.close();

        connection.close();

    }

    /**
     * 
     * @param passwordToHash raw password entry
     * @return
     * 
     * Hashes password as to not store plain text
     * May also be used to decode a previously hashed password
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
        } catch (NoSuchAlgorithmException e) {
            Flag.DEBUG("Error hashing password", e);
        }

        return generatedPassword;
    } 
}
