package social.Database.LocalStorage;

import java.sql.SQLException;
import java.util.ArrayList;

import social.Database.Database;

public class UserData {
    
    private ArrayList<String> data;
    private Database db;

    public UserData() throws SQLException {

        this.db = new Database();
        this.data = db.getUserData();

    }

    public ArrayList<String> getData(){
        return this.data;
    }


}
