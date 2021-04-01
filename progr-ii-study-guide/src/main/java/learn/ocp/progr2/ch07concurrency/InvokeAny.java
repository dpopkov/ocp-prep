package learn.ocp.progr2.ch07concurrency;

import java.util.List;
import java.util.concurrent.*;

public class InvokeAny {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            List<Callable<String>> tasks = List.of(
                    new CallableWorker(1, 100),
                    new CallableWorker(2, 100),
                    new CallableWorker(3, 100)
            );
            String firstResult = service.invokeAny(tasks);
            System.out.println(firstResult);
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
