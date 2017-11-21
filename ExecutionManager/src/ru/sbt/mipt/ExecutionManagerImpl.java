package ru.sbt.mipt;

import ru.sbt.mipt.Tools.ThreadPool;

public class ExecutionManagerImpl implements ExecutionManager {
    private final int numberOfThreads;
    private final ThreadPool<Runnable> threadPool;

    public ExecutionManagerImpl(int numberOfThreads) {
        if (numberOfThreads < 1) {
            throw new IllegalArgumentException("Number of threads must be more than 0.");
        }

        this.numberOfThreads = numberOfThreads;
        this.threadPool = new ThreadPool<>(numberOfThreads);
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        Context context = new ContextImpl(tasks.length);

        new Thread(() -> {
            try{
                while (!context.isFinished()){
                    Thread.sleep(500);
                }
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
            if (context.getFailedTaskCount() == 0 || context.getInterruptedTaskCount() == 0) {
                callback.run();
            }
        }).start();

        try {
            for (Runnable task : tasks) {
                threadPool.addTask(task);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return context;
    }

    private class ContextImpl implements Context {
        private final int numberOfTasks;

        private ContextImpl(int numberOfTasks) {
            this.numberOfTasks = numberOfTasks;
        }

        @Override
        public int getCompletedTaskCount() {
            return threadPool.getCompletedTaskCount();
        }

        @Override
        public int getFailedTaskCount() {
            return threadPool.getFailedTaskCount();
        }

        @Override
        public int getInterruptedTaskCount() {
            return threadPool.getInterruptedTaskCount();
        }

        @Override
        public void interrupt() {
            threadPool.interrupt();
        }

        @Override
        public boolean isFinished() {
            return (numberOfTasks == getCompletedTaskCount() + getFailedTaskCount());
        }
    }
}
