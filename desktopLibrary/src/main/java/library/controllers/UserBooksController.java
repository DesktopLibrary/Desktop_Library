package library.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    void descriptionButtonClicked() throws IOException {
        Book selectedItem = table.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/singleBookView.fxml"));

        AnchorPane root = fxmlLoader.load();
        SingleBookViewController controller = fxmlLoader.<SingleBookViewController>getController();
        controller.initData(selectedItem);

        this.rootPane.getChildren().setAll(root);
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
