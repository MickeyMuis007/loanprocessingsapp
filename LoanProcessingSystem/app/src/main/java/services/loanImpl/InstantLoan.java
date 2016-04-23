package services.loanImpl;

import Domain.Customer;
import Domain.Loan;
import services.LoanService;

/**
 * Created by Riaan on 4/13/2016.
 */
public class InstantLoan implements LoanService {
    @Override
    public Loan process(Customer customer, String paymentOption, int numberOfPayments) {
        if(customer.isInstantApproval()){
            return new Loan
                    .Builder()
                    .type("Instant Loan")
                    .interest(19)
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
                    .type("Instant Loan")
                    .interest(19)
                    .customer(customer)
                    .approved(false)
                    .build();
        }
}
}
