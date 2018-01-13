package library.services.api;


import library.entities.Role;

import java.util.List;

public interface RoleService {
    void saveOrUpdate(Role role);
    Role getRoleByName(String roleName);
    Role getRoleById(int id);
    List<Role> getAllRoles();
}
