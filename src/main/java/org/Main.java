package org;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

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
    public static void main(String[] args) {

    }
}