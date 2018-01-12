package library.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.entities.Book;
import library.entities.User;
import library.services.api.BookService;
import library.services.api.UserService;
import library.services.impl.BookServiceImpl;
import library.services.impl.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserBooksController implements Initializable {

    private UserService userService;
    private BookService bookService;
    private User user;

    @FXML
    private TableView<Book> table;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane rootPane;

    @FXML
    void backToMainMenuClicked() throws IOException {
        AnchorPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/menu.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }

    @FXML
    void deleteButtonClicked() {
        Book selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            table.getItems().remove(selectedItem);
        }
    }

    @FXML
    void editButtonClicked() {

    }

    @FXML
    void descriptionButtonClicked() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bookService = new BookServiceImpl();
        this.userService = new UserServiceImpl();
    }

    public void initData(User user){
        this.user = user;
        List<Book> booksByUser = this.bookService.getBooksByUserId(user.getId());
        ObservableList<Book> observableList = FXCollections.observableList(booksByUser);
        this.table.setItems(observableList);
    }

}
