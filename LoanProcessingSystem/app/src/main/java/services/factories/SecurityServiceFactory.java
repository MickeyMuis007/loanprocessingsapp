package services.factories;

import services.SecurityService;
import services.securityserviceImpl.CarLoanCheck;
import services.securityserviceImpl.HouseLoanCheck;
import services.securityserviceImpl.InstantLoanCheck;

/**
 * Created by Riaan on 4/15/2016.
 */
public class SecurityServiceFactory {
    private static SecurityServiceFactory securityServiceFactory = new SecurityServiceFactory();
    private SecurityServiceFactory(){
    }
    public static SecurityService getService(String securityCheck){
        if(securityCheck.equalsIgnoreCase("Check Car Loan"))
            return new CarLoanCheck();
        else if (securityCheck.equalsIgnoreCase("Check House Loan"))
                return new HouseLoanCheck();
        else if(securityCheck.equalsIgnoreCase("Check Instant Loan"))
            return new InstantLoanCheck();
        return null;
    }
    public static SecurityServiceFactory getInstance(){
        if(securityServiceFactory.equals(null))
            return new SecurityServiceFactory();
        return securityServiceFactory;
    }

}
