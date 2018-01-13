package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.entities.Role;
import library.entities.User;
import library.services.api.RoleService;
import library.services.api.UserService;
import library.services.impl.RoleServiceImpl;
import library.services.impl.UserServiceImpl;
import library.utilities.BCryptEncoder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

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

    private UserService userService;
    private RoleService roleService;

    @FXML
    public void regButtonClicked() throws IOException {
        if (this.userService.getAllUsers().stream().filter(u -> u.getUsername().equals(this.usernameField.getText())).count() > 0) {
            this.errorLabel.setText("This username is already taken!");
            return;
        }
        if (!this.passwordField.getText().equals(this.confirmPasswordField.getText())) {
            this.errorLabel.setText("Passwords don't match!");
            return;
        }
        if (this.userService.getAllUsers().stream().filter(u -> u.getEmail().equals(this.emailField.getText())).count() > 0) {
            this.errorLabel.setText("There is already registered user with this email");
            return;
        }
        if (this.usernameField.getText().equals("")) {
            this.errorLabel.setText("Username cannot be empty");
            return;
        }
        if (this.passwordField.getText().equals("")) {
            this.errorLabel.setText("Password cannot be empty");
            return;
        }
        if (this.emailField.getText().equals("")) {
            this.errorLabel.setText("Email cannot be empty");
            return;
        }

        User user = new User(this.usernameField.getText(), BCryptEncoder.hashPassword(this.passwordField.getText()), this.emailField.getText());
        Role role = this.roleService.getRoleByName("ROLE_USER");
        user.setRole(role);
        this.userService.saveOrUpdate(user);
        AnchorPane loginScene = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        this.rootPane.getChildren().setAll(loginScene);
    }

    @FXML
    public void backButtonClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = new UserServiceImpl();
        this.roleService = new RoleServiceImpl();
    }
}
