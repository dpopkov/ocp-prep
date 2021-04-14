package learn.core2.ch09modules.useservice;

import learn.core2.ch09modules.greetsvc.GreeterService;

import java.util.ServiceLoader;

public class HelloService {
    public static void main(String[] args) {
        ServiceLoader<GreeterService> greeterLoader = ServiceLoader.load(GreeterService.class);
        String desiredLanguage = args.length > 0 ? args[0] : "de";
        GreeterService chosenGreeter = null;
        for (GreeterService greeter : greeterLoader) {
            if (greeter.getLocale().getLanguage().equals(desiredLanguage)) {
                chosenGreeter = greeter;
                break;
            }
        }
        if (chosenGreeter == null) {
            System.out.println("No suitable greeter");
        } else {
            System.out.println(chosenGreeter.greet("Modular World"));
        }
    }
}
