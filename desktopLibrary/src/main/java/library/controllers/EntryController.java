package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class EntryController {

    @FXML
    private GridPane rootPane;

    @FXML
    public void loginButtonClicked() throws IOException {
        AnchorPane loginScene = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        this.rootPane.getChildren().setAll(loginScene);
    }

    @FXML
    public void registerButtonClicked() throws IOException {
        AnchorPane registerScene = FXMLLoader.load(getClass().getResource("/FXML/register.fxml"));
        this.rootPane.getChildren().setAll(registerScene);
    }
}
