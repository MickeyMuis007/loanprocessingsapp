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
        if(customer.isCarApproval()){
            return new Loan
                    .Builder()
                    .type("Car Loan")
                    .interest(6.5)
                    .customer(customer)
                    .approved(true)
                    .loanAmount(customer.getRequestAmount())
                    .numberOfPayments(paymentOption, numberOfPayments)
                    .build();
        }
        else
        {
            return new Loan
                    .Builder()
                    .type("Car Loan")
                    .interest(6.5)
                    .customer(customer)
                    .approved(false)
                    .loanAmount(0)
                    .numberOfPayments(0)
                    .build();
        }
    }
}
