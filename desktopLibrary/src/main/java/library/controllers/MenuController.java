package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.entities.User;
import library.utilities.LoaderProvider;

import java.io.IOException;

public class MenuController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label loggedName;

    private User user;

    @FXML
    public void viewBooksClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/userBooks.fxml"));
        AnchorPane root = fxmlLoader.load();
        UserBooksController controller = fxmlLoader.<UserBooksController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    public void editProfileClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/editProfile.fxml"));
        AnchorPane root = fxmlLoader.load();
        EditProfileController controller = fxmlLoader.<EditProfileController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    public void addBookClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/addBook.fxml"));
        AnchorPane root = fxmlLoader.load();
        AddBookController controller = fxmlLoader.<AddBookController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    void exitClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }

    public void initData(User user) {
        this.user = user;
        this.loggedName.setText("Welcome, " + this.user.getUsername() + "!");
    }
}
