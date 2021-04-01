package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class SheepManagerAtomic implements SheepManager {
    public static final int NUM_THREADS = 100;
    public static final int NUM_SHEEP = 100_000;
    private final AtomicInteger sheepCount = new AtomicInteger(0);

    @Override
    public int getSheepCount() {
        return sheepCount.get();
    }

    @Override
    public void incrementAndReport() {
        System.out.println((sheepCount.incrementAndGet()) + " ");
    }

    public static void main(String[] args) {
        SheepManager manager = new SheepManagerAtomic();
        new CountSheepService(NUM_THREADS, NUM_SHEEP).serve(manager);
    }
}
