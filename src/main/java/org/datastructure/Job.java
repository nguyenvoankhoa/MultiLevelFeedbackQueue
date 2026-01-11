package org.datastructure;

import java.util.Random;

public class Job {
    private int runTime;

    public Job() {
        this.runTime = new Random(10).nextInt() + 1;
    }

    public boolean isFinish() {
        return this.runTime == 0;
    }

    public int run(int quantum) {
        int executed = Math.min(quantum, runTime);
        runTime -= executed;
        return executed;
    }
}
