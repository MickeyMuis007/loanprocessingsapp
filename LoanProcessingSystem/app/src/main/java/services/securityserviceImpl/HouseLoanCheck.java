package services.securityserviceImpl;

import Domain.Customer;
import Domain.SecurityCheck;
import services.SecurityService;

/**
 * Created by Riaan on 4/14/2016.
 */
public class HouseLoanCheck implements SecurityService {
    @Override
    public SecurityCheck process(Customer customer) {
        if(customer.getMonthlyIncome() > 10000){
            customer = new Customer
                    .Builder()
                    .copy(customer)
                    .houseApproval(true)
                    .build();
        }
        else {
            customer = new Customer
                    .Builder()
                    .copy(customer)
                    .houseApproval(false)
                    .build();
        }
        return new SecurityCheck(customer);
    }
}
