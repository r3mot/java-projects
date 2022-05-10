package social.Database.LocalStorage;

import java.sql.SQLException;
import java.util.ArrayList;

import social.Database.Database;

public class UserData {

    private final int USERNAME = 0;
    private final int FIRSTNAME = 1;
    private final int LASTNAME = 2;
    private final int MAJOR = 3;
    private final int STANDING = 4;
    private final int YEAR = 5;
    private final int DREAM_JOB = 6;
    private final int IMAGE = 7;
    private final int CLUBS = 8;
    
    private ArrayList<String> data;
    private Database db;

    public UserData() throws SQLException {

        this.db = new Database();
        this.data = db.getUserData();

    }

    // public ArrayList<String> getData(){
    //     return this.data;
    // }

    public String getFirstName(){
        return this.data.get(FIRSTNAME);
    }

    public String getLastName(){
        return this.data.get(LASTNAME);
    }

    public String getMajor(){
        return this.data.get(MAJOR);
    }

    public String getStanding(){
        return this.data.get(STANDING);
    }

    public String getYear(){
        return this.data.get(YEAR);
    }

    public String getDreamJob(){
        return this.data.get(DREAM_JOB);
    }

    public String getImageURL(){
        return this.data.get(IMAGE);
    }

    public String getClubs(){
        return this.data.get(CLUBS);
    }


}
