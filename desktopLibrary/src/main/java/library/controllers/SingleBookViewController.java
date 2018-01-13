package library.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import library.entities.Book;
import library.entities.User;
import library.utilities.LoaderProvider;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SingleBookViewController {

    @FXML
    public AnchorPane rootPane;
    @FXML
    private TextField titleTextField;
    @FXML
    private ImageView pictureSpace;
    @FXML
    private TextArea summaryTextField;
    @FXML
    private TextField authorTextField;

    private User user;
    private Book selectedItem;

    @FXML
    public void backToMainMenuClicked() throws IOException {
        FXMLLoader fxmlLoader = LoaderProvider.get();
        fxmlLoader.setLocation(getClass().getResource("/FXML/userBooks.fxml"));
        AnchorPane root = fxmlLoader.load();
        UserBooksController controller = fxmlLoader.<UserBooksController>getController();
        controller.initData(this.user);
        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    public void searchInGoogle() {
        if (Desktop.isDesktopSupported()) {
            try {
                String title = this.selectedItem.getTitle().replace(" ", "+");
                String author = this.selectedItem.getAuthor().replace(" ", "+");
                String google = String.format("https://www.google.bg/search?tbm=bks&q=%s+%s", author, title);
                Desktop.getDesktop().browse(new URI(google));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void initData(User user, Book selectedItem) {
        this.user = user;
        this.selectedItem = selectedItem;
        this.titleTextField.setEditable(false);
        this.titleTextField.setMouseTransparent(true);
        this.titleTextField.setFocusTraversable(false);
        this.titleTextField.setText(selectedItem.getTitle());
        this.authorTextField.setEditable(false);
        this.authorTextField.setMouseTransparent(true);
        this.authorTextField.setFocusTraversable(false);
        this.authorTextField.setText(selectedItem.getAuthor());
        this.summaryTextField.setEditable(false);
        this.summaryTextField.setFocusTraversable(false);
        this.summaryTextField.setText(selectedItem.getSummary());
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\book_images\\";
        File file = new File(imagePath + "no-image.jpg");
        if (selectedItem.getPicture() != null) {
            file = new File(imagePath + selectedItem.getPicture());
        }
        Image image = new Image(file.toURI().toString());
        this.pictureSpace.setImage(image);
    }
}


