package Domain;

/**
 * Created by Riaan on 4/15/2016.
 */
public class Report {
    private Loan loan;
    private String type;
    private long userNo;
    private String userName;
    private String userType;
    private long customerNo;
    private long branchNo;
    private String branchName;
    private String customerName;
    private double interest;
    private double totalAmount;
    private double loanAmount;
    private double monthlyPayment;

    public Report(Loan loan,String type){
        this.loan = loan;
        this.type = type;
        setBranch();
        setCustomer();
        setTransaction();
        setUser();
    }

    private void setCustomer(){
        this.customerNo = loan.getCustomer().getCustomerId();
        this.customerName = loan.getCustomer().getFullName().toString();
    }
    private void setUser(){
        this.userNo = loan.getUser().getUserNo();
        this.userName = loan.getUser().getFullName().toString();
        this.userType = loan.getUser().getType();
    }
    private void setTransaction(){
        this.interest = loan.getInterest();
        this.totalAmount = loan.getTotalAmount();
        this.loanAmount = loan.getLoanAmount();
        this.monthlyPayment = loan.getTotalAmount()/loan.getNumberOfPayments();
    }
    private void setBranch(){
        this.branchNo = loan.getUser().getBranch().getBranchNo();
        this.branchName = loan.getUser().getBranch().getName();
    }
    public Loan getLoan() {
        return loan;
    }

    public String getBranchName() {
        return branchName;
    }

    public long getBranchNo() {
        return branchNo;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public long getUserNo() {
        return userNo;
    }

    public long getCustomerNo() {
        return customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getInterest() {
        return interest;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public String getUserType() {
        return userType;
    }

    public String display() {
        String display = "";
        display += "Type: "+ getType() + "\n";
        display += "Loan Reference No: " + getLoan().getLoanReferenceNo() + "\n";
        display += "Customer Name: " + getCustomerName() + "\n";
        display += "User Name: " + getUserName()+ "\n";
        display += "User No: " + getUserNo()+ "\n";
        display += "User Type: " + getUserType()+ "\n";
        display += "Branch Name: " + getBranchName()+ "\n";
        display += "Branch No: " + getBranchNo()+ "\n";
        display += "Loan Amount: " + getLoanAmount()+ "\n";
        display += "Total interest: " + getTotalAmount()+ "\n";
        display += "Interest rate: " + getInterest()+ "%\n";
        display += "Monthly payments: " + getMonthlyPayment()+ "\n";
        return display;
    }

}
