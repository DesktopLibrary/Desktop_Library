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
import library.utilities.*;

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
        this.roleService = RoleServiceInstance.getInstance();
        this.userService = UserServiceInstance.getInstance();

        if (this.roleService.getAllRoles().size() == 0) {
            seedRolesInDB();
        }

        if (this.userService.getAllUsers().stream().filter(u -> u.getRole().getName().equals("ROLE_ADMIN")).count() == 0) {
            seedAdminInDB();
        }


        Parent root = FXMLLoader.load(getClass().getResource("/FXML/entry.fxml"));
        root.setFocusTraversable(true);
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("iconsAndWallpapers/icon.png"));
        primaryStage.setTitle("DesktopLibrary");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    private void seedAdminInDB() {
        User user = new User();
        Role role = this.roleService.getRoleByName("ROLE_ADMIN");
        user.setRole(role);
        user.setEmail("admin@admin.com");
        user.setPassword(BCryptEncoder.hashPassword("admin"));
        user.setUsername("admin");
        this.userService.saveOrUpdate(user);
    }

    private void seedRolesInDB() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        this.roleService.saveOrUpdate(roleAdmin);
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        this.roleService.saveOrUpdate(roleUser);
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
