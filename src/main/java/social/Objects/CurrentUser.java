package social.Objects;

public class CurrentUser {
    
    private CurrentUser() {}

    public static String username;
    public static String name;
    public static String imageURL;

    public static void setUsername(String un){
        username = un;
    }

    public static void setName(String first, String last){
        name = first + " " + last;
    }

    public static void setImage(String url){
        imageURL = url;
    }

}
