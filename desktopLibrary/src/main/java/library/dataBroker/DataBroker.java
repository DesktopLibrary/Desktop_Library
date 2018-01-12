package library.dataBroker;

import library.entities.Book;
import library.entities.User;

import java.util.List;

public interface DataBroker {
    void saveOrUpdate(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User userLogin(String username, String password);

    void saveOrUpdate(Book book);
    List<Book> getAllBooks();
    List<Book> getBooksByUserId(long id);
    Book getBookById(long id);
    Book getBookByTitle(String title);
    Book getBookByAuthor(String author);
    void deleteBookById(Book book);
}
