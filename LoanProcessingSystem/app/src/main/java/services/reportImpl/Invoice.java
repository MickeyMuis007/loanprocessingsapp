package services.reportImpl;

import Domain.Loan;
import Domain.Report;
import services.ReportService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class Invoice implements ReportService{
    Report report;

    public Invoice(Loan loan) {
        report = new Report(loan,"Invoice");
    }

    @Override
    public String display() {
        String display = "";
        display += "Type: "+ report.getType() + "\n";
        display += "Loan Reference No: " + report.getLoan().getLoanReferenceNo() + "\n";
        display += "Customer Name: " + report.getCustomerName() + "\n";
        display += "User Name: " + report.getUserName()+ "\n";
        display += "User No: " + report.getUserNo()+ "\n";
        display += "Branch Name: " + report.getBranchName()+ "\n";
        display += "Branch No: " + report.getBranchNo()+ "\n";
        display += "Loan Amount: " + report.getLoanAmount()+ "\n";
        display += "Total interest: " + report.getTotalAmount()+ "\n";
        display += "Interest rate: " + report.getInterest()+ "%\n";
        display += "Monthly payments: " + report.getMonthlyPayment()+ "\n";
        return display;
    }

    @Override
    public Report getReport() {
        return report;
    }
}
