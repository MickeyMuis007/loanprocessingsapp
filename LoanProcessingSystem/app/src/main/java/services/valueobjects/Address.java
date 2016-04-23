package services.valueobjects;

/**
 * Created by Riaan on 4/13/2016.
 */
public class Address {
    private int no;
    private String street;
    private String suburb;
    private String city;
    private int postalCode;

    private Address(){}

    public Address(Builder builder)
    {
        this.no = builder.no;
        this.street = builder.street;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.postalCode =builder.postalCode;
    }

    public int getNo() {
        return no;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String toString(){
        if(!suburb.equals(""))
            return no + ", " + street + ", " + suburb + ", " + city + ", " + postalCode;
        else
            return no + ", " + street + ", " + city + ", " + postalCode;
    }
    public static class Builder
    {
        private int no;
        private String street;
        private String suburb;
        private String city;
        private int postalCode;

        public Builder(){
            no = 0;
            street = "";
            suburb = "";
            city = "";
            postalCode = 0;
        }
        public Builder no(int value){
            this.no = value;
            return this;
        }
        public Builder street(String value){
            this.street = value;
            return this;
        }
        public Builder suburb(String value){
            this.suburb = value;
            return this;
        }
        public Builder city(String value){
            this.city = value;
            return this;
        }
        public Builder postalCode(int value){
            this.postalCode = value;
            return this;
        }
        public Builder copy(Address value){
            this.no = value.no;
            this.street = value.street;
            this.suburb = value.suburb;
            this.city = value.city;
            this.postalCode =value.postalCode;
            return this;
        }
        public Address build(){
            return new Address(this);
        }
    }
}
