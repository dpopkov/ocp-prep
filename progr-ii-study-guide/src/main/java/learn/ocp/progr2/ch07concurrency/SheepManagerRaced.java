package learn.ocp.progr2.ch07concurrency;

public class SheepManagerRaced implements SheepManager {
    public static final int NUM_THREADS = 100;
    public static final int NUM_SHEEP = 100_000;
    private int sheepCount = 0;

    @Override
    public int getSheepCount() {
        return sheepCount;
    }

    @Override
    public void incrementAndReport() {
        System.out.println((++sheepCount) + " ");
    }

    public static void main(String[] args) {
        SheepManager manager = new SheepManagerRaced();
        new CountSheepService(NUM_THREADS, NUM_SHEEP).serve(manager);
    }
}
