package social.Database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import social.Database.QueryStrings.Query;
import social.Debug.Flag;
import social.Objects.CurrentUser;

public class CredentialManager {

    private DatabaseHelper dbHelper = new DatabaseHelper();
    private Connection connection = dbHelper.getConnection();
    
        /**
     * 
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * When user attempts to login, the method will return true or false
     * The boolean nature of the method is used to determine error handling
     * on the login screen (i.e. informing the user of incorrect credentials)
     */
    public boolean login(String username, String password) throws ClassNotFoundException, SQLException{
        return loginSuccessful(username, password);
    }

        /**
     * 
     * @param un
     * @param pw
     * @return valid login
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * Checks entered username and password against
     * the database
     */
    private boolean loginSuccessful(String username, String password) throws ClassNotFoundException, SQLException{

        boolean loginResult = false;
        PreparedStatement ps;
        ResultSet rs;

        password = hash(password);

        connection = dbHelper.getConnection();
        ps = connection.prepareStatement(Query.VALID_LOGIN);
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();

        while(rs.next()){

            String user = rs.getString(Query.GET_USERNAME);
            String pass = rs.getString(Query.GET_PASSWORD);

            // Getting current full name in same query 
            String first = rs.getString(Query.GET_FIRST_NAME);
            String last = rs.getString(Query.GET_LAST_NAME);

            if(user.equals(username) && pass.equals(password)){
                loginResult = true;

                CurrentUser.setName(first, last);
                break;
            }

           
        }

        ps.close();
        rs.close();
        connection.close();

        return loginResult;
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
