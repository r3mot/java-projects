package social.Objects;

public class FriendRequest {

    private int sendingUserId;
    private String sender;
    private String reciever;
    private int status;

    public FriendRequest(int sendingUserId, String sender, String reciever, int status){
        this.sendingUserId = sendingUserId;
        this.sender = sender;
        this.reciever = reciever;
        this.status = status;
    }
    
    public int getSenderId(){
        return this.sendingUserId;
    }

    public String getSenderUserName(){
        return this.sender;
    }

    public String getReceivingUserName(){
        return this.reciever;
    }

    public int getStatus(){
        return this.status;
    }
}
