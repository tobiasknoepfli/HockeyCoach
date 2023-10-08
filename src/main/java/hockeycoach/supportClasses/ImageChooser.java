package hockeycoach.supportClasses;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.nio.channels.FileChannel;

public class ImageChooser {
    public Image chooseImage(MouseEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if(selectedFile !=null) {
            Image image = new Image(selectedFile.toURI().toString());
            return image;
        }
        return null;
    }
}
