package library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import library.dataBroker.Manager;
import library.utilities.ConfirmBox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Field;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        root.setFocusTraversable(true);
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("iconsAndWallpapers/icon.jpg"));
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
        if (confirmation) {
            EntityManager em = Manager.getInstance();
            em.close();
            try {
                Class<?> managerClass = Manager.class;
                Field emfField = managerClass.getDeclaredField("emf");
                emfField.setAccessible(true);
                EntityManagerFactory emf = (EntityManagerFactory) emfField.get(null);
                emf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.primaryStage.close();
        }
    }
}
