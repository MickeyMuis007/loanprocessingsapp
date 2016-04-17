package Domain;


import services.valueobjects.Address;
import services.valueobjects.FullName;

/**
 * Created by Riaan on 4/15/2016.
 */
public class User {
    private long userNo;
    private String type;
    private FullName fullName;
    private Address address;
    private Branch branch;

    public User(String type) {
        this.type = type;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public long getUserNo() {
        return userNo;
    }

    public void setUserNo(long userNo) {
        this.userNo = userNo;
    }

    public String getType() {
        return type;
    }



    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }
    public void setFullName(String name, String middleName, String lastName){
        fullName = new FullName(name,middleName,lastName);
    }
    public void setFullName(String name, String lastName){
        fullName = new FullName(name,lastName);
    }
    public void setFullName(String name){
        fullName = new FullName(name);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public void setAddress(int no, String street, String suburb, String city, String country, int postalCode){
        address = new Address(no,street,suburb,city,country,postalCode);
    }
    public void setAddress(int no, String street, String city, String country, int postalCode){
        address = new Address(no,street,city,country,postalCode);
    }
}
