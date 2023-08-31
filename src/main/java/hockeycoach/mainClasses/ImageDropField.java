package hockeycoach.mainClasses;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImageDropField {
    String target;
    DragEvent event;
    TextField textField;

    public ImageDropField(String target, TextField textField){
        this.target= target;
        this.textField = textField;
    }

    public void initializeControls(){
        textField.setOnDragOver(this::onDragOver);
        textField.setOnDragDropped(this::onDragDropped);
    }


    private void onDragOver(DragEvent event){
        Dragboard dragboard = event.getDragboard();
        if(dragboard.hasFiles() && isImageFile(dragboard.getFiles().get(0))) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void onDragDropped(DragEvent event){
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasFiles()){
            File sourceImage = dragboard.getFiles().get(0);
            File targetFolder =new File(target);
            targetFolder.mkdirs();

            try {
                String targetFileName = sourceImage.getName();
                Path targetFilePath = targetFolder.toPath().resolve(targetFileName);
                Files.copy(sourceImage.toPath(), targetFilePath, StandardCopyOption.REPLACE_EXISTING);
                success = true;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
    private boolean isImageFile(File file){
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".png");
    }
}
