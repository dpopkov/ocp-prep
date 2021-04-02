package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManagerCyclic {

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

    public void performTasks(CyclicBarrier removeLionsBarrier, CyclicBarrier cleanPenBarrier) {
        try {
            performTask(this::removeLions);
            removeLionsBarrier.await();
            performTask(this::cleanPen);
            cleanPenBarrier.await();
            performTask(this::addLions);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(NUM_WORKERS);
            var manager = new LionPenManagerCyclic();
            var c1 = new CyclicBarrier(NUM_WORKERS, () -> System.out.println("*** Lions removed!"));
            var c2 = new CyclicBarrier(NUM_WORKERS, () -> System.out.println("*** Pen Cleaned!"));
            for (int i = 0; i < NUM_WORKERS; i++) {
                service.submit(() -> manager.performTasks(c1, c2));
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
