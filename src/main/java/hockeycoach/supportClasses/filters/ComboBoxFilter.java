package hockeycoach.supportClasses.filters;

import hockeycoach.mainClasses.Drill;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.util.List;

public abstract class ComboBoxFilter {
    public abstract void setComboboxText(ComboBox comboBox, String label);
}
