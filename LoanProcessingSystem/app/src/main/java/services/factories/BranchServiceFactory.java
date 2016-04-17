package services.factories;

import services.BranchService;
import services.branchImpl.CapeTown;
import services.branchImpl.Goodwood;
import services.branchImpl.HeadOffice;

/**
 * Created by Riaan on 4/15/2016.
 */
public class BranchServiceFactory {
    private static BranchServiceFactory branchServiceFactory = new BranchServiceFactory();
    private BranchServiceFactory() {
    }
    public static BranchService getService(String service){
        if(service.equalsIgnoreCase("Goodwood"))
            return new Goodwood();
        else if(service.equalsIgnoreCase("Cape Town"))
            return new CapeTown();
        else if(service.equalsIgnoreCase("Head Office"))
            return new HeadOffice();
        return null;
    }
    public static BranchServiceFactory getInstance(){
        if(branchServiceFactory.equals(null))
            return new BranchServiceFactory();
        return branchServiceFactory;
    }
}
