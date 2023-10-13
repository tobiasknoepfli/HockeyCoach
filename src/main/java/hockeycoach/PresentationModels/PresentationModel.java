package hockeycoach.PresentationModels;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

public abstract class PresentationModel {

    public abstract void initializeControls(Pane root);

    public abstract void getDBEntries(Pane root);

    public abstract void setupButtons(Pane root);

    public abstract void setupEventListeners(Pane root);

    public void createHoverInfo(Control control, String string) {
        Tooltip tooltip = new Tooltip(string);
        control.setTooltip(tooltip);
    }
}
