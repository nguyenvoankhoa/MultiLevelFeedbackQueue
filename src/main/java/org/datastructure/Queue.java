package org.datastructure;

public interface Queue<T> {
    T peek();

    T dequeue();

    void enqueue(T data);

    boolean isEmpty();

    int size();
}
