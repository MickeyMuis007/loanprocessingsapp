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
   // private boolean approval;
    private boolean carApproval;
    private boolean houseApproval;
    private boolean instantApproval;
    private Address address;
    private double monthlyIncome;
    private double requestAmount;

    public Customer(String type) {
        this.type = type;
    }

    public boolean isCarApproval() {
        return carApproval;
    }

    public void setCarApproval(boolean carApproval) {
        this.carApproval = carApproval;
    }

    public boolean isHouseApproval() {
        return houseApproval;
    }

    public void setHouseApproval(boolean houseApproval) {
        this.houseApproval = houseApproval;
    }

    public boolean isInstantApproval() {
        return instantApproval;
    }

    public void setInstantApproval(boolean instantApproval) {
        this.instantApproval = instantApproval;
    }

    public String getType() {
        return type;
    }


    /*public boolean getApproval(){
        return approval;
          public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }
    }*/

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = new FullName((fullName));
    }
    public void setFullName(String name, String lastname){ this.fullName = new FullName(name,lastname);}
    public void setFullName(String name, String middleName, String lastname){ this.fullName = new FullName(name,middleName,lastname); }
    public void setFullName(FullName fullName) { this.fullName = fullName; }



    public Address getAddress() {
        return address;
    }

    public void setAddress(int no, String street, String suburb, String city, String country, int postalCode){
        address = new Address(no,street,suburb,city,country,postalCode);
    }
    public void setAddress(int no, String street, String city, String country, int postalCode){
        address = new Address(no,street,city,country,postalCode);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }

}
