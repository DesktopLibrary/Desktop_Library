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
<<<<<<< HEAD
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
=======
>>>>>>> c4d5987e21f2c17f044c015316863fa4ae9b3111
import library.entities.Book;
import library.entities.User;
import library.services.api.BookService;
import library.services.api.UserService;
import library.services.impl.BookServiceImpl;
import library.services.impl.UserServiceImpl;
import library.utilities.ConfirmBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserBooksController implements Initializable {

    @FXML
    private TableView<Book> table;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane rootPane;
<<<<<<< HEAD
=======

    private User user;
    private UserService userService;
    private BookService bookService;
>>>>>>> c4d5987e21f2c17f044c015316863fa4ae9b3111

    @FXML
    void deleteButtonClicked() {
        Book selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Boolean confirmation = ConfirmBox.display("DeleteBook", "Would you like to delete this book?");
            if (confirmation) {
                table.getItems().remove(selectedItem);
                this.bookService.deleteBookById(selectedItem);
            }
        } else {
            errorLabel.setText("Please select a book.");
        }
    }

    @FXML
    void editButtonClicked() throws IOException {
        Book selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/editBook.fxml"));

            AnchorPane root = fxmlLoader.load();
            EditBookController controller = fxmlLoader.<EditBookController>getController();
            controller.initData(this.user, selectedItem);

            this.rootPane.getChildren().setAll(root);
        }else {
            this.errorLabel.setText("Please select book first!");
        }
    }

    @FXML
    void backToMainMenuClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/menu.fxml"));
        AnchorPane root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(user);

        this.rootPane.getChildren().setAll(root);
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

    public void initData(User user) {
        this.user = user;
        List<Book> booksByUser = this.bookService.getBooksByUserId(user.getId());
        ObservableList<Book> observableList = FXCollections.observableList(booksByUser);
        this.table.setItems(observableList);
    }
}
