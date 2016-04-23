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

    private User(){}
    public User(Builder builder) {
        this.userNo = builder.userNo;
        this.type = builder.type;
        this.fullName = builder.fullName;
        this.address = builder.address;
        this.branch = builder.branch;
    }

    public Branch getBranch() {
        return branch;
    }

    public long getUserNo() {
        return userNo;
    }

    public String getType() {
        return type;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Address getAddress() {
        return address;
    }

    public static class Builder{
        private long userNo;
        private String type;
        private FullName fullName;
        private Address address;
        private Branch branch;

        public Builder(){
            userNo = 0;
            type = "";
            fullName = null;
            address = null;
            branch = null;
        }
        public Builder userNo(long value){
            this.userNo = value;
            return this;
        }
        public Builder type(String value){
            this.type = value;
            return this;
        }
        public Builder fullName(String valueName, String valueMiddleName, String valueLastName){
            this.fullName = new FullName
                    .Builder()
                    .name(valueName)
                    .middleName(valueMiddleName)
                    .lastName(valueLastName)
                    .build();
            return this;
        }
        public Builder fullName(String valueName, String valueLastName){
            this.fullName = new FullName
                    .Builder()
                    .name(valueName)
                    .lastName(valueLastName)
                    .build();
            return this;
        }
        public Builder fullName(String valueName){
            this.fullName = new FullName
                    .Builder()
                    .name(valueName)
                    .build();
            return this;
        }
        public Builder fullName(FullName value){
            this.fullName = value;
            return this;
        }
        public Builder address(int no, String street, String suburb, String city, int postalCode){
            address = new Address
                    .Builder()
                    .no(no)
                    .street(street)
                    .suburb(suburb)
                    .city(city)
                    .postalCode(postalCode)
                    .build();
            return this;
        }
        public Builder address(int no, String street, String city, int postalCode){
            address = new Address
                    .Builder()
                    .no(no)
                    .street(street)
                    .city(city)
                    .postalCode(postalCode)
                    .build();
            return this;
        }
        public Builder address(Address value){
            this.address = value;
            return this;
        }
        public Builder branch(Branch value){
            this.branch = value;
            return this;
        }
        public Builder copy(User value){
            this.userNo = value.userNo;
            this.type = value.type;
            this.fullName = value.fullName;
            this.address = value.address;
            this.branch =value.branch;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userNo == user.userNo;

    }

    @Override
    public int hashCode() {
        return (int) (userNo ^ (userNo >>> 32));
    }
}
