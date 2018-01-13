package library.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.entities.User;
import library.services.api.BookService;
import library.services.api.UserService;

import java.io.IOException;

public class AdminMenuController {

    @FXML
    private Button allUsersButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button allBooksButton;
    @FXML
    private Button logoutButton;

    private UserService userService;
    private BookService bookService;
    private User user;


    @FXML
    private void allUsersClicked() throws IOException {
        FXMLLoader usersLoader = new FXMLLoader(getClass().getResource("/FXML/allUsersView.fxml"));

        AnchorPane root = usersLoader.load();
        viewAllUsersAdminController controller = usersLoader.<viewAllUsersAdminController>getController();
        controller.initData(user);

        this.anchorPane.getChildren().setAll(root);
    }

    @FXML
    private void allBooksClicked(){

    }

    @FXML
    void exitClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.anchorPane.getChildren().setAll(entryScene);
    }

    public void initData(User user) {
        this.user = user;
    }
}
