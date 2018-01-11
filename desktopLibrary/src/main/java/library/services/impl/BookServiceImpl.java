package library.services.impl;

import library.dataBroker.DataBroker;
import library.dataBroker.DataBrokerImpl;
import library.entities.Book;
import library.services.api.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private DataBroker broker;

    public BookServiceImpl() {
        this.broker = new DataBrokerImpl();
    }

    @Override
    public void saveOrUpdate(Book book) {
        this.broker.saveOrUpdate(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.broker.getAllBooks();
    }

    @Override
    public List<Book> getBooksByUserId(long id) {
        return this.broker.getBooksByUserId(id);
    }

    @Override
    public Book getBookById(long id) {
        return this.broker.getBookById(id);
    }

    @Override
    public Book getBookByTitle(String title) {
        return this.broker.getBookByTitle(title);
    }

    @Override
    public Book getBookByAuthor(String author) {
        return this.broker.getBookByAuthor(author);
    }
}
