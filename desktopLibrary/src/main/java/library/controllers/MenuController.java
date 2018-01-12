package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.Main;
import library.entities.User;
import library.services.api.BookService;
import library.services.api.UserService;
import library.services.impl.BookServiceImpl;
import library.services.impl.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private UserService userService;
    private BookService bookService;
    private User user;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label loggedName;

    @FXML
    public void viewBooksClicked() throws IOException {
        FXMLLoader booksLoader = new FXMLLoader(getClass().getResource("/FXML/userBooks.fxml"));

        AnchorPane root = booksLoader.load();
        UserBooksController controller = booksLoader.<UserBooksController>getController();
        controller.initData(user);

        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    public void editProfileClicked() {
        
    }

    @FXML
    public void addBookClicked() throws IOException {
        FXMLLoader addBookLoader = new FXMLLoader(getClass().getResource("/FXML/addBook.fxml"));

        AnchorPane root = addBookLoader.load();
        AddBookController controller = addBookLoader.<AddBookController>getController();
        controller.initData(user);

        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    void exitClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bookService = new BookServiceImpl();
        this.userService = new UserServiceImpl();
    }

    public  void initData(User user){
        this.user = user;
        this.loggedName.setText("Hello, " + this.user.getUsername());
    }
}
