package sample.services.impl;

import sample.dataBroker.DataBroker;

import sample.dataBroker.DataBrokerImpl;
import sample.entities.User;
import sample.services.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private DataBroker broker;

    public UserServiceImpl() {
        this.broker = new DataBrokerImpl();
    }

    @Override
    public void saveOrUpdate(User user) {
        this.broker.saveOrUpdate(user);
    }

    @Override
    public List<User> getAllUsers() {
       return this.broker.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return this.broker.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.broker.getUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.broker.getUserByUsername(username);
    }

    @Override
    public User userLogin(String username, String password) {
        return this.broker.userLogin(username,password);
    }
}
