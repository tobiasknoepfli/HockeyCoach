package hockeycoach.supportClasses;

import java.util.Optional;
import java.util.function.Function;

public class NullCheck {
    public static <T,R> R isNotNullElse(T object, Function<T, R> action, R returnValueIfNull){
        return Optional.ofNullable(object)
                .map(action)
                .orElse((R) returnValueIfNull);
    }
}
