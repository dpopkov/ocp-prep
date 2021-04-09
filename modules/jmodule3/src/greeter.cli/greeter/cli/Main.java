package greeter.cli;

import greeter.hello.service.HelloMessageService;

public class Main {
    public static void main(String[] args) {
        HelloMessageService service = new HelloMessageService();
        String message = service.getMessage();
        System.out.println(message + " from a module!");
    }
}
