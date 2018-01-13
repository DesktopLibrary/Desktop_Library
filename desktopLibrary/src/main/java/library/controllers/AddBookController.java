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
import library.utilities.BookServiceInstance;
import library.utilities.LoaderProvider;
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
        String titleString = this.title.getText();
        String authorString = this.author.getText();
        String summaryString = this.summary.getText();

        if (!titleString.equals("")) {
            this.book.setTitle(titleString);
        } else {
            errorLabel.setText("Please provide book title");
            return;
        }

        if (!authorString.equals("")) {
            this.book.setAuthor(authorString);
        } else {
            this.errorLabel.setText("Please provide author name");
            return;
        }

        if (summaryString != null) {
            this.book.setSummary(summaryString);
        }

        this.book.setUser(this.user);

        this.bookService.saveOrUpdate(this.book);
        backToMenu();
    }

    @FXML
    public void backToMainMenuClicked() throws IOException {
        backToMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bookService = BookServiceInstance.getInstance();
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
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/menu.fxml"));
        AnchorPane root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }
}
