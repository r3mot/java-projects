package social.Database.QueryStrings;

public class Insert {
    
    public static final String NEW_USER = "INSERT INTO USERS(USERNAME, PASSWORD, F_NAME, L_NAME, MAJOR, STANDING, YEAR, D_JOB, IMAGE) VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String NEW_POST = "INSERT INTO POSTS(USERNAME, NAME, CONTENT, DATE, IMAGE, LIKES) VALUES (?,?,?,?,?,?)";
}
