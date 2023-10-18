package hockeycoach.mainClasses;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public class Picture {
    int pictureID;
    Image image;
    String pictureName;

    public Picture(){
    }

    public Picture(int pictureID,Image image, String pictureName){
        this.pictureID = pictureID;
        this.image = image;
        this.pictureName = pictureName;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
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
