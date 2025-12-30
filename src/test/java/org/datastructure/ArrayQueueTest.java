package org.datastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    private ArrayQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue<>(3);
    }

    @Test
    void newQueue_shouldBeEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void enqueue_shouldAddElements() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
    }

    @Test
    void dequeue_shouldFollowFifoOrder() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void peek_shouldReturnFirstElementWithoutRemoving() {
        queue.enqueue(10);
        queue.enqueue(20);

        assertEquals(10, queue.peek());
        assertEquals(2, queue.size()); // size unchanged
    }

    @Test
    void dequeue_onEmptyQueue_shouldThrowException() {
        IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> queue.dequeue());

        assertEquals("Queue is empty", exception.getMessage());
    }

    @Test
    void peek_onEmptyQueue_shouldThrowException() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

    @Test
    void enqueue_onFullQueue_shouldThrowException() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> queue.enqueue(4));

        assertEquals("Queue has full capacity", exception.getMessage());
    }

    @Test
    void circularBehavior_shouldWorkCorrectly() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());

        // wrap around
        queue.enqueue(4);
        queue.enqueue(5);

        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void size_shouldBeUpdatedCorrectly() {
        assertEquals(0, queue.size());

        queue.enqueue(1);
        assertEquals(1, queue.size());

        queue.enqueue(2);
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());
    }
}
