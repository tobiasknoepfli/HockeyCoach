module com.example.hockeycoach {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.hockeycoach to javafx.fxml;
    exports com.example.hockeycoach;
}