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
        if(customer.isHouseApproval()){
            return new Loan
                    .Builder()
                    .type("House Loan")
                    .interest(12)
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
                    .type("House Loan")
                    .interest(12)
                    .customer(customer)
                    .approved(false)
                    .build();
        }
    }
}
