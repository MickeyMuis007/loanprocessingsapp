package Domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Riaan on 4/13/2016.
 */
public class Loan {


    private long loanReferenceNo;
    private String type;
    private double rateOfInterest;
    private double interest;
    private double loanAmount;
    private double totalAmount;
    private int numberOfPayments;
    private boolean approved;
    private Customer customer;
    private User user;

    public Loan(String type, double interest) {
        this.type = type;
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDuration(String paymentOption, int numberOfPayments){
        if(paymentOption.equalsIgnoreCase("Years")){
            rateOfInterest = (interest/100)/12;
            this.numberOfPayments = numberOfPayments * 12;
        }
        else{
            rateOfInterest = (interest/100)/12;
            this.numberOfPayments = numberOfPayments;
        }
    }
    public void setDuration(int numberOfPayments){
        //numberOfPayment in months
        rateOfInterest = (interest/100)/12;
        this.numberOfPayments = numberOfPayments;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public double getTotalAmount() {

        totalAmount =((rateOfInterest*loanAmount)/(1-(Math.pow(1+rateOfInterest,-numberOfPayments))))*numberOfPayments;
        BigDecimal bd = new BigDecimal(totalAmount);
        totalAmount = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return totalAmount;
    }

    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public long getLoanReferenceNo() {
        return loanReferenceNo;
    }

    public void setLoanReferenceNo(long loanReferenceNo) {
        this.loanReferenceNo = loanReferenceNo;
    }

    public String getType() {
        return type;
    }



    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }


    public Customer getCustomer() {
        return customer;
    }
}
