package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManagerChaotic {

    private static final int NUM_WORKERS = 10;

    private void pause() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void removeLions() {
        System.out.println("Removing lions");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addLions() {
        System.out.println("Adding lions");
    }

    private void performTask(Runnable r) {
        r.run();
        pause();
    }

    public void performTasks() {
        performTask(this::removeLions);
        performTask(this::cleanPen);
        performTask(this::addLions);
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(NUM_WORKERS);
            var manager = new LionPenManagerChaotic();
            for (int i = 0; i < NUM_WORKERS; i++) {
                service.submit(manager::performTasks);
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
