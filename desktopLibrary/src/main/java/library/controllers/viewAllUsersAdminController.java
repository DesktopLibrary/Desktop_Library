package library.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import library.entities.Book;
import library.entities.User;
import library.services.api.BookService;
import library.services.api.UserService;

import java.io.IOException;
import java.util.List;

public class viewAllUsersAdminController {
    @FXML
    private Button deleteButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button moreInfoButton;
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<User> table;

    private User user;
    private UserService userService;
    private BookService bookService;


    @FXML
    void descriptionButtonClicked() {

    }

    @FXML
    void deleteButtonClicked() {

    }



    @FXML
    void backToMainMenuClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/adminMenu.fxml"));
        AnchorPane root = fxmlLoader.load();
       AdminMenuController controller = fxmlLoader.<AdminMenuController>getController();
        controller.initData(user);
        this.anchorPane.getChildren().setAll(root);
    }

    public void initData(User user) {
        this.user = user;
        List<User> booksByUser = this.userService.getAllUsers();
        ObservableList<User> observableList = FXCollections.observableList(booksByUser);
        this.table.setItems(observableList);
    }

}