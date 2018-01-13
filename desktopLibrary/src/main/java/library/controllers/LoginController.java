package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.controllers.admin.AdminMenuController;
import library.entities.User;
import library.services.api.UserService;
import library.utilities.BCryptEncoder;
import library.utilities.LoaderProvider;
import library.utilities.UserServiceInstance;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private UserService userService;

    @FXML
    public void loginButtonClicked() throws IOException {

        User user = this.userService.getUserByUsername(this.username.getText());
        if (user != null && BCryptEncoder.checkPass(this.password.getText(), user.getPassword())) {
            FXMLLoader fxmlLoader = LoaderProvider.get();

            if (user.getRole().getName().equals("ROLE_ADMIN")) {
                fxmlLoader.setLocation(getClass().getResource("/FXML/admin/adminMenu.fxml"));
                AnchorPane root = fxmlLoader.load();
                AdminMenuController controller = fxmlLoader.<AdminMenuController>getController();
                controller.initData(user);
                this.rootPane.getChildren().setAll(root);
                return;
            }

            fxmlLoader.setLocation(getClass().getResource("/FXML/menu.fxml"));
            AnchorPane root = fxmlLoader.load();
            MenuController controller = fxmlLoader.<MenuController>getController();
            controller.initData(user);

            this.rootPane.getChildren().setAll(root);
        }

        this.errorLabel.setText("Wrong username or password!");
        return;
    }

    @FXML
    public void backToEntryClicked() throws IOException {
        GridPane entryScene = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.rootPane.getChildren().setAll(entryScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = UserServiceInstance.getInstance();
    }

}
