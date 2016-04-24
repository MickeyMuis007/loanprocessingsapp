package services.securityserviceImpl;

import Domain.Customer;
import Domain.SecurityCheck;
import services.SecurityService;

/**
 * Created by Riaan on 4/14/2016.
 */
public class InstantLoanCheck implements SecurityService {
    @Override
    public SecurityCheck process(Customer customer) {

        if(customer.getMonthlyIncome() > 2000){
            customer = new Customer
                    .Builder()
                    .copy(customer)
                    .instantApproval(true)
                    .build();
        }
        else{
            customer = new Customer
                    .Builder()
                    .copy(customer)
                    .instantApproval(false)
                    .build();
        }
        return new SecurityCheck
                .Builder()
                .customer(customer)
                .build();
    }
}
