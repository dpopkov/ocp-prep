package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountSheepService {
    private final int numThreads;
    private final int numSheep;

    public CountSheepService(int numThreads, int numSheep) {
        this.numThreads = numThreads;
        this.numSheep = numSheep;
    }

    public void serve(SheepManager manager) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(numThreads);
            for (int i = 0; i < numSheep; i++) {
                service.submit(manager::incrementAndReport);
            }
        } finally {
            if (service != null) {
                service.shutdown();
                try {
                    service.awaitTermination(3, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Sheep count = " + manager.getSheepCount());
        }
    }
}
