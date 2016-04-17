package services.valueobjects;

/**
 * Created by Riaan on 4/13/2016.
 */
public class Address {
    private int no;
    private String street;
    private String suburb;
    private String city;
    private String country;
    private int postalCode;


    public Address(int no, String street, String suburb, String city, String country, int postalCode) {
        this.no = no;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Address(int no, String street, String city, String country, int postalCode) {
        this.no = no;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.suburb = "";
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String toString(){
        if(!suburb.equals(""))
            return no + ", " + street + ", " + suburb + ", " + city + ", " + country + ", " + postalCode;
        else
            return no + ", " + street + ", " + city + ", " + country + ", " + postalCode;
    }
}
