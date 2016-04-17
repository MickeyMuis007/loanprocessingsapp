package services.factories;

import services.CustomerService;
import services.customerImpl.Member;
import services.customerImpl.NonMember;

/**
 * Created by Riaan on 4/14/2016.
 */
public class CustomerServicesFactory {
    private static CustomerServicesFactory factory = new CustomerServicesFactory();
    private CustomerServicesFactory(){
    }

    public static CustomerService getService(String customer){
        if(customer.equalsIgnoreCase("Member"))
            return new Member();
        else if(customer.equalsIgnoreCase("Non Member"))
            return new NonMember();
        return null;
    }
     public static CustomerServicesFactory getInstance(){
         if(factory.equals(null))
             return new CustomerServicesFactory();
         return factory;
     }
}
