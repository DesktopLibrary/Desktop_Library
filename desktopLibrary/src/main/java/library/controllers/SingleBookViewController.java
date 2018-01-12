package library.controllers;

        import java.io.File;
        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextArea;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import library.entities.Book;
        import library.utilities.ImageUpload;

public class SingleBookViewController {
    @FXML
    private TextArea titleTextField;

    @FXML
    private ImageView pictureSpace;

    @FXML
    private TextArea sumaryTextField;

    @FXML
    private TextArea authorTextField;

    private Book selectedItem;

    @FXML
    void initialize() {


    }

    public void initData(Book selectedItem) {
        this.selectedItem=selectedItem;
        titleTextField.setText(selectedItem.getTitle());
        authorTextField.setText(selectedItem.getAuthor());
        sumaryTextField.setText(selectedItem.getSummary());
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\book_images\\snimka.jpg";
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toString());
        pictureSpace.setImage(image);

    }
}


