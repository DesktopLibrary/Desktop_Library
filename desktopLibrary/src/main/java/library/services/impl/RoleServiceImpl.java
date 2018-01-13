package library.services.impl;

import library.dataBroker.DataBroker;
import library.dataBroker.DataBrokerImpl;
import library.entities.Role;
import library.services.api.RoleService;

public class RoleServiceImpl implements RoleService {

    private DataBroker broker;

    public RoleServiceImpl() {
        this.broker = new DataBrokerImpl();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return this.broker.getRoleByName(roleName);
    }

    @Override
    public Role getRoleById(int id) {
        return this.broker.getRoleById(id);
    }
}
