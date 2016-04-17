package services.loanImpl;

import Domain.Customer;
import Domain.Loan;
import services.LoanService;

/**
 * Created by Riaan on 4/13/2016.
 */
public class HouseLoan implements LoanService {
    @Override
    public Loan process(Customer customer, String paymentOption, int numberOfPayments) {
        Loan loan = new Loan("House Loan",12);
        loan.setCustomer(customer);
        if(loan.getCustomer().isHouseApproval()){
            loan.setApproved(true);
            loan.setLoanAmount(loan.getCustomer().getRequestAmount());
            loan.setDuration(paymentOption, numberOfPayments);
            return loan;
        }
        else
        {
            loan.setApproved(false);
            return loan;
        }
    }
}
