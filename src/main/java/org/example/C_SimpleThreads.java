package org.example;

public class C_SimpleThreads {

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {

        public void run() {
            String importantInfo[] = {
                    "Message #1",
                    "Message #2",
                    "Message #3",
                    "Message #4"
            };

            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String args[])
            throws InterruptedException {
        // Waiting time
        // long patience = 1000 * 60 * 60;
        long patience = 9000;

        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread messageThread = new Thread(new MessageLoop());
        messageThread.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop
        // thread exits
        while (messageThread.isAlive()) {
            threadMessage("Still waiting...");
            messageThread.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && messageThread.isAlive()) {
                threadMessage("Tired of waiting!");
                messageThread.interrupt();
                messageThread.join();
            }
        }
        threadMessage("Finally!");
    }
}