package ru.sbt.mipt.Tools;

import ru.sbt.mipt.Tools.PoolHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadPool<T extends Runnable> {
    private final int numberOfThreads;
    private final List<PoolHandler<T>> poolHandlers;
    private final TaskSyncQueue<T> taskSyncQueue;
    private int interruptedTasksCount;

    private static final Object LOCK = new Object();

    public ThreadPool(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;

        this.taskSyncQueue = new TaskSyncQueue<>();

        this.poolHandlers = Arrays.asList(new PoolHandler[numberOfThreads]);
        for (int i = 0; i < numberOfThreads; i++) {
            poolHandlers.set(i, new PoolHandler(taskSyncQueue));
            poolHandlers.get(i).start();
        }

        this.interruptedTasksCount = 0;
    }

    public void addTask(T task) throws Exception {
        if (task != null) {
            taskSyncQueue.addLastTask(task);
        } else {
            throw new Exception("The task for a thread is not determined.");
        }
    }

    public void stop() {
        for (PoolHandler<T> poolHandler : poolHandlers) {
            poolHandler.interrupt();
        }
    }

    public int getCompletedTaskCount() {
        int completedTaskCount = 0;

        for (PoolHandler<T> poolHandler : poolHandlers) {
            completedTaskCount += poolHandler.getCompletedTasksCount();
        }

        return completedTaskCount;
    }

    public int getFailedTaskCount() {
        int failedTaskCount = 0;

        for (PoolHandler<T> poolHandler : poolHandlers) {
            failedTaskCount += poolHandler.getFailedTasksCount();
        }

        return failedTaskCount;
    }

    public int getInterruptedTaskCount() {
        synchronized (LOCK){
            return interruptedTasksCount;
        }
    }

    public void interrupt() {
        synchronized (LOCK){
            interruptedTasksCount = taskSyncQueue.removeAll();
        }
        this.stop();
    }
}
