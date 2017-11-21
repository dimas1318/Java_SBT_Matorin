package ru.sbt.mipt.Tools;

class PoolHandler<T extends Runnable> extends Thread {
    private final TaskSyncQueue<T> taskSyncQueue;
    private int completedTasksCount;
    private int failedTasksCount;

    public PoolHandler(TaskSyncQueue<T> taskSyncQueue) {
        this.taskSyncQueue = taskSyncQueue;
        this.completedTasksCount = 0;
        this.failedTasksCount = 0;
    }

    public int getCompletedTasksCount() {
        return completedTasksCount;
    }

    public int getFailedTasksCount() {
        return failedTasksCount;
    }

    @Override
    public void run() {
        Runnable work;
        while (true){
            if (Thread.currentThread().isInterrupted()){
                break;
            }

            try {
                work = taskSyncQueue.getFirst();
                work.run();
                completedTasksCount++;
            } catch (InterruptedException e) {
                break;
            } catch (Exception e) {
                failedTasksCount++;
                System.out.println(e.getMessage());
            }
        }
    }
}
