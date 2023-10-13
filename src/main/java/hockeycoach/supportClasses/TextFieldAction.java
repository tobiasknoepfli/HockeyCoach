package hockeycoach.supportClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.util.Stack;

public class TextFieldAction extends Action {
    TextField textField;
    String oldValue, newValue;

    public TextFieldAction(){
    }

    public TextFieldAction(TextField textField, String oldValue, String newValue) {
        this.textField = textField;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void undo() {
        textField.setText(oldValue);
    }

    public TextField getTextField() {
        return textField;
    }

    public void setupTextFieldUndo(TextField textField, Stack<TextFieldAction> textFieldActions) {
        StringProperty oldValueProperty = new SimpleStringProperty(textField.getText());

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    String oldValueStr = oldValueProperty.get();
                    String newValueStr = textField.getText();
                    if (!oldValue.equals(newValue)) {
                        TextFieldAction action = new TextFieldAction(textField, oldValueStr, newValueStr);
                        textFieldActions.push(action);
                        oldValueProperty.set(newValueStr);
                    }
                }
            }
        });
    }

    public static void undoLastAction(Stack<TextFieldAction> textFieldActions) {
        if (!textFieldActions.isEmpty()) {
            TextFieldAction lastAction = textFieldActions.pop();
            lastAction.undo();
        }
    }
}
