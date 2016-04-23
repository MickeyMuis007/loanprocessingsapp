package Domain;

import services.valueobjects.Address;

/**
 * Created by Riaan on 4/15/2016.
 */
public class Branch {
    private long branchNo;
    private String name;
    private Address address;

    private Branch(){}
    public Branch(Builder builder) {
        this.branchNo = builder.branchNo;
        this.address = builder.address;
        this.name = builder.name;
    }


    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public long getBranchNo() {
        return branchNo;
    }

    public static class Builder{
        private long branchNo;
        private String name;
        private Address address;

        public Builder(){
            this.branchNo = 0;
            this.name = "";
            this.address = null;
        }
        public Builder branchNo(long value){
            this.branchNo = value;
            return this;
        }
        public Builder name(String value){
            this.name = value;
            return this;
        }
        public Builder address(int no, String street, String suburb, String city, int postalCode){
            this.address = new Address
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
            this.address = new Address
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
        public Builder copy(Branch value){
            this.branchNo = value.branchNo;
            this.name = value.name;
            this.address = value.address;
            return this;
        }
        public Branch build(){
            return new Branch(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        return branchNo == branch.branchNo;

    }

    @Override
    public int hashCode() {
        return (int) (branchNo ^ (branchNo >>> 32));
    }
}
