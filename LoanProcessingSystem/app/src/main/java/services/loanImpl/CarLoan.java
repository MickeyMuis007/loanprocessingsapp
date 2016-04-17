package services.loanImpl;

import Domain.Customer;
import Domain.Loan;
import services.LoanService;

/**
 * Created by Riaan on 4/13/2016.
 */
public class CarLoan implements LoanService {

    @Override
    public Loan process(Customer customer, String paymentOption, int numberOfPayments) {
        Loan loan = new Loan("Car Loan",6.5);
        loan.setCustomer(customer);
        if(loan.getCustomer().isCarApproval()){
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
