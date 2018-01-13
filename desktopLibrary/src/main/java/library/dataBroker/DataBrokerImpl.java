package library.dataBroker;

import library.entities.Book;
import library.entities.Role;
import library.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DataBrokerImpl implements DataBroker {

    private EntityManager em;

    public DataBrokerImpl() {
        this.em = Manager.getInstance();
    }

    @Override
    public void saveOrUpdate(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers  = em.createQuery("SELECT u FROM User AS u").getResultList();
        return allUsers;
    }

    @Override
    public User getUserById(long id) {
        Query selectById = em.createQuery("SELECT u FROM User AS u WHERE u.id = :id");
        selectById.setParameter("id", id);
        List<User> users = selectById.getResultList();
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserByEmail(String email) {
        Query selectByEmail = em.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        selectByEmail.setParameter("email", email);
        List<User> users = selectByEmail.getResultList();
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserByUsername(String username) {
        Query selectByUsername = em.createQuery("SELECT u FROM User AS u WHERE u.username = :username");
        selectByUsername.setParameter("username", username);
        List<User> users = selectByUsername.getResultList();
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    public User userLogin(String username, String password) {
        Query loginUserQuery = em.createQuery("SELECT u FROM User AS u WHERE u.username = :username AND u.password = :password");
        loginUserQuery.setParameter("username", username);
        loginUserQuery.setParameter("password", password);
        List<User> users = loginUserQuery.getResultList();
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    public void saveOrUpdate(Book book) {
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> allBooks  = em.createQuery("SELECT b FROM Book AS b").getResultList();
        return allBooks;
    }

    @Override
    public List<Book> getBooksByUserId(long id) {
        Query selectById = em.createQuery("SELECT b FROM Book AS b WHERE b.user.id = :id");
        selectById.setParameter("id", id);
        List<Book> books = selectById.getResultList();
        return books;
    }

    @Override
    public Book getBookById(long id) {
        Query selectById = em.createQuery("SELECT b FROM Book AS b WHERE b.id = :id");
        selectById.setParameter("id", id);
        List<Book> books = selectById.getResultList();
        if(books.isEmpty()){
            return null;
        }
        return books.get(0);
    }

    @Override
    public Book getBookByTitle(String title) {
        Query selectByTitle = em.createQuery("SELECT b FROM Book AS b WHERE b.title = :title");
        selectByTitle.setParameter("title", title);
        List<Book> books = selectByTitle.getResultList();
        if(books.isEmpty()){
            return null;
        }
        return books.get(0);
    }

    @Override
    public Book getBookByAuthor(String author) {
        Query selectByAuthor = em.createQuery("SELECT b FROM Book AS b WHERE b.author = :author");
        selectByAuthor.setParameter("author", author);
        List<Book> books = selectByAuthor.getResultList();
        if(books.isEmpty()){
            return null;
        }
        return books.get(0);
    }

    @Override
    public void deleteBookById(Book book) {
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
    }

    @Override
    public void saveOrUpdate(Role role) {
        em.getTransaction().begin();
        em.merge(role);
        em.getTransaction().commit();
    }

    @Override
    public Role getRoleByName(String roleName) {
        Query selectByRoleName = em.createQuery("SELECT r FROM Role AS r WHERE r.name = :name");
        if (roleName.equals("ROLE_ADMIN") || roleName.equals("ROLE_USER")) {
            selectByRoleName.setParameter("name", roleName);
            List<Role> roles = selectByRoleName.getResultList();
            if (roles.isEmpty()) {
                return null;
            }
            return roles.get(0);
        }
        return null;
    }

    @Override
    public Role getRoleById(int id) {
        Query selectByRoleId = em.createQuery("SELECT r FROM Role AS r WHERE r.id = :id");
        selectByRoleId.setParameter("id", id);
        List<Role> roles = selectByRoleId.getResultList();
        if (roles.isEmpty()) {
            return null;
        }
        return roles.get(0);
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles  = em.createQuery("SELECT r FROM Role AS r").getResultList();
        return allRoles;
    }
}
