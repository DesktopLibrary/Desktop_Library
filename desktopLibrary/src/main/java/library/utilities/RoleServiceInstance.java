package library.utilities;

import library.services.api.RoleService;
import library.services.impl.RoleServiceImpl;

public class RoleServiceInstance {

    private static RoleService roleService;

    public static RoleService getInstance(){
        if (roleService != null) {
            return roleService;
        } else {
            roleService = new RoleServiceImpl();
            return roleService;
        }
    }
}
