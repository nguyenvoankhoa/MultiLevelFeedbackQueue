package org.scheduler;

import org.algorithm.RoundRobin;
import org.datastructure.ArrayQueue;
import org.datastructure.Job;
import org.datastructure.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    Theory: Multi-level Feedback Queue
    - Highest priority queue run first, same priority run by Round-Robin
    Rule 1: Priority(A) > Priority(B) => A runs
    Rule 2: Priority(A) = Priority(B) => A & B runs Round-Robin
    Rule 3: New job enters => placed at the highest priority queue
    Rule 4a: The job does not end in time slice it runs => reduce priority
    Rule 4b: The job finishes in the time slice => remain priority
    Rule 5: After period of time S, move all jobs to the highest priority queue
    Additional: The job runs in one queue for n times => reduce priority
*/
public class MultiLevelFeedbackQueue {

    private static final int jobAmountThreshHold = 5;

    private static final int S = 36000;

    private final List<Queue<Job>> multiLevelQueues;

    private final HashMap<Job, Integer> jobRunAmountTimes;

    private final int timeSlice;

    public MultiLevelFeedbackQueue(int queueQuantity, int queueCapacity, int timeSlice) {
        this.multiLevelQueues = new ArrayList<>();
        for (int i = 0; i < queueCapacity; i++) {
            multiLevelQueues.add(i, new ArrayQueue<>(queueQuantity));
        }
        this.timeSlice = timeSlice;
        jobRunAmountTimes = new HashMap<>();
    }

    public void schedule() {
        int currentTime = 0;
        do {
            for (int i = 0; i < multiLevelQueues.size(); i++) {
                Queue<Job> feedbackQueue = multiLevelQueues.get(i);
                if (feedbackQueue.isEmpty()) {
                    continue;
                }
                Job job = feedbackQueue.dequeue();

                // Rule 2
                RoundRobin roundRobin = new RoundRobin(feedbackQueue);
                HashMap<Job, Integer> runtimeMap = roundRobin.run();
                int runTime = runtimeMap.get(job);
                currentTime += runTime;
                jobRunAmountTimes.put(job, jobRunAmountTimes.getOrDefault(job, 0) + 1);

                // Rule 4a
                if (runTime >= timeSlice) {
                    if (i < feedbackQueue.size() - 1) {
                        multiLevelQueues.get(i + 1).enqueue(job);
                    }
                } else if (jobRunAmountTimes.get(job) > jobAmountThreshHold) {
                    if (i < feedbackQueue.size() - 1) {
                        multiLevelQueues.get(i + 1).enqueue(job);
                    }
                } else {
                    feedbackQueue.enqueue(job);
                }
                break;
            }
            if (currentTime >= S) {
                List<Job> jobs = new ArrayList<>();
                multiLevelQueues.forEach(q -> {
                    while (!q.isEmpty()) {
                        jobs.add(q.dequeue());
                    }
                });

                for (Job job : jobs) {
                    multiLevelQueues.get(0).enqueue(job);
                }

                currentTime = 0;
            }

        } while (multiLevelQueues.stream().anyMatch(q -> !q.isEmpty()));

    }

    public void addJob(Job job) {
        multiLevelQueues.get(0).enqueue(job);
    }

}
