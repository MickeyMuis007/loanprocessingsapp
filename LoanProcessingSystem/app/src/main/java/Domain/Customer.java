package Domain;

import services.valueobjects.Address;
import services.valueobjects.FullName;

/**
 * Created by Riaan on 4/13/2016.
 */
public class Customer {
    private long customerId;
    private String type;
    private FullName fullName;
    private boolean carApproval;
    private boolean houseApproval;
    private boolean instantApproval;
    private Address address;
    private double monthlyIncome;
    private double requestAmount;

    public Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.type = builder.type;
        this.fullName = builder.fullName;
        this.address = builder.address;
        this.carApproval = builder.carApproval;
        this.houseApproval = builder.houseApproval;
        this.instantApproval = builder.instantApproval;
        this.monthlyIncome = builder.monthlyIncome;
        this.requestAmount = builder.requestAmount;
    }

    public boolean isCarApproval() {
        return carApproval;
    }

    public boolean isHouseApproval() {
        return houseApproval;
    }

    public boolean isInstantApproval() {
        return instantApproval;
    }

    public String getType() {
        return type;
    }

    public long getCustomerId() {
        return customerId;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Address getAddress() {
        return address;
    }


    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public static class Builder {
        private long customerId;
        private String type;
        private FullName fullName;
        private boolean carApproval;
        private boolean houseApproval;
        private boolean instantApproval;
        private Address address;
        private double monthlyIncome;
        private double requestAmount;

        public Builder() {
            customerId = 0;
            type = "";
            fullName = null;
            carApproval = false;
            houseApproval = false;
            instantApproval = false;
            address = null;
            monthlyIncome = 0;
            requestAmount = 0;
        }

        public Builder customerId(long value) {
            this.customerId = value;
            return this;
        }

        public Builder type(String value) {
            this.type = value;
            return this;
        }

        public Builder fullName(String valueName, String valueMiddleName, String valueLastName) {
            this.fullName = new FullName
                    .Builder()
                    .name(valueName)
                    .middleName(valueMiddleName)
                    .lastName(valueLastName)
                    .build();
            return this;
        }

        public Builder fullName(String valueName, String valueLastName) {
            this.fullName = new FullName
                    .Builder()
                    .name(valueName)
                    .lastName(valueLastName)
                    .build();
            return this;
        }

        public Builder fullName(String valueName) {
            this.fullName = new FullName
                    .Builder()
                    .name(valueName)
                    .build();
            return this;
        }
        public Builder fullName(FullName fullName){
            this.fullName = fullName;
            return this;
        }

        public Builder carApproval(boolean value) {
            this.carApproval = value;
            return this;
        }

        public Builder houseApproval(boolean value) {
            this.houseApproval = value;
            return this;
        }

        public Builder instantApproval(boolean value) {
            this.instantApproval = value;
            return this;
        }

        public Builder address(int valueNo, String valueStreet, String valueSuburb, String valueCity, int valuePostalCode) {
            address = new Address
                    .Builder()
                    .no(valueNo)
                    .street(valueStreet)
                    .suburb(valueSuburb)
                    .city(valueCity)
                    .postalCode(valuePostalCode)
                    .build();
            return this;
        }

        public Builder address(int valueNo, String valueStreet, String valueCity, int valuePostalCode) {
            address = new Address
                    .Builder()
                    .no(valueNo)
                    .street(valueStreet)
                    .city(valueCity)
                    .postalCode(valuePostalCode)
                    .build();
            return this;
        }
        public Builder address(Address address){
            this.address = address;
            return this;
        }

        public Builder monthlyIncome(double value){
            this.monthlyIncome=value;
            return this;
        }
        public Builder requestAmount(double value){
            this.requestAmount = value;
            return this;
        }
        public Builder copy(Customer value){
            this.customerId = value.customerId;
            this.type = value.type;
            this.fullName = value.fullName;
            this.address = value.address;
            this.carApproval = value.carApproval;
            this.houseApproval = value.houseApproval;
            this.instantApproval = value.instantApproval;
            this.monthlyIncome = value.monthlyIncome;
            this.requestAmount = value.requestAmount;
            return this;
        }
        public Customer build(){
            return new Customer(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return customerId == customer.customerId;

    }

    @Override
    public int hashCode() {
        return (int) (customerId ^ (customerId >>> 32));
    }
}
