package library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.utilities.ConfirmBox;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("DesktopLibrary");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }


    public void closeProgram() {
        Boolean confirmation = ConfirmBox.display("ExitWindow", "Do you really want to exit?");

        if(confirmation){
            this.primaryStage.close();
        }
    }
}

