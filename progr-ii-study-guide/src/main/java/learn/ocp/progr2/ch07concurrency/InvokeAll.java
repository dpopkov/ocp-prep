package learn.ocp.progr2.ch07concurrency;

import java.util.List;
import java.util.concurrent.*;

public class InvokeAll {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            List<Callable<String>> tasks = List.of(
                    new CallableWorker(1, 100),
                    new CallableWorker(2, 100),
                    new CallableWorker(3, 100)
            );
            List<Future<String>> futures = service.invokeAll(tasks);
            for (var future : futures) {
                System.out.println(future.get());
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
