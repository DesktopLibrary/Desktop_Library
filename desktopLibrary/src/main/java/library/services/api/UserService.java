package library.services.api;

import library.entities.Book;
import library.entities.User;

import java.util.List;

public interface UserService {
    void saveOrUpdate(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User userLogin(String username, String password);
    void deleteUserById(User user, List<Book> books);
}
