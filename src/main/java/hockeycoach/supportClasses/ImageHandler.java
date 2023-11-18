package hockeycoach.supportClasses;

import hockeycoach.mainClasses.Picture;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageHandler {
    public Picture setPicture(ImageView imageView, TextField imageName, String suffix, String targetPath){
        Picture picture = new Picture();

        URI url = URI.create(imageView.getImage().getUrl());
        Path path = Paths.get(url);
        String extension = getFileExtension(path);

        picture.setImage(imageView.getImage());
        picture.setPictureName(imageName.getText() + suffix + extension);
        picture.setTargetPath(targetPath  + picture.getPictureName());
        picture.setOriginalPath(path.toString());

        return picture;
    }

    private String getFileExtension(Path filePath) {
        String fileName = filePath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

    public void copyImage(Picture picture){
        try{
            Files.copy(Path.of(picture.getOriginalPath()),Path.of(picture.getTargetPath()),StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
//    public String copyImage(Path sourceImagePath, Path destinationDirectory, String fileName) {
//        try {
//            Files.createDirectories(destinationDirectory);
//            String fileExtension = getFileExtension(sourceImagePath);
//            Path destinationPath = destinationDirectory.resolve(fileName+fileExtension);
//            Files.copy(sourceImagePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//            return destinationPath + "/" + fileName;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }


}
