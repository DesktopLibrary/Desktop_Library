package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.entities.User;
import library.services.api.UserService;
import library.services.impl.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private UserService userService;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void loginButtonClicked() throws IOException {
        if (this.userService.userLogin(this.username.getText(), this.password.getText()) == null) {
            this.errorLabel.setText("Incorrect username or password!");
            return;
        }

        User user = this.userService.userLogin(this.username.getText(), this.password.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/menu.fxml"));

        AnchorPane root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(user);

        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    public void backToEntryClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = new UserServiceImpl();
    }
}
