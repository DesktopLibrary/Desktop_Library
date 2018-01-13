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
    void initialize() {

    }

    @FXML
    public void backToMainMenuClicked() throws IOException {
        FXMLLoader booksLoader = new FXMLLoader(getClass().getResource("/FXML/userBooks.fxml"));
        AnchorPane root = booksLoader.load();
        UserBooksController controller = booksLoader.<UserBooksController>getController();
        controller.initData(user);

        this.rootPane.getChildren().setAll(root);
    }

    @FXML
    public void searchInGoogle() {
        if (Desktop.isDesktopSupported()) {
            try {
                String title = selectedItem.getTitle().replace(" ", "+");
                String author = selectedItem.getAuthor().replace(" ", "+");
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
        titleTextField.setEditable(false);
        titleTextField.setMouseTransparent(true);
        titleTextField.setFocusTraversable(false);
        titleTextField.setText(selectedItem.getTitle());
        authorTextField.setEditable(false);
        authorTextField.setMouseTransparent(true);
        authorTextField.setFocusTraversable(false);
        authorTextField.setText(selectedItem.getAuthor());
        summaryTextField.setEditable(false);
        summaryTextField.setFocusTraversable(false);
        summaryTextField.setText(selectedItem.getSummary());
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\book_images\\";
        File file = new File(imagePath + "no-image.jpg");
        if (selectedItem.getPicture() != null) {
            file = new File(imagePath + selectedItem.getPicture());
        }
        Image image = new Image(file.toURI().toString());
        pictureSpace.setImage(image);
    }
}


