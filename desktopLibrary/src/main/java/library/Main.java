package library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import library.dataBroker.Manager;
import library.entities.Role;
import library.entities.User;
import library.services.api.RoleService;
import library.services.api.UserService;
import library.services.impl.RoleServiceImpl;
import library.services.impl.UserServiceImpl;
import library.utilities.ConfirmBox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Field;
import java.util.logging.Level;

public class Main extends Application {

    private Stage primaryStage;
    private RoleService roleService;
    private UserService userService;

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.roleService = new RoleServiceImpl();
        this.userService = new UserServiceImpl();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        root.setFocusTraversable(true);
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("iconsAndWallpapers/icon.png"));
        primaryStage.setTitle("DesktopLibrary");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        if(this.userService.getAllUsers().stream().filter(u->u.getRole().getName().equals("ROLE_ADMIN")).count()==0){
            User user = new User();
            Role role = this.roleService.getRoleByName("ROLE_ADMIN");
            user.setRole(role);
            user.setEmail("admin@admin.com");
            user.setPassword("admin");
            user.setUsername("admin");
            this.userService.saveOrUpdate(user);
        }
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
