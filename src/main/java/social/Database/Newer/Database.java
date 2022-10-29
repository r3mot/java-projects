package social.Database.Newer;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import social.Objects.Club;
import social.Objects.ClubPane;
import social.Objects.CurrentUser;
import social.Objects.FeedPane;
import social.Objects.FriendPane;
import social.Objects.Post;
import social.Objects.User;

public class Database {

    private ConnectionManager connection = new ConnectionManager();
    private FriendshipManager friendMngr = new FriendshipManager();

    /**
     * 
     * @param user
     * 
     *             Create user credentials
     */
    public void createUser(User user) {

        PreparedStatement ps;
        String hashedPassword = hash(user.getPassword());
        String query = "INSERT INTO Users(Username, Password) VALUES (?,?)";
        try {

            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, hashedPassword);
            ps.executeUpdate();
            ps.close();
            connection.closeConnection();

            addUserProfile(user);

        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    /**
     * 
     * @param user
     * 
     *             Add user information to database
     */
    private void addUserProfile(User user) {

        PreparedStatement ps;
        String query = "INSERT INTO UserProfile(Username, FirstName, LastName, Major, Standing, GradYear, DreamJob, Picture, UserId) VALUES (?,?,?,?,?,?,?,?,?)";

        int maxStudentId = getMaxStudentID();
        int maxClubId = getMaxClubId();
        int id = maxStudentId > maxClubId ? maxStudentId + 1 : maxClubId + 1;

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getMajor());
            ps.setString(5, user.getStanding());
            ps.setString(6, user.getYear());
            ps.setString(7, user.getDreamJob());
            ps.setString(8, user.getImage());
            ps.setInt(9, id);
            ps.executeUpdate();
            ps.close();
            connection.closeConnection();

            CurrentUser.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param club
     * 
     *             Create club credentials
     */
    public void createClub(Club club) {

        PreparedStatement ps;
        String hashedPassword = hash(club.getPassword());
        String query = "INSERT INTO Clubs(Email, Password) VALUES (?,?)";
        try {

            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, club.getEmail());
            ps.setString(2, hashedPassword);
            ps.executeUpdate();
            ps.close();
            connection.closeConnection();

            addClubProfile(club);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param club
     * 
     *             Add club information to database
     */
    private void addClubProfile(Club club) {

        PreparedStatement ps;
        String query = "INSERT INTO ClubProfile(ClubId, Email, Name, Purpose, MainContact, Website, Picture) VALUES (?,?,?,?,?,?,?)";

        int maxStudentId = getMaxStudentID();
        int maxClubId = getMaxClubId();
        int id = maxStudentId > maxClubId ? maxStudentId + 1 : maxClubId + 1;

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, club.getEmail());
            ps.setString(3, club.getName());
            ps.setString(4, club.getPurpose());
            ps.setString(5, club.getMainContact());
            ps.setString(6, club.getWebsite());
            ps.setString(7, club.getIcon());

            ps.executeUpdate();
            ps.close();
            connection.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param usernameLookup
     * @return
     * 
     *         Get all profile information for a user
     */
    public User getUserProfile(String usernameLookup) {

        PreparedStatement ps;
        ResultSet rs;
        User profile = null;

        String query = "SELECT Username, FirstName, LastName, Major, Standing, GradYear, DreamJob, Picture FROM UserProfile WHERE Username=?";

        try {

            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, usernameLookup);
            rs = ps.executeQuery();

            while (rs.next()) {

                String username = rs.getString("Username");
                String firstname = rs.getString("FirstName");
                String lastname = rs.getString("LastName");
                String major = rs.getString("Major");
                String standing = rs.getString("Standing");
                String gradYear = rs.getString("GradYear");
                String dreamJob = rs.getString("DreamJob");
                String picture = rs.getString("Picture");

                profile = new User(username, "", firstname, lastname, major, standing, gradYear, dreamJob, picture);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profile;

    }

    /**
     * 
     * @return all users in database
     */
    public ArrayList<User> getAllUsers() {

        ArrayList<User> allUsers = new ArrayList<User>();

        PreparedStatement ps;
        ResultSet rs;
        User user;

        String query = "SELECT Username, FirstName, LastName, Picture FROM UserProfile";

        try {

            ps = connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String firstname = rs.getString("FirstName");
                String lastname = rs.getString("LastName");
                String picture = rs.getString("Picture");

                user = new User(username, "", firstname, lastname, "", "", "", "", picture);
                allUsers.add(user);
            }
        } catch (SQLException e) {
        }

        return allUsers;
    }

    /**
     * 
     * @param post
     * 
     *             Adds a post (found in the feed) to the database
     */
    public void addUserPost(Post post) {

        PreparedStatement ps;
        String query = "INSERT INTO UserPost(Username, Name, Content, Date, Picture) VALUES (?,?,?,?,?)";

        try {

            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, CurrentUser.getUsername());
            ps.setString(2, CurrentUser.getFullName());
            ps.setString(3, post.getContent());
            ps.setString(4, post.getDate());
            ps.setString(5, CurrentUser.getPictureUrl());
            ps.executeUpdate();

            ps.close();
            connection.closeConnection();

        } catch (SQLException e) {
        }
    }

    /**
     * 
     * @return posts from all users
     */
    public ArrayList<FeedPane> getGlobalFeed() {

        ArrayList<FeedPane> globalFeed = new ArrayList<FeedPane>();

        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT Username, Name, Content, Date, Picture FROM UserPost";
        Post post;

        try {
            ps = connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                String username = rs.getString("Username");
                String name = rs.getString("Name");
                String content = rs.getString("Content");
                String date = rs.getString("Date");
                String picture = rs.getString("Picture");

                post = new Post(username, name, content, date, picture);
                globalFeed.add(new FeedPane(post));
            }

            ps.close();
            rs.close();
            connection.closeConnection();

        } catch (SQLException e) {
        }

        return globalFeed;

    }

    /**
     * 
     * @return posts made by the user
     */
    public ArrayList<FeedPane> getUserFeed() {

        ArrayList<FeedPane> feed = new ArrayList<FeedPane>();
        Post post;

        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT Username, Name, Content, Date, Picture FROM UserPost WHERE Username=?";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, CurrentUser.getUsername());
            rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String name = rs.getString("Name");
                String content = rs.getString("Content");
                String date = rs.getString("Date");
                String picture = rs.getString("Picture");

                post = new Post(username, name, content, date, picture);
                feed.add(new FeedPane(post));
            }

            ps.close();
            rs.close();
            connection.closeConnection();

        } catch (SQLException e) {
        }

        return feed;

    }

    /**
     * 
     * @return user friendlist
     */
    public ArrayList<FriendPane> getUserFriends() {

        PreparedStatement ps;
        ResultSet rs;

        ArrayList<String> friends = friendMngr.getFriendships();
        ArrayList<FriendPane> userfriends = new ArrayList<FriendPane>();
        User user;

        String query = "SELECT Username, FirstName, LastName, Major, Standing, GradYear, DreamJob, Picture FROM UserProfile WHERE Username=?";

        try {
            ps = connection.getConnection().prepareStatement(query);
            for (int i = 0; i < friends.size(); i++) {
                ps.setString(1, friends.get(i));
                rs = ps.executeQuery();

                while (rs.next()) {
                    String username = rs.getString("Username");
                    String firstname = rs.getString("FirstName");
                    String lastname = rs.getString("LastName");
                    String major = rs.getString("Major");
                    String standing = rs.getString("Standing");
                    String year = rs.getString("GradYear");
                    String job = rs.getString("DreamJob");
                    String picture = rs.getString("Picture");

                    user = new User(username, firstname, lastname, major, standing, year, job, picture);
                    userfriends.add(new FriendPane(user));
                }
            }

            connection.closeConnection();
        } catch (SQLException e) {
        }

        return userfriends;
    }

    /**
     * 
     * @return clubs user has joined
     */
    public ArrayList<ClubPane> getUserClubs() {

        ArrayList<String> clubs = friendMngr.getUserClubs();
        ArrayList<ClubPane> userClubs = new ArrayList<ClubPane>();

        PreparedStatement ps;
        ResultSet rs;
        Club club;

        String query = "SELECT Email, Name, Purpose, MainContact, Website, Picture FROM ClubProfile WHERE Email=?";

        try {
            ps = connection.getConnection().prepareStatement(query);
            for (int i = 0; i < clubs.size(); i++) {
                ps.setString(1, clubs.get(i));
                rs = ps.executeQuery();

                while (rs.next()) {

                    String email = rs.getString("Email");
                    String name = rs.getString("Name");
                    String purpose = rs.getString("Purpose");
                    String mainContact = rs.getString("MainContact");
                    String website = rs.getString("Website");
                    String imageURL = rs.getString("Picture");

                    club = new Club(name, purpose, mainContact, website, email, imageURL);
                    userClubs.add(new ClubPane(club));
                }
            }

            ps.close();
            connection.closeConnection();
        } catch (SQLException e) {
        }

        return userClubs;

    }

    /**
     * 
     * @return current highest id
     * 
     *         User to determine the new ID given on account creation
     */
    private int getMaxStudentID() {

        PreparedStatement ps;
        ResultSet rs;

        String query = "SELECT * FROM UserProfile ORDER BY UserId DESC LIMIT 1";

        int number = 0;

        try {
            ps = connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                number = rs.getInt("UserId");
            }
        } catch (SQLException e) {
        }

        return number;
    }

    /**
     * 
     * @return current highest id
     * 
     *         User to determine the new ID given on account creation
     */
    private int getMaxClubId() {

        PreparedStatement ps;
        ResultSet rs;

        String query = "SELECT * FROM UserClub ORDER BY ClubId DESC LIMIT 1";

        int number = 0;

        try {
            ps = connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                number = rs.getInt("ClubId");
            }
        } catch (SQLException e) {
        }

        return number;
    }

    /**
     * 
     * @return uploaded image file
     */
    public File uploadImage() {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        return file;

    }

    /**
     * 
     * @param passwordToHash raw password entry
     * @return
     * 
     *         Hashes password as to not store plain text
     *         May also be used to decode a previously hashed password
     */
    private String hash(String passwordToHash) {

        MessageDigest md;
        StringBuilder sb;

        String generatedPassword = null;
        try {

            md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            md.update(bytes);
            sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
        }

        return generatedPassword;
    }
}
