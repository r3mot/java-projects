package social.Objects;

public class CurrentUser {
    
    private static User user;
    private static int userId;

    private CurrentUser(){}

    public static void setUser(User userIn){
        user = userIn;
    }

    public static void setUserId(int id){
        userId = id;
    }

    public static void setCurrentImage(String url){
        user.setImage(url);
    }

    public static int getUserId(){
        return userId;
    }

    public static String getUsername(){
        return user.getUsername();
    }

    public static String getFirstName(){
        return user.getFirstName();
    }

    public static String getLastName(){
        return user.getLastName();
    }

    public static String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public static String getMajor(){
        return user.getMajor();
    }

    public static String getStanding(){
        return user.getStanding();
    }

    public static String getGradYear(){
        return user.getYear();
    }

    public static String getDreamJob(){
        return user.getDreamJob();
    }

    public static String getPictureUrl(){
        return user.getImage();
    }

    public static String currentUserString(){
        String current =  user.getUsername() + " " +
            user.getFirstName() +  " " +
            user.getLastName() + " " + 
            user.getMajor() + " " +
            user.getStanding() + " " +
            user.getYear() + " " +
            user.getDreamJob() + " " +
            user.getImage();

        return current;
    }
}
