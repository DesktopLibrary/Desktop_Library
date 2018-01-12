package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.entities.Book;
import library.entities.User;
import library.services.api.BookService;
import library.services.impl.BookServiceImpl;
import library.utilities.ImageUpload;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditBookController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField authorField;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea summaryField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button changeImageButton;

    private BookService bookService;
    private Book book;
    private User user;

    @FXML
    void changeImageButtonClicked() {
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\book_images\\";
        File imageFile = new File(imagePath + this.book.getPicture());
        String filePath = ImageUpload.saveToFile(imagePath, this.rootPane.getScene().getWindow());
        if (filePath != null) {
            this.book.setPicture(filePath);
            this.changeImageButton.setText("Image Changed");
            imageFile.delete();
        }
    }

    @FXML
    void editButtonClicked() throws IOException {

        String titleString = this.titleField.getText();
        String authorString = this.authorField.getText();
        String summaryString = this.summaryField.getText();
        if (!titleString.equals("")) {
            book.setTitle(titleString);
        } else {
            errorLabel.setText("Book title cannot be empty!");
            return;
        }

        if (!authorString.equals("")) {
            book.setAuthor(authorString);
        } else {
            errorLabel.setText("Book author cannot be empty!");
            return;
        }

        if (summaryString != null) {
            book.setSummary(summaryString);
        }

        this.bookService.saveOrUpdate(book);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/userBooks.fxml"));
        AnchorPane root = fxmlLoader.load();
        UserBooksController controller = fxmlLoader.<UserBooksController>getController();
        controller.initData(this.user);

        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    public void backToMainMenuClicked() throws IOException {
        backToMyBooks();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bookService = new BookServiceImpl();
    }

    public void initData(User user, Book book){
        this.user = user;
        this.book = book;
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        summaryField.setText(book.getSummary());
    }

    private void backToMyBooks() throws IOException {
        FXMLLoader booksLoader = new FXMLLoader(getClass().getResource("/FXML/userBooks.fxml"));
        AnchorPane root = booksLoader.load();
        UserBooksController controller = booksLoader.<UserBooksController>getController();
        controller.initData(user);

        this.rootPane.getChildren().setAll(root);
    }
}
