package social.Database.QueryStrings;

public class Query {

    private Query(){}

    public static final String VALID_LOGIN = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
    public static final String ALL_COLUMNS_BY_USERNAME = "SELECT * FROM USERS WHERE USERNAME=?";

    public static final String GET_USERNAME = "USERNAME";
    public static final String GET_PASSWORD = "PASSWORD";
    public static final String GET_FIRST_NAME = "F_NAME";
    public static final String GET_LAST_NAME = "L_NAME";

}
