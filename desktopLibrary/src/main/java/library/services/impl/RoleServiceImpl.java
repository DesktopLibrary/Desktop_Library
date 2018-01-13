package library.services.impl;

import library.dataBroker.DataBroker;
import library.dataBroker.DataBrokerImpl;
import library.entities.Role;
import library.services.api.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private DataBroker broker;

    public RoleServiceImpl() {
        this.broker = new DataBrokerImpl();
    }

    @Override
    public void saveOrUpdate(Role role) {
        this.broker.saveOrUpdate(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return this.broker.getRoleByName(roleName);
    }

    @Override
    public Role getRoleById(int id) {
        return this.broker.getRoleById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return this.broker.getAllRoles();
    }
}
