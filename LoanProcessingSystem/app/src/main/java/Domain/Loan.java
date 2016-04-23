package Domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Riaan on 4/13/2016.
 */
public class Loan {


    private long loanReferenceNo;
    private String type;
    private double interest;
    private double loanAmount;
    private int numberOfPayments;
    private boolean approved;
    private Customer customer;
    private User user;

    private Loan(){}
    public Loan(Builder builder) {
        this.loanReferenceNo = builder.loanReferenceNo;
        this.loanAmount = builder.loanAmount;
        this.user = builder.user;
        this.customer = builder.customer;
        this.approved = builder.approved;
        this.interest = builder.interest;
        this.numberOfPayments = builder.numberOfPayments;
        this.type = builder.type;
    }

    public double getInterest() {
        return interest;
    }

    public User getUser() {
        return user;
    }

    public boolean isApproved() {
        return approved;
    }

    public double getTotalAmount() {
        double rateOfInterest = (interest/100)/12;
        double totalAmount =((rateOfInterest*loanAmount)/(1-(Math.pow(1+rateOfInterest,-numberOfPayments))))*numberOfPayments;
        BigDecimal bd = new BigDecimal(totalAmount);
        totalAmount = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return totalAmount;
    }

    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    public long getLoanReferenceNo() {
        return loanReferenceNo;
    }

    public String getType() {
        return type;
    }



    public double getLoanAmount() {
        return loanAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static class Builder{
        private long loanReferenceNo;
        private String type;
        private double interest;
        private double loanAmount;
        private int numberOfPayments;
        private boolean approved;
        private Customer customer;
        private User user;

        public Builder(){
            loanReferenceNo = 0;
            type = "";
            interest = 0;
            loanAmount = 0;
            numberOfPayments = 0;
            approved = false;
            customer = null;
            user = null;
        }
        public Builder loanReferenceNo(long value){
            this.loanReferenceNo = value;
            return this;
        }
        public Builder type(String value){
            this.type = value;
            return this;
        }
        public Builder loanAmount(double value){
            this.loanAmount = value;
            return this;
        }
        public Builder numberOfPayments(String paymentOption, int numberOfPayments){
            if(paymentOption.equalsIgnoreCase("Years")){
                this.numberOfPayments = numberOfPayments * 12;
            }
            else{
                this.numberOfPayments = numberOfPayments;
            }
            return this;
        }
        public Builder numberOfPayments(int numberOfPayments){
            //numberOfPayment in months
            this.numberOfPayments = numberOfPayments;
            return this;
        }
        public Builder approved(boolean value){
            this.approved = value;
            return this;
        }
        public Builder customer(Customer value){
            this.customer = value;
            return this;
        }
        public Builder user(User value){
            this.user = value;
            return this;
        }
        public Builder interest(double value){
            this.interest = value;
            return this;
        }
        public Builder copy(Loan value){
            this.loanReferenceNo = value.loanReferenceNo;
            this.loanAmount = value.loanAmount;
            this.user = value.user;
            this.customer = value.customer;
            this.approved = value.approved;
            this.interest = value.interest;
            this.numberOfPayments = value.numberOfPayments;
            this.type = value.type;
            return this;
        }
        public Loan build(){
            return new Loan(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        return loanReferenceNo == loan.loanReferenceNo;

    }

    @Override
    public int hashCode() {
        return (int) (loanReferenceNo ^ (loanReferenceNo >>> 32));
    }
}
