package ru.sbt.mipt;

public class Main {
    public static void main(String[] args) {
        int numberOfTasks = 3;
        int numberOfThreads = 4;

        class Task implements Runnable {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.printf("%s is running.\n", Thread.currentThread().getName());
            }
        }

        class Callback implements Runnable {
            @Override
            public void run() {
                System.out.println("Call of callback.");
            }
        }

        ExecutionManager executorManager = new ExecutionManagerImpl(numberOfThreads);
        Runnable[] tasks = new Task[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            tasks[i] = new Task();
        }

        Context context = executorManager.execute(new Callback(), tasks);

        try {
            while (!context.isFinished()) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.interrupt();
    }
}
