package library.services.api;

import library.entities.Book;

import java.util.List;

public interface BookService {
    void saveOrUpdate(Book book);
    List<Book> getAllBooks();
    List<Book> getBooksByUserId(long id);
    Book getBookById(long id);
    Book getBookByTitle(String title);
    Book getBookByAuthor(String author);
    void deleteBookById(Book book);
}
