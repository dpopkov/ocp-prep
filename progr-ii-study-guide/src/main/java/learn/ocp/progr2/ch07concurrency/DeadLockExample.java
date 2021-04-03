package learn.ocp.progr2.ch07concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockExample {
    static class Food {}

    static class Water {}

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    static class Fox {
        private final String name;

        Fox(String name) {
            this.name = name;
        }

        public void eatAndDrink(Food food, Water water) {
            synchronized (food) {
                System.out.println(name + " got Food!");
                move();
                synchronized (water) {
                    System.out.println(name + " got Water!");
                }
            }
        }

        public void drinkAndEat(Water water, Food food) {
            synchronized (water) {
                System.out.println(name + " got Water!");
                move();
                synchronized (food) {
                    System.out.println(name + " got Food!");
                }
            }
        }

        private void move() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Moving");
        }
    }

    public static void main(String[] args) {
        Fox foxy = new Fox("Foxy");
        Fox tails = new Fox("Tails");
        Food food = new Food();
        Water water = new Water();
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(2);
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(water, food));
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }

    }
}
