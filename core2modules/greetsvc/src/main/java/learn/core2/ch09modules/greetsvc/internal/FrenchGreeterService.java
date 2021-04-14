package learn.core2.ch09modules.greetsvc.internal;

import learn.core2.ch09modules.greetsvc.GreeterService;

import java.util.Locale;

public class FrenchGreeterService implements GreeterService {
    @Override
    public String greet(String subject) {
        return "Bonjour " + subject;
    }

    @Override
    public Locale getLocale() {
        return Locale.FRENCH;
    }
}
