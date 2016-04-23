package services.customerImpl;

import Domain.Customer;
import services.CustomerService;

/**
 * Created by Riaan on 4/14/2016.
 */
public class Member implements CustomerService {
    @Override
    public Customer getCustomer() {
        Customer customer = new Customer
                .Builder()
                .type("Member")
                .build();
        return customer;
    }
}
