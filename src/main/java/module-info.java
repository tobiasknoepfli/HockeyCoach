module com.example.hockeycoach {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires tornadofx.controls;
    requires jfxtras.controls;

    opens hockeycoach to javafx.fxml;
    exports hockeycoach;
    exports hockeycoach.mainClasses;
    opens hockeycoach.mainClasses to javafx.fxml;
    exports hockeycoach.controllers;
    opens hockeycoach.controllers to javafx.fxml;
    exports hockeycoach.supportClasses;
    opens hockeycoach.supportClasses to javafx.fxml;
    exports hockeycoach.supportClasses.filters;
    opens hockeycoach.supportClasses.filters to javafx.fxml;
    exports hockeycoach.mainClasses.Lines;
    opens hockeycoach.mainClasses.Lines to javafx.fxml;
}