package greeter.hello.util;

public class CapitalizeUtil {
    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}