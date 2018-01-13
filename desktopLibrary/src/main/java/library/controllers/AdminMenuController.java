package library.controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import library.entities.User;
import library.services.api.BookService;
import library.services.api.UserService;

import java.awt.*;
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
    private void allUsersClicked(){

    }

    @FXML
    private void allBooksClicked(){

    }

    @FXML
    void exitClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.anchorPane.getChildren().setAll(entryScene);
    }

}
