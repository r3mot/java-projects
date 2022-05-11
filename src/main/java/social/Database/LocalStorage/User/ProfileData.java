package social.Database.LocalStorage.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import social.Database.Database;
import social.Database.QueryStrings.Index;

public class ProfileData {

    private final int USERNAME = 0;
    private final int FIRSTNAME = 1;
    private final int LASTNAME = 2;
    private final int MAJOR = 3;
    private final int STANDING = 4;
    private final int YEAR = 5;
    private final int DREAM_JOB = 6;
    private final int IMAGE = 7;
    private final int CLUBS = 8;
    
    private ArrayList<String> profileData;
    private ArrayList<ArrayList<String>> feedData;
    private Database db;

    public ProfileData() throws SQLException {

        this.db = new Database();
        this.profileData = db.getUserData();
        this.feedData = db.getUserFeed();

    }

    // Profile
    public String getFirstName(){
        return this.profileData.get(FIRSTNAME);
    }

    public String getLastName(){
        return this.profileData.get(LASTNAME);
    }

    public String getMajor(){
        return this.profileData.get(MAJOR);
    }

    public String getStanding(){
        return this.profileData.get(STANDING);
    }

    public String getYear(){
        return this.profileData.get(YEAR);
    }

    public String getDreamJob(){
        return this.profileData.get(DREAM_JOB);
    }

    public String getImageURL(){
        return this.profileData.get(IMAGE);
    }

    public String getClubs(){
        return this.profileData.get(CLUBS);
    }

}
