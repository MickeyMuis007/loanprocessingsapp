package services;

import Domain.Customer;
import Domain.SecurityCheck;

/**
 * Created by Riaan on 4/14/2016.
 */
public interface SecurityService {
    public SecurityCheck process(Customer customer);
}
