package org.datastructure;

import java.util.ArrayList;
import java.util.List;

/*
   Theory: First in first out
   Function:
   + getFirst() => peek()
   + pickFirst() => dequeue()
   + insert() => enqueue()
   + isEmpty()
   + size()

 */
public class ArrayQueue<T> implements Queue<T> {

    private final int capacity;

    private final T[] queue;

    private int front;

    private int rear;

    private int size;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T firstData = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;

        return firstData;
    }

    @Override
    public void enqueue(T data) {
        if (size() == capacity) {
            throw new IllegalStateException("Queue has full capacity");
        }
        queue[rear] = data;
        rear = (rear + 1) % capacity;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }
}
