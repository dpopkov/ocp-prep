package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.Callable;

class CallableWorker implements Callable<String> {
    private final int id;
    private final int volume;

    @SuppressWarnings("SameParameterValue")
    CallableWorker(int id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String call() {
        try {
            for (int i = 0; i < volume; i++) {
                Thread.sleep(1);
            }
            return "Worker #" + id + " finished normally";
        } catch (InterruptedException e) {
            System.out.println("Worker #" + id + " interrupted");
            return "Worker #" + id + " finished interrupted";
        }
    }
}
