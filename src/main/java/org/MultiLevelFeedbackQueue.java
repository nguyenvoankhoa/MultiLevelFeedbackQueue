package org;

import org.algorithm.RoundRobin;
import org.datastructure.ArrayQueue;
import org.datastructure.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

public class MultiLevelFeedbackQueue<T> {
    private RoundRobin roundRobin;

    private HashMap<Integer, Queue<T>> multiLevelQueues;

    private final int timeSlice;

    public MultiLevelFeedbackQueue(RoundRobin roundRobin, int queueQuantity, int queueCapacity, int timeSlice) {
        this.roundRobin = roundRobin;
        this.multiLevelQueues = new HashMap<>(queueQuantity);
        for (int i = 0; i < queueQuantity; i++) {
            multiLevelQueues.put(i, new ArrayQueue<>(queueQuantity));
        }
        this.timeSlice = timeSlice;
    }

    public void schedule() {
        // new time
        for (HashMap.Entry(Queue<T>) feedbackQueue : multiLevelQueues.entrySet()) {
            if (feedbackQueue.isEmpty()) {
                continue;
            }
            // time.start
            int time = roundRobin.run(feedbackQueue.peek());
            if (time <= timeSlice) {
                continue;
            } else {

            }
        }
    }

    public void addJob(T job) {
        multiLevelQueues.get(0).enqueue(job);
    }

    public void run(T job) {

    }
}
