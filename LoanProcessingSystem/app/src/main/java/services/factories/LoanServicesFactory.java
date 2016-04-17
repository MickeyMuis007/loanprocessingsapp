package services.factories;

import services.loanImpl.CarLoan;
import services.LoanService;
import services.loanImpl.HouseLoan;
import services.loanImpl.InstantLoan;

/**
 * Created by Riaan on 4/14/2016.
 */
public class LoanServicesFactory {
    private static LoanServicesFactory factory = new LoanServicesFactory();
    private LoanServicesFactory() {
    }
    public static LoanService getService(String service){
        if(service.equalsIgnoreCase("Car Loan"))
            return new CarLoan();
        else if(service.equalsIgnoreCase("House Loan"))
            return new HouseLoan();
        else if(service.equalsIgnoreCase("Instant Loan"))
            return new InstantLoan();
        return null;
    }
    public static LoanServicesFactory getInstance(){
        if(factory.equals(null))
            return new LoanServicesFactory();
        return factory;
    }
}
