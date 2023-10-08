package hockeycoach.supportClasses;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Reflector {
    public Reflector() {
    }

    public Map<String, Object> mapFields(Object controller, FXMLLoader loader) {
        Class<?> controllerClass = controller.getClass();
        Field[] fields = controllerClass.getDeclaredFields();
        Map<String, Object> fieldMap = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);

        }

        return fieldMap;
    }
}
