package hockeycoach.mainClasses;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public class Picture {
    int ID;
    String pictureName;
    String targetPath;
    String originalPath;
    Image image;

    public Picture() {
    }

    public Picture(int ID, String pictureName, String targetPath, Image image) {
        this.ID = ID;
        this.pictureName = pictureName;
        this.targetPath = targetPath;
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

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }
}
