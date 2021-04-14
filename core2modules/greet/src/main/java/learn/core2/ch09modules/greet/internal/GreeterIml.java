package learn.core2.ch09modules.greet.internal;

import learn.core2.ch09modules.greet.Greeter;

public class GreeterIml implements Greeter {
    @Override
    public String greet(String subject) {
        return "Hello, " + subject + "!";
    }
}
