package learn.core2.ch09modules.greetsvc;

import java.util.Locale;

public interface GreeterService {

    String greet(String subject);

    Locale getLocale();
}
