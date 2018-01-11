package library.utilities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class ImageUpload {

    private static final FileChooser fileChooser = new FileChooser();

    private ImageUpload() {
    }

    public static FileChooser getInstance() {
        return fileChooser;
    }

    public static void configureFileChooser() {
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
        FileChooser.ExtensionFilter extFilterBMP = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.BMP");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterGIF, extFilterBMP);
    }


    public static String saveToFile(String outputPath, Window stage) throws IOException {
        File file = fileChooser.showOpenDialog(stage);
        String imagePath = file.toURI().toString();
        Image image = new Image(imagePath);

        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);


        if (file != null) {

            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.indexOf(".") + 1, file.getName().length());

            String outputFileName = UUID.randomUUID().toString() + "." + fileExtension;
            File outputFile = new File(outputPath + outputFileName);
            try {
                ImageIO.write(bImage, fileExtension, outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return outputFileName;
        }

        return null;
    }
}
