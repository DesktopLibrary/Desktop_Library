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

public class RegisterController implements Initializable {

    private UserService userService;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField emailField;


    @FXML
    public void regButtonClicked() throws IOException {
        if (this.userService.getAllUsers().stream().filter(u -> u.getUsername().equals(usernameField.getText())).count() > 0) {
            this.errorLabel.setText("This username is already taken!");
        }
        if (!this.passwordField.getText().equals(this.confirmPasswordField.getText())) {
            this.errorLabel.setText("Passwords don't match!");
        }
        if (this.userService.getAllUsers().stream().filter(u -> u.getEmail().equals(emailField.getText())).count() > 0) {
            this.errorLabel.setText("There is already registered user with this email");
        }

        User user = new User(this.usernameField.getText(), this.passwordField.getText(), this.emailField.getText());
        this.userService.saveOrUpdate(user);
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }

    @FXML
    public void backButtonClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = new UserServiceImpl();
    }
}
