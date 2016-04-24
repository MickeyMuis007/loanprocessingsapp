package services.securityserviceImpl;

import Domain.Customer;
import Domain.SecurityCheck;
import services.SecurityService;

/**
 * Created by Riaan on 4/14/2016.
 */
public class CarLoanCheck implements SecurityService {
    @Override
    public SecurityCheck process(Customer customer) {
        if(customer.getMonthlyIncome() > 3000) {
            customer = new Customer
                    .Builder()
                    .copy(customer)
                    .carApproval(true)
                    .build();
        }
        else{
            customer = new Customer
                    .Builder()
                    .copy(customer)
                    .carApproval(false)
                    .build();
        }
        return new SecurityCheck
                .Builder()
                .customer(customer)
                .build();
    }
}
