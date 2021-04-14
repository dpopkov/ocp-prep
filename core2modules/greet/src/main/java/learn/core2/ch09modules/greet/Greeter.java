package learn.core2.ch09modules.greet;

import learn.core2.ch09modules.greet.internal.GreeterIml;

public interface Greeter {

    static Greeter newInstance() {
        return new GreeterIml();
    }

    String greet(String subject);
}
