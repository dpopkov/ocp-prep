package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.*;

public class WaitForAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            service.submit(new Worker(1,100));
            service.submit(new Worker(2,100));
            service.submit(new Worker(3,100));
        } finally {
            if (service != null) {
                service.shutdown();
                System.out.println("Service is shutdown: " + service.isShutdown());
            }
        }
        service.awaitTermination(1, TimeUnit.MINUTES);
        if (service.isTerminated()) {
            System.out.println("Finished!");
        } else {
            System.out.println("At least one task is still running");
        }
    }

    private static class Worker implements Runnable {
        private final int id;
        private final int volume;

        @SuppressWarnings("SameParameterValue")
        private Worker(int id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < volume; i++) {
                    Thread.sleep(1);
                }
                System.out.println("Worker #" + id + " finished");
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted", e);
            }
        }
    }
}
