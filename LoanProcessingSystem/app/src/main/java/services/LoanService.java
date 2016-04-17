package services;

import Domain.Customer;
import Domain.Loan;

/**
 * Created by Riaan on 4/13/2016.
 */
public interface LoanService {
    public Loan process(Customer customer, String paymentOption, int numberOfPayments);
}
