package hockeycoach.mainClasses;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Picture {
    int ID;
    String pictureName;
    String imagePath;
    Image image;

    public Picture() {
    }

    public Picture(int ID, String pictureName, String imagePath,Image image) {
        this.ID = ID;
        this.pictureName = pictureName;
        this.imagePath = imagePath;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public Image byteToImage(byte[] imageData) {
        return new Image(new ByteArrayInputStream(imageData));
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String copyImage(Path sourceImagePath, Path destinationDirectory, String fileName) {
        try {
            Files.createDirectories(destinationDirectory);
            String fileExtension = getFileExtension(sourceImagePath);
            Path destinationPath = destinationDirectory.resolve(fileName+fileExtension);
            Files.copy(sourceImagePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return destinationPath + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getFileExtension(Path filePath) {
        String fileName = filePath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

}
