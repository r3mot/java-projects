package social.Objects;

public class User {
    
    private String firstName;
    private String lastName;
    private String major;
    private String standing;
    private String year;
    private String dreamJob;
    private String username;
    private String password;
    private String image;
    private int userId;

    public User(String username, String password, String firstName, String lastName, String major, String standing, String year, String dreamJob, String image){
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.standing = standing;
        this.year = year;
        this.dreamJob = dreamJob;
        this.image = image;
        this.username = username;
        this.password = password;
    }

    public User(String username, String firstName, String lastName, String major, String standing, String year, String dreamJob, String image){
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.standing = standing;
        this.year = year;
        this.dreamJob = dreamJob;
        this.image = image;
        this.username = username;

    }

    public User(int userId, String username, String firstName, String lastName, String image){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.userId = userId;
    }

    public User(String username, String firstName, String lastName, String image){
        
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;

    }

    public void setImage(String url){
        this.image = url;
    }
    
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public int getUserId(){
        return this.userId;
    }
    
    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getMajor(){
        return this.major;
    }

    public String getStanding(){
        return this.standing;
    }

    public String getYear(){
        return this.year;
    }

    public String getDreamJob(){
        return this.dreamJob;
    }

    public String getImage(){
        return this.image;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

}
