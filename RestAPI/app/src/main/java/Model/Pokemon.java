package Model;

public class Pokemon {

    private int id;
    private String name;
    private String type;
    private String abilities;
    private String imageURL;
    private String location;


    public Pokemon() {
    }



    public Pokemon(int id, String name, String type, String abilities, String imageURL, String location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.abilities = abilities;
        this.imageURL = imageURL;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
