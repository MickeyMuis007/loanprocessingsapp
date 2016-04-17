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
        SecurityCheck securityCheck = new SecurityCheck(customer);
        if(customer.getMonthlyIncome() > 3000)
            customer.setCarApproval(true);
        else
            customer.setCarApproval(false);
        return securityCheck;
    }
}
