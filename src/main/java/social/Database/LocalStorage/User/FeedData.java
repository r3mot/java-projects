package social.Database.LocalStorage.User;

import java.sql.SQLException;
import java.util.ArrayList;

import social.Database.Database;
import social.Database.QueryStrings.Index;

public class FeedData {

    private ArrayList<ArrayList<String>> feedData;
    private Database db;

    public FeedData() throws SQLException {

        this.db = new Database();
        this.feedData = db.getUserFeed();

    }

     // Feed
     public String getPostID(int postNum){
        return this.feedData.get(postNum).get(Index.POST_ID);
    }

    public String getPostUsername(int postNum){
        return this.feedData.get(postNum).get(Index.POST_USERNAME);
    }

    public String getPostContent(int postNum){
        return this.feedData.get(postNum).get(Index.POST_CONTENT);
    }

    public String getPostName(int postNum){
        return this.feedData.get(postNum).get(Index.POST_NAME);
    }

    public String getPostDate(int postNum){
        return this.feedData.get(postNum).get(Index.POST_DATE);
    }

    public String getPostImage(int postNum){
        return this.feedData.get(postNum).get(Index.POST_URL);
    }

    public int getNumPosts(){
        return this.feedData.size();
    }
    
}
