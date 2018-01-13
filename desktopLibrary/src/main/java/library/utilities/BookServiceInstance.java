package library.utilities;

import library.services.api.BookService;
import library.services.impl.BookServiceImpl;

import javax.persistence.Persistence;

public class BookServiceInstance {

    private static BookService bookService;

    public static BookService getInstance(){
        if (bookService != null) {
            return bookService;
        } else {
            bookService = new BookServiceImpl();
            return bookService;
        }
    }
}
