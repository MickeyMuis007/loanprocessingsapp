package Domain;

import services.valueobjects.Address;

/**
 * Created by Riaan on 4/15/2016.
 */
public class Branch {
    private long branchNo;
    private String name;
    private Address address;

    public Branch(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
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

    public long getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(long branchNo) {
        this.branchNo = branchNo;
    }
}
