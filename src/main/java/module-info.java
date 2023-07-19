module com.example.hockeycoach {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens hockeycoach to javafx.fxml;
    exports hockeycoach;
    exports hockeycoach.mainClasses;
    opens hockeycoach.mainClasses to javafx.fxml;
    exports hockeycoach.controllers;
    opens hockeycoach.controllers to javafx.fxml;
}