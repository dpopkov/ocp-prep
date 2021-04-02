package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SheepManagerTryLock implements SheepManager {
    public static final int NUM_THREADS = 100;
    public static final int NUM_SHEEP = 100_000;
    private int sheepCount = 0;
    private final Lock lock = new ReentrantLock();

    @Override
    public int getSheepCount() {
        return sheepCount;
    }

    @Override
    public void incrementAndReport() {
        if (lock.tryLock()) {
            try {
                System.out.println((++sheepCount) + " ");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Unable to acquire lock, doing something else");
        }
    }

    public static void main(String[] args) {
        SheepManagerTryLock manager = new SheepManagerTryLock();
        new CountSheepService(NUM_THREADS, NUM_SHEEP).serve(manager);
    }
}
