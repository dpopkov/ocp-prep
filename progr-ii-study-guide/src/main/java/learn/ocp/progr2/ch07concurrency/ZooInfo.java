package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Example of Single-Thread Executor.
 * Results are guaranteed to be executed sequentially.
 */
public class ZooInfo {
    public static void main(String[] args) {
        ExecutorService service = null;
        Runnable task1 = () -> System.out.println("Printing zoo inventory");
        Runnable task2 = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Printing record: " + i);
            }
        };
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            serviceState("State of created service", service);
            service.execute(task1);
            service.execute(task2);
            service.execute(task1);
            System.out.println("end");
        } finally {
            if (service != null) {
                service.shutdown();
                serviceState("State after shutdown", service);
            }
        }
    }

    private static void serviceState(String msg, ExecutorService service) {
        String text = msg + ": {" +
                "isShutdown=" + service.isShutdown() + ", " +
                "isTerminated=" + service.isTerminated() +
                "}";
        System.out.println(text);
    }
}
