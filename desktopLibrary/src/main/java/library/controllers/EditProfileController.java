package library.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.entities.User;
import library.services.api.UserService;
import library.utilities.BCryptEncoder;
import library.utilities.LoaderProvider;
import library.utilities.UserServiceInstance;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;

    private User user;
    private UserService userService;

    @FXML
    public void editButtonClicked() throws IOException {
        String emailString = this.emailField.getText();
        String passwordString = this.passwordField.getText();

        if (!emailString.equals("")) {
            this.user.setEmail(emailString);
        } else {
            this.errorLabel.setText("Email field cannot be empty!");
            return;
        }

        if (!passwordString.equals("")) {
            this.user.setPassword(BCryptEncoder.hashPassword(passwordString));
        }

        this.userService.saveOrUpdate(this.user);

        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/menu.fxml"));
        AnchorPane root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    private void backButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/menu.fxml"));
        AnchorPane root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = UserServiceInstance.getInstance();
    }

    public void initData(User user) {
        this.user = user;
        this.usernameField.setEditable(false);
        this.usernameField.setMouseTransparent(true);
        this.usernameField.setFocusTraversable(false);
        this.usernameField.setText(this.user.getUsername());
        this.emailField.setText(this.user.getEmail());
    }
}
