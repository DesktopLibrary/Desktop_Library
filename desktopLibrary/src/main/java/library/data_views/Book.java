package library.data_views;

import javafx.beans.property.SimpleStringProperty;

public class Book {
    private final SimpleStringProperty author = new SimpleStringProperty("");
    private final SimpleStringProperty title = new SimpleStringProperty("");


    public Book() {
        this("", "" );
    }

    public Book(String author, String title) {
        setAuthor(author);
        setTitle(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
}
