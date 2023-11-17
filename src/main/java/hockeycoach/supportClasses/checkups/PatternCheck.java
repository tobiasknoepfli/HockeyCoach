package hockeycoach.supportClasses.checkups;

import java.util.regex.Pattern;

public class PatternCheck {
    public static int isNumericElse(String string, int output) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (pattern.matcher(string).matches()) {
            return Integer.parseInt(string);
        }
        return output;
    }
}
