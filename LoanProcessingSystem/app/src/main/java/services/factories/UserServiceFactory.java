package services.factories;

import services.UserService;
import services.userImpl.Consultant;
import services.userImpl.Manager;

/**
 * Created by Riaan on 4/15/2016.
 */
public class UserServiceFactory {
    private static UserServiceFactory userServiceFactory = new UserServiceFactory();
    private UserServiceFactory(){}
    public static UserService getService(String service){
        if(service.equalsIgnoreCase("Consultant"))
            return new Consultant();
        else if(service.equalsIgnoreCase("Manager"))
            return new Manager();
        return null;
    }
    public static UserServiceFactory getInstance(){
        if(userServiceFactory.equals(null))
            return new UserServiceFactory();
        return userServiceFactory;
    }
}
