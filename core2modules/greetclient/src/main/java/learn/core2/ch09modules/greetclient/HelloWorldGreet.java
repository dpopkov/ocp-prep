package learn.core2.ch09modules.greetclient;

import learn.core2.ch09modules.greet.Greeter;

public class HelloWorldGreet {
    public static void main(String[] args) {
        Greeter greeter = Greeter.newInstance();
        System.out.println(greeter.greet("Modular World"));
    }
}
