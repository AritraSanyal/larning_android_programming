package Model;

public class Contact {

    private String name;
    private String phoneNo;
    private int id;


    public Contact() {


    }

    public Contact(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public Contact(String name, String phoneNo, int id) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
