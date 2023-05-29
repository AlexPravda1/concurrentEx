package org.example;

public class D_Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend friend) {
            System.out.format("%s: %s has bowed to me!%n",
                    this.name, friend.getName());
            friend.bowBack(this);
        }
        public synchronized void bowBack(Friend friend) {
            System.out.format("%s: %s has bowed back to me!%n",
                    this.name, friend.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Friend alex = new Friend("Alex");
        final Friend roger = new Friend("Roger");
        new Thread(() -> alex.bow(roger)).start();
        new Thread(() -> roger.bow(alex)).start();

        while (Thread.currentThread().isAlive()) {
            threadMessage("Still waiting for other Friend");
            Thread.sleep(2000);
        }
    }

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }
}