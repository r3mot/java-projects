package social.Database.QueryStrings;

public class Query {

    private Query(){}

    public final static String VALID_LOGIN = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";

    public final static String USERNAME = "USERNAME";
    public final static String PASSWORD = "PASSWORD";
    public final static String FIRST_NAME = "F_NAME";
    public final static String LAST_NAME = "L_NAME";

}
