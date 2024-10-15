package Model;

public class Pokemon {

    private int id;
    private String name;
    private String type;
    private String abilities;
    private String imageURL;


    public Pokemon() {
    }

    public Pokemon(int id) {
        this.id = id;
    }

    public Pokemon(String name) {
        this.name = name;
    }

    public Pokemon(int id, String name, String type, String abilities, String imageURL) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.abilities = abilities;
        this.imageURL = imageURL;
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
}
