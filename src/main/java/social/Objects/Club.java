package social.Objects;


public class Club {
    
    private String name;
    private String purpose;
    private String mainContact;
    private String website;
    private String email;
    private String password;
    private String icon;

    // User for club creation
    public Club(String name, String purpose, String mainContact, String website, String email, String password, String icon) {
        
        this.name = name;
        this.purpose = purpose;
        this.mainContact = mainContact;
        this.website = website;
        this.email = email;
        this.password = password;
        this.icon = icon;

    }

    public Club(String name, String purpose, String mainContact, String website, String email, String icon){

        this.name = name;
        this.purpose = purpose;
        this.mainContact = mainContact;
        this.website = website;
        this.email = email;
        this.icon = icon;
    }

    public Club(String name, String email, String icon){

        this.name = name;
        this.email = email;
        this.icon = icon;

    }

    public String getName(){
        return this.name;
    }

    public String getPurpose(){
        return this.purpose;
    }

    public String getMainContact(){
        return this.mainContact;
    }

    public String getWebsite(){
        return this.website;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getIcon(){
        return this.icon;
    }
}
