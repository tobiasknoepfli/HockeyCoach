package hockeycoach.mainClasses;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public class Picture {
    int ID;
    Image image;
    String pictureName;

    public Picture(){
    }

    public Picture(int ID, Image image, String pictureName){
        this.ID = ID;
        this.image = image;
        this.pictureName = pictureName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public Image byteToImage(byte[] imageData){
        return new Image(new ByteArrayInputStream(imageData));
    }
}
