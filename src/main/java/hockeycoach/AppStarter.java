package hockeycoach;

import hockeycoach.UI.PresentationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppStarter extends Application {
    public static double WIDTH = 1220; //Screen.getPrimary().getVisualBounds().getWidth();
    public static double HEIGHT = 855; //Screen.getPrimary().getVisualBounds().getHeight();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        FXMLLoader mainPaneLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
        Pane root = mainPaneLoader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Hockey Coach");
        stage.show();

        FXMLLoader startPageLoader = new FXMLLoader(getClass().getResource("start-page.fxml"));
        Pane contentPane = startPageLoader.load();

        AnchorPane anchorPane= (AnchorPane) root.lookup("#contentPane");
        anchorPane.getChildren().add(contentPane);
        AnchorPane.setTopAnchor(contentPane, 0.0);
        AnchorPane.setLeftAnchor(contentPane, 0.0);
        AnchorPane.setRightAnchor(contentPane, 0.0);
        AnchorPane.setBottomAnchor(contentPane, 0.0);

        PresentationModel presentationModel = new PresentationModel();
        presentationModel.initializeControls(contentPane);






    }

    public static void main(String[] args) {
        launch();
    }

}