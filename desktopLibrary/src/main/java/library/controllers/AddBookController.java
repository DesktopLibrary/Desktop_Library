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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label errorLabel;
    @FXML
    private Button uploadImageButton;
    @FXML
    private TextArea summary;
    @FXML
    private TextField author;
    @FXML
    private TextField title;

    private User user;
    private Book book;
    private BookService bookService;

    @FXML
    public void submitButtonClicked() throws IOException {
        String titleString = title.getText();
        String authorString = author.getText();
        String summaryString = summary.getText();

        if (!titleString.equals("")) {
            book.setTitle(titleString);
        } else {
            errorLabel.setText("Please provide book title");
            return;
        }

        if (!authorString.equals("")) {
            book.setAuthor(authorString);
        } else {
            errorLabel.setText("Please provide author name");
            return;
        }

        if (summaryString != null) {
            book.setSummary(summaryString);
        }

        book.setUser(user);

        this.bookService.saveOrUpdate(this.book);
        backToMenu();
    }

    @FXML
    public void backToMainMenuClicked() throws IOException {
        backToMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bookService = new BookServiceImpl();
        this.book = new Book();
    }

    public void uploadImage() {
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\book_images\\";
        String filePath = ImageUpload.saveToFile(imagePath, this.rootPane.getScene().getWindow());
        if (filePath != null) {
            this.book.setPicture(filePath);
            this.uploadImageButton.setText("Image Uploaded");
        }

    }

    public void initData(User user) {
        ImageUpload.getInstance();
        ImageUpload.configureFileChooser();
        this.user = user;
    }

    private void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/menu.fxml"));
        AnchorPane root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(user);

        this.rootPane.getChildren().setAll(root);
    }
}
