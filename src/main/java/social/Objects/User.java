package social.Objects;

import javafx.scene.image.Image;

public class User {
    
    private String firstName;
    private String lastName;
    private String major;
    private String standing;
    private String year;
    private String dreamJob;
    private String username;
    private String password;
    private Image image;

    public User(String firstName, 
                    String lastName, 
                    String major, 
                    String standing, 
                    String year, 
                    String dreamJob,
                    Image image,
                    String username,
                    String password)
    {
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

    public Image getImage(){
        return this.image;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
