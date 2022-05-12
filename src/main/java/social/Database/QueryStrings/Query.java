package social.Database.QueryStrings;

public class Query {

    private Query(){}

   
    //FRIENDs
    public static final String USER_FRIENDS = "SELECT USERNAME, F_NAME, IMAGE FROM USERS";

    //USER
    public static final String GET_REQUESTED_USER = "SELECT F_NAME, L_NAME, MAJOR, STANDING, YEAR, D_JOB, IMAGE FROM USERS WHERE USERNAME=?";

    // CLUBS
    public static final String VALID_CLUB_LOGIN = "SELECT * FROM CLUBS WHERE EMAIL=? AND PASSWORD=?";
    public static final String GET_CLUB_EMAIL = "EMAIL";
    public static final String GET_CLUB_NAME = "NAME";
    public static final String GET_CLUB_PURPOSE = "PURPOSE";
    public static final String GET_CLUB_CONTACT = "CONTACT";
    public static final String GET_CLUB_WEBSITE = "WEBSITE";
    public static final String GET_CLUB_ICON = "ICON";
    public static final String GET_THE_CLUBS = "SELECT * FROM CLUBS";
    public static final String GET_ALL_CLUBS = "SELECT EMAIL, NAME, PURPOSE, CONTACT, WEBSITE, ICON FROM CLUBS";
    public static final String GET_CLUB = "SELECT EMAIL, NAME, PURPOSE, CONTACT, WEBSITE, ICON FROM CLUBS WHERE EMAIL=?";
    public static final String ADD_CLUB = "INSERT CLUBS INTO USERS WHERE USERNAME=?";

    // FEED
    public static final String GLOBAL_FEED = "SELECT NAME, CONTENT, DATE, IMAGE FROM POSTS";
    public static final String USER_FEED = "SELECT * FROM POSTS WHERE USERNAME=?";
    public static final String GET_POSTID = "POSTID";
    public static final String GET_POST_CONTENT = "CONTENT";
    public static final String GET_POST_DATE = "DATE";
    public static final String GET_FULL_NAME = "NAME";


    public static final String GET_USERNAME = "USERNAME";
    public static final String GET_PASSWORD = "PASSWORD";
    public static final String GET_FIRST_NAME = "F_NAME";
    public static final String GET_LAST_NAME = "L_NAME";
    public static final String GET_MAJOR = "MAJOR";
    public static final String GET_STANDING = "STANDING";
    public static final String GET_YEAR = "YEAR";
    public static final String GET_DREAM_JOB = "D_JOB";
    public static final String GET_IMAGE = "IMAGE";
    public static final String GET_CLUBS = "CLUBS";
    public static final String VALID_USER_LOGIN = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
    public static final String ALL_COLUMNS_BY_USERNAME = "SELECT * FROM USERS WHERE USERNAME=?";

}
