package social.Database.LocalStorage.Global;

import java.sql.SQLException;
import java.util.ArrayList;

import social.Database.Database;
import social.Objects.Post;

public class GlobalFeedData {

    private ArrayList<ArrayList<String>> feedData;
    private Database db;

    public GlobalFeedData() throws SQLException{

        db = new Database();

        feedData = db.getGlobalFeed();

    }
    
    public void addPost(Post post){

        String name = post.getPostName();
        String content = post.getPostContent();
        String date = post.getPostDate();
        String url = post.getPostURL();

        ArrayList<String> row = new ArrayList<>();

        row.add(name);
        row.add(content);
        row.add(date);
        row.add(url);

        feedData.add(row);   

    }
    

    public String getPostContent(int postNum){
        return this.feedData.get(postNum).get(1);
    }

    public String getPostName(int postNum){
        return this.feedData.get(postNum).get(0);
    }

    public String getPostDate(int postNum){
        return this.feedData.get(postNum).get(2);
    }

    public String getPostImage(int postNum){
        return this.feedData.get(postNum).get(3);
    }

    public int getNumPosts(){
        return this.feedData.size();
    }
    
}
