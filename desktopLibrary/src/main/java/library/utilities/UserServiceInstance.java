package library.utilities;

import library.services.api.UserService;
import library.services.impl.UserServiceImpl;

public class UserServiceInstance {
    private static UserService userService;

    public static UserService getInstance(){
        if (userService != null) {
            return userService;
        } else {
            userService = new UserServiceImpl();
            return userService;
        }
    }
}
