package services.customerImpl;

import Domain.Customer;
import services.CustomerService;

/**
 * Created by Riaan on 4/14/2016.
 */
public class NonMember implements CustomerService {
    @Override
    public Customer getCustomer() {
        Customer customer = new Customer("Non Member");
        return customer;
    }
}
