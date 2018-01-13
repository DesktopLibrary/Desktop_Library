package library.services.api;


import library.entities.Role;

public interface RoleService {
    Role getRoleByName(String roleName);
    Role getRoleById(int id);
}
