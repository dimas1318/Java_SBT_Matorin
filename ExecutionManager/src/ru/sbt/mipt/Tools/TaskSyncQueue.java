package ru.sbt.mipt.Tools;

import java.util.LinkedList;
import java.util.Queue;

public class TaskSyncQueue<T extends Runnable> {
    private final Queue<T> taskQueue = new LinkedList<>();

    public void addLastTask(T task) {
        synchronized (taskQueue){
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public T getFirst() throws InterruptedException {
        synchronized (taskQueue){
            while (taskQueue.isEmpty()){
                taskQueue.wait();
            }
            return taskQueue.remove();
        }
    }

    public boolean isEmpty(){
        boolean result;
        synchronized (taskQueue){
            result = taskQueue.isEmpty();
        }
        return result;
    }

    public int removeAll(){
        synchronized (taskQueue){
            int size = taskQueue.size();
            taskQueue.clear();
            return size;
        }
    }
}
