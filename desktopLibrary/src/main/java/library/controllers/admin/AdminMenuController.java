package library.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import library.entities.User;
import library.utilities.LoaderProvider;

import java.io.IOException;

public class AdminMenuController {


    @FXML
    private AnchorPane anchorPane;

    private User user;

    @FXML
    private void allUsersClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/admin/allUsersView.fxml"));
        AnchorPane root = fxmlLoader.load();
        ViewAllUsersAdminController controller = fxmlLoader.<ViewAllUsersAdminController>getController();
        controller.initData(this.user);
        this.anchorPane.getChildren().setAll(root);
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
