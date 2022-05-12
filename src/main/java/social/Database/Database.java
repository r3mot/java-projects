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
import social.Database.QueryStrings.Index;
import social.Database.QueryStrings.Insert;
import social.Objects.Club;
import social.Objects.ClubPane;
import social.Objects.CurrentUser;
import social.Objects.FriendPane;
import social.Objects.Post;
import social.Objects.User;
import social.Debug.Flag;

public class Database {

    private DatabaseHelper dbHelper = new DatabaseHelper();
    private Connection connection = dbHelper.getConnection();


    public ArrayList<ClubPane> getClubs() throws SQLException{

        ArrayList<ClubPane> clubs = new ArrayList<ClubPane>();

        ClubPane clubPane;

        PreparedStatement ps;
        ResultSet rs;

        connection = dbHelper.getConnection();
        
        ps = connection.prepareStatement(Query.GET_THE_CLUBS);

        rs = ps.executeQuery();

        int index = 0;
        while(rs.next()){

            String name = rs.getString(Query.GET_CLUB_NAME);
            String purpose = rs.getString(Query.GET_CLUB_PURPOSE);
            String contact = rs.getString(Query.GET_CLUB_CONTACT);
            String website = rs.getString(Query.GET_CLUB_WEBSITE);
            String email = rs.getString(Query.GET_CLUB_EMAIL);
            String icon = rs.getString(Query.GET_CLUB_ICON);

            clubPane = new ClubPane(new Club(name, purpose, contact, website, email, "", icon));

            clubs.add(index, clubPane);
            index++;
        }

        System.out.println("CLUBS: " + clubs.toString());
        ps.close();
        rs.close();

        return clubs;

    }
    /**
     * 
     * @param name of club
     * @throws SQLException
     * 
     * Fetches current user from database
     * Adds the selected club to the user profile
     */
    public void addClub(String email, String username) throws SQLException{

        
        ArrayList<String> toAdd = getClub(email);
        PreparedStatement ps;

        connection = dbHelper.getConnection();
        
        ps = connection.prepareStatement(Insert.ADD_CLUB_TO_USER);
        ps.setString(1, toAdd.toString());
        ps.setString(2, CurrentUser.username);

        ps.close();
        
    }

    public ArrayList<String> getClub(String email) throws SQLException{

        ArrayList<String> club = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String currentList ="";
        connection = dbHelper.getConnection();
        
        ps = connection.prepareStatement(Query.GET_CLUB);
        ps.setString(1, email);
        rs = ps.executeQuery();

        while(rs.next()){

            club.add(rs.getString(Query.GET_CLUB_EMAIL));
            club.add(rs.getString(Query.GET_CLUB_NAME));
            club.add(rs.getString(Query.GET_CLUB_PURPOSE));
            club.add(rs.getString(Query.GET_CLUB_CONTACT));
            club.add(rs.getString(Query.GET_CLUB_WEBSITE));
            club.add(rs.getString(Query.GET_CLUB_ICON));

        }

        ps.close();
        rs.close();

        return club;
    }

    /**
     * 
     * @param username
     * @return
     * @throws SQLException
     * 
     * Returns an arraylist of user data
     */
    public ArrayList<String> getUser(String username) throws SQLException{

        ArrayList<String> user = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            user.add("");
        }
        PreparedStatement ps;
        ResultSet rs;

        ps = connection.prepareStatement(Query.GET_REQUESTED_USER);
        ps.setString(1, username);
        rs = ps.executeQuery();

        while(rs.next())
        {
            user.set(0,rs.getString(Query.GET_FIRST_NAME));
            user.set(1,rs.getString(Query.GET_LAST_NAME));
            user.set(2,rs.getString(Query.GET_MAJOR));
            user.set(3,rs.getString(Query.GET_STANDING));
            user.set(4,rs.getString(Query.GET_YEAR));
            user.set(5,rs.getString(Query.GET_DREAM_JOB));
            user.set(6, rs.getString(Query.GET_IMAGE));
        }


        ps.close();
        rs.close();


        return user;
    }

    /**
     * 
     * @param post
     * @throws SQLException
     */
    public void addUserPost(Post post) throws SQLException{
        
        PreparedStatement ps;

        ps = connection.prepareStatement(Insert.NEW_POST);

        ps.setString(1, CurrentUser.username);
        ps.setString(2, post.getPostName());
        ps.setString(3, post.getPostContent());
        ps.setString(4, post.getPostDate());
        ps.setString(5, post.getPostURL());
        ps.executeUpdate();
        ps.close();

    }
    /**
     * 
     * @return global feed
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>> getGlobalFeed() throws SQLException{
        
        ArrayList<ArrayList<String>> feed = new ArrayList<ArrayList<String>>();

        PreparedStatement ps;
        ResultSet rs;

        ps = connection.prepareStatement(Query.GLOBAL_FEED);
        rs = ps.executeQuery();

        while(rs.next()){

            ArrayList<String> row = new ArrayList<>();
            row.add(0, rs.getString(Query.GET_FULL_NAME));
            row.add(1, rs.getString(Query.GET_POST_CONTENT));
            row.add(2, rs.getString(Query.GET_POST_DATE));
            row.add(3, rs.getString(Query.GET_IMAGE));
            feed.add(row);
            
        }

        ps.close();
        rs.close();


        return feed;
    
    }
    
    /**
     * 
     * @return user feed
     * @throws SQLException
     * 
     * Gathers all user posts and returns an ArrayList of strings
     * to store in local storage for the scope of the program
     * 
     * Any new posts will be retained in local storage to
     * limit the number of queries 
     */
    public ArrayList<ArrayList<String>> getUserFeed() throws SQLException{
        
        ArrayList<ArrayList<String>> feed = new ArrayList<ArrayList<String>>();

        // feed.add(new ArrayList<String>());

        PreparedStatement ps;
        ResultSet rs;

        ps = connection.prepareStatement(Query.USER_FEED);
        ps.setString(1, CurrentUser.username);
        rs = ps.executeQuery();

        while(rs.next()){
            ArrayList<String> row = new ArrayList<>();
            row.add(Index.POST_ID, String.valueOf(rs.getInt(Query.GET_POSTID)));
            row.add(Index.POST_USERNAME, rs.getString(Query.GET_USERNAME));
            row.add(Index.POST_NAME, rs.getString(Query.GET_FULL_NAME));
            row.add(Index.POST_CONTENT, rs.getString(Query.GET_POST_CONTENT));
            row.add(Index.POST_DATE, rs.getString(Query.GET_POST_DATE));
            row.add(Index.POST_URL, rs.getString(Query.GET_IMAGE));
            feed.add(row);

        }

        ps.close();
        rs.close();

        return feed;
    
    }

    /**
     * 
     * @return
     * @throws SQLException
     */
    public ArrayList<FriendPane> getFriends() throws SQLException{

        ResultSet rs;
        PreparedStatement ps;

        ArrayList<FriendPane> friends = new ArrayList<>();
        FriendPane friend;

        ps = connection.prepareStatement(Query.USER_FRIENDS);
        rs = ps.executeQuery();

        while(rs.next()){

            if(rs.getString(Query.GET_USERNAME).equals(CurrentUser.username)){
                continue;
            }

            friend = new FriendPane
            (
                rs.getString(Query.GET_FIRST_NAME),
                rs.getString(Query.GET_USERNAME),
                rs.getString(Query.GET_IMAGE)
            );

            friends.add(friend);
        }

        ps.close();
        rs.close();

        return friends;
    }
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

        return data;
    }

    // /**
    //  * 
    //  * @param club
    //  * @throws SQLException
    //  */
    // public void createClub(Club club) throws SQLException{

    //     PreparedStatement ps;

    //     String hashedPassword = hash(club.getPassword());
    //     connection = dbHelper.getConnection();

    //     ps = connection.prepareStatement(Insert.NEW_CLUB);
    //     ps.setString(1, club.getEmail());
    //     ps.setString(2, hashedPassword);
    //     ps.setString(3, club.getName());
    //     ps.setString(4, club.getPurpose());
    //     ps.setString(5, club.getMainContact());
    //     ps.setString(6, club.getWebsite());
    //     ps.setString(7, club.getIcon().getUrl());

    //     ps.executeUpdate();
    //     ps.close();

    // }

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
            Flag.DEBUG(e.getCause().toString());
        }

        return generatedPassword;
    } 
}
