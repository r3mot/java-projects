package social.Database.Newer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import social.Objects.Club;
import social.Objects.CurrentUser;
import social.Objects.ExploreClub;
import social.Objects.ExploreUser;
import social.Objects.FriendRequest;
import social.Objects.NotificationPane;
import social.Objects.User;

public class FriendshipManager {
    
    private ConnectionManager connection = new ConnectionManager();


    /**
     * 
     * @param to requested friend
     * 
     * Sends a friend request to another user
     * Stores user in "Friendship" table to determine the status of the friendship
     */
    public void sendFriendRequest(String to){

        PreparedStatement ps;

        String query = "INSERT INTO Friendship(SendingUserId, Sender, Reciever, Status) VALUES (?,?,?,?)";


        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setInt(1, CurrentUser.getUserId());
            ps.setString(2, CurrentUser.getUsername());
            ps.setString(3, to);
            ps.setInt(4, 0);
            ps.executeUpdate();

            ps.close();
            connection.closeConnection();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @param username of the user who request the friendship
     * 
     */
    public void acceptFriendRequest(String username){
        
        PreparedStatement ps;
        String query = "UPDATE Friendship SET Status=? WHERE Sender=? AND Reciever=?";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setInt(1, 1);
            ps.setString(2, username);
            ps.setString(3, CurrentUser.getUsername());
            ps.executeUpdate();
            ps.close();

            connection.closeConnection();
        }
        catch(SQLException e) { e.printStackTrace(); }

        updateFriendship(username);
        
    }

    /**
     * 
     * @param username of the user who request the friendship
     * 
     * Updates "Friendship" table to reflect the relationship status
     */
    private void updateFriendship(String username){

        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM Friendship WHERE Sender=? AND Reciever=?";
        String sender = "";
        String reciever = "";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, CurrentUser.getUsername());
            rs = ps.executeQuery();

            while(rs.next()){
                sender = rs.getString("Sender");
                reciever = rs.getString("Reciever");
            }

            rs.close();
            ps.close();
            connection.closeConnection();

            addFriend(sender, reciever);
            addFriend(reciever, sender);

        }
        catch(SQLException e) { e.printStackTrace(); }
    }

    /**
     * 
     * @param sender username
     * @param reciever username
     * 
     * Updates the "UserFriend" table to reflect their friendship
     * The send and reciever will now be displayed in their respective
     * friends list
     */
    private void addFriend(String sender, String reciever){

        PreparedStatement ps;
        String query = "INSERT INTO UserFriend(Username, FriendUserName) VALUES (?,?)";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, sender);
            ps.setString(2, reciever);
            ps.executeUpdate();
            ps.close();
            connection.closeConnection();

        }
        catch(SQLException e) { e.printStackTrace(); }

    }

    /**
     * 
     * @return friends ship status (0 or 1)
     */
    public ArrayList<String> getFriendships(){

        ArrayList<String> friends = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        String query = "SELECT FriendUserName FROM UserFriend WHERE Username=?";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, CurrentUser.getUsername());
            rs = ps.executeQuery();

            while(rs.next()){
                friends.add(rs.getString("FriendUserName"));
            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) { e.printStackTrace(); }

        return friends;

    }

    /**
     * 
     * @return all pending friend requests
     * 
     * Stores the request in a NotificationPane object
     * with the information about the requesting user
     * 
     * This will be displayed in the notification
     * section of the dashboard
     */
    public ArrayList<NotificationPane> getFriendRequests(){

        PreparedStatement ps;
        ResultSet rs;
        
        ArrayList<FriendRequest> pending = getNotifications();
        ArrayList<NotificationPane> requests = new ArrayList<NotificationPane>();
        User user = null;

        String query = "SELECT UserId, Username, FirstName, LastName, Picture FROM UserProfile WHERE Username=?";

        try {

      
            for(int i = 0; i < pending.size(); i++){

                ps = connection.getConnection().prepareStatement(query);
                ps.setString(1, pending.get(i).getSenderUserName());
                rs = ps.executeQuery();

                while(rs.next()){
                    user = new User(
                        rs.getInt("UserId"),
                        rs.getString("Username"),
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
                        rs.getString("Picture"));
                }

                requests.add(new NotificationPane(user));

                ps.close();
                rs.close();
                connection.closeConnection();

            }
         
        }
        catch(SQLException e) { e.printStackTrace(); }
        
        return requests;
    }

    /**
     * 
     * @param username
     * @return friendship status
     */
    public boolean notFriends(String username){

        PreparedStatement ps;
        ResultSet rs;

        String query = "SELECT Username, FriendUserName FROM UserFriend WHERE FriendUserName=?";

        boolean result = true;

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while(rs.next()){
                
                String user = rs.getString("Username");
                String friend = rs.getString("FriendUserName");

                if(user.equals(CurrentUser.getUsername()) && friend.equals(username)){
                    result = false;
                }
            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) {}

        return result;
    }


    /**
     * Helper method
     * @return user profile request
     */
    private ArrayList<User> queryUsers(){

        ArrayList<User> users = new ArrayList<User>();
        PreparedStatement ps;
        ResultSet rs;
        User user;

        String query = "SELECT Username, FirstName, LastName, Picture FROM UserProfile ORDER BY FirstName ASC";

        try {

            ps = connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){

                if(rs.getString("Username").equals(CurrentUser.getUsername())){
                    continue;
                }
                String username = rs.getString("Username");
                String firstname = rs.getString("FirstName");
                String lastname = rs.getString("LastName");
                String picture = rs.getString("Picture");

                user = new User(username, firstname, lastname, picture);
                users.add(user);
            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) { e.printStackTrace();}

        return users;
    }

    /**
     * 
     * @param user
     * @return pending status
     */
    private boolean pendingRequest(User user){

        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT Sender, Reciever, Status FROM Friendship";

        boolean result = false;

        try {
            ps = connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                
                int status = rs.getInt("Status");
                String sender = rs.getString("Sender");
                String reciever = rs.getString("Reciever");

                if((status == 0)){
                    result = (user.getUsername().equals(sender) || user.getUsername().equals(reciever));
                }

            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) {}

        return result;

    }

    /**
     * 
     * @return non-friend users
     */
    public ArrayList<ExploreUser> getExploreStudents(){

        ArrayList<User> users = queryUsers();
        ArrayList<ExploreUser> explorer = new ArrayList<ExploreUser>();
        
        for(int i = 0; i < users.size(); i++){
            if(notFriends(users.get(i).getUsername()) && !pendingRequest(users.get(i))){
                explorer.add(new ExploreUser(users.get(i)));
            }
        }

        return explorer;
    }

    /**
     * 
     * @return non-joined clubs
     */
    public ArrayList<ExploreClub> getExploreClubs(){

        ArrayList<Club> clubs = getClubs();
        ArrayList<ExploreClub> explorer = new ArrayList<ExploreClub>();

        for(int i = 0; i < clubs.size(); i++){
            if(!notSignedUp(clubs.get(i))){
                explorer.add(new ExploreClub(clubs.get(i)));
            }
        }

        return explorer;
    }

    /**
     * Helper Method
     * @param club
     * @return club affiliation status
     */
    private boolean notSignedUp(Club club){

        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT Username, ClubEmail FROM UserClub WHERE ClubEmail=?";

        boolean result = false;

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, club.getEmail());
            rs = ps.executeQuery();

            while(rs.next()){
                
                String username = rs.getString("Username");
                String email = rs.getString("ClubEmail");

                if(username.equals(CurrentUser.getUsername()) && email.equals(club.getEmail())){
                    result = true;
                }
            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) {}

        return result;
    }

    /**
     * 
     * @param email of club to join
     * 
     * When a user joins a club,
     * the "UserClub" table is updated to reflect this change
     */
    public void joinClub(String email){

        PreparedStatement ps;

        String query = "INSERT INTO UserClub(Username, ClubEmail) VALUES (?,?)";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, CurrentUser.getUsername());
            ps.setString(2, email);
            ps.executeUpdate();
            
            ps.close();
            connection.closeConnection();

        }
        catch(SQLException e) { e.printStackTrace(); }
    }

    /**
     * Helper Method
     * @return all clubs
     */
    private ArrayList<Club> getClubs(){
        
        ArrayList<Club> clubs = new ArrayList<Club>();
        PreparedStatement ps;
        ResultSet rs;
        Club club = null;

        String query = "SELECT Email, Name, Purpose, MainContact, Website, Picture FROM ClubProfile";

        try {
            ps = connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){

                String email = rs.getString("Email");
                String name = rs.getString("Name");
                String purpose = rs.getString("Purpose");
                String mainContact = rs.getString("MainContact");
                String website = rs.getString("Website");
                String imageURL = rs.getString("Picture");

                club = new Club(name, purpose, mainContact, website, email, imageURL);
                clubs.add(club);
            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) {}

        return clubs;
    }

    /**
     * 
     * @return all clubs the user is affiliated with
     */
    public ArrayList<String> getUserClubs(){

        ArrayList<String> clubs = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        String query = "SELECT ClubEmail FROM UserClub WHERE Username=?";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, CurrentUser.getUsername());
            rs = ps.executeQuery();

            while(rs.next()){
                clubs.add(rs.getString("ClubEmail"));
            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) { e.printStackTrace(); }


        return clubs;
    }

    /**
     * 
     * @return all friend requests
     */
    private ArrayList<FriendRequest> getNotifications(){

        PreparedStatement ps;
        ResultSet rs;
        
        ArrayList<FriendRequest> requests = new ArrayList<FriendRequest>();
        FriendRequest request;

        String query = "SELECT * FROM Friendship WHERE Reciever=? AND Status=?";

        try {
            ps = connection.getConnection().prepareStatement(query);
            ps.setString(1, CurrentUser.getUsername());
            ps.setInt(2, 0);
            rs = ps.executeQuery();

            while(rs.next()){
                request = new FriendRequest(
                    rs.getInt("SendingUserId"),
                    rs.getString("Sender"),
                    rs.getString("Reciever"),
                    rs.getInt("Status"));

                requests.add(request);
            }

            ps.close();
            rs.close();
            connection.closeConnection();
        }
        catch(SQLException e) {}

        return requests;
    }

}
