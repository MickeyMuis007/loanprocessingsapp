package services.userImpl;

import Domain.User;
import services.UserService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class Manager implements UserService {
    @Override
    public User getUser() {
        User user = new User("Manager");
        return user;
    }
}
