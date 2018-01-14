package library.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import library.entities.User;
import library.utilities.LoaderProvider;

import java.awt.print.Book;
import java.io.IOException;

public class MoreInfoController {
    @FXML
    private TextArea userNameField;
    @FXML
    private Label userNameEmptyLabel;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextArea emailField;
    @FXML
    private TableView<Book> table;
    private User user;


    @FXML
    void exitClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/admin/allUsersView.fxml"));
        AnchorPane root = fxmlLoader.load();
        ViewAllUsersAdminController controller = fxmlLoader.<ViewAllUsersAdminController>getController();
        controller.initData(this.user);
        this.anchorPane.getChildren().setAll(root);
    }

    @FXML
    void deleteButtonClicked() {

    }

    @FXML
    void deleteUserClicked() {

    }

}
