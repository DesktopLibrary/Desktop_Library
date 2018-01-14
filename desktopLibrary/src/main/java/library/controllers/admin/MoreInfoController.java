package library.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.entities.Book;
import library.entities.User;
import library.services.api.BookService;
import library.services.api.UserService;
import library.services.impl.BookServiceImpl;
import library.services.impl.UserServiceImpl;
import library.utilities.ConfirmBox;
import library.utilities.LoaderProvider;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MoreInfoController implements Initializable {

    @FXML
    public Label errorLabel;
    @FXML
    public TextField userNameField;
    @FXML
    public TextField emailField;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Book> table;

    private User user;
    private UserService userService;
    private BookService bookService;

    @FXML
    void deleteBookClicked() {
        Book selectedItem = this.table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Boolean confirmation = ConfirmBox.display("DeleteBook", "Would you like to delete this book?");
            if (confirmation) {
                this.table.getItems().remove(selectedItem);
                this.bookService.deleteBookById(selectedItem);
            }
        } else {
            this.errorLabel.setText("Please select a book.");
        }
    }

    @FXML
    void deleteUserClicked() throws IOException {
        Boolean confirmation = ConfirmBox.display("DeleteUser", "Would you like to delete this user?");
        if (confirmation) {
            List<Book> books = this.bookService.getBooksByUserId(this.user.getId());
            this.userService.deleteUserById(this.user, books);
            FXMLLoader fxmlLoader = LoaderProvider.get();
            fxmlLoader.setLocation(getClass().getResource("/FXML/admin/allUsersView.fxml"));
            AnchorPane root = fxmlLoader.load();
            ViewAllUsersAdminController controller = fxmlLoader.<ViewAllUsersAdminController>getController();
            controller.initData(this.user);
            this.rootPane.getChildren().setAll(root);
        }
    }

    @FXML
    void backClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/admin/allUsersView.fxml"));
        AnchorPane root = fxmlLoader.load();
        ViewAllUsersAdminController controller = fxmlLoader.<ViewAllUsersAdminController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }

    public void initData(User user) {
        this.user = user;
        List<Book> users = this.bookService.getBooksByUserId(this.user.getId());
        ObservableList<Book> observableList = FXCollections.observableList(users);
        this.table.setItems(observableList);
        this.userNameField.setEditable(false);
        this.userNameField.setMouseTransparent(true);
        this.userNameField.setFocusTraversable(false);
        this.userNameField.setText(this.user.getUsername());
        this.emailField.setEditable(false);
        this.emailField.setMouseTransparent(true);
        this.emailField.setFocusTraversable(false);
        this.emailField.setText(this.user.getEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = new UserServiceImpl();
        this.bookService = new BookServiceImpl();
    }
}
