package org.algorithm;

import org.datastructure.Job;
import org.datastructure.Queue;

import java.util.HashMap;

/*
    Theory: Round-Robin
    Run job in quantum time, if the job done -> Termination
                                        not done -> Put to queue
 */

public class RoundRobin {

    private final Queue<Job> roundRobinQueue;

    private static final int QUANTUM = 2;

    public RoundRobin(Queue<Job> roundRobinQueue) {
        this.roundRobinQueue = roundRobinQueue;
    }

    public HashMap<Job, Integer> run() {
        HashMap<Job, Integer> execTimeMap = new HashMap<>();
        while (!roundRobinQueue.isEmpty()) {
            Job job = roundRobinQueue.dequeue();
            int execTime = job.run(QUANTUM);
            execTimeMap.putIfAbsent(job, execTimeMap.getOrDefault(job, 0) + execTime);

            if (!job.isFinish()) {
                roundRobinQueue.enqueue(job);
            }
        }
        return execTimeMap;
    }
}
