package social.Objects;



public class Post {
    
    private String username;
    private String name;
    private String content;
    private String imageURL;
    private String date;

    public Post(String username, String name, String content,  String date, String imageURL){

        this.name = name;
        this.content = content;
        this.imageURL = imageURL;
        this.date = date;

    }

    public String getUsername(){
        return this.username;
    }
    
    public String getCreatorName(){
        return this.name;
    }

    public String getContent(){
        return this.content;
    }

    public String getImageURL(){
        return this.imageURL;
    }

    public String getDate(){
        return this.date;
    }
}
