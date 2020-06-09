package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    private int moveIndex(int i) {
        if (i < capacity - 1) {
            i += 1;
        } else {
            i = 0;
        }
        return i;
    }

    private void checkFull() {
        if (this.capacity == this.fillCount) {
            throw new IllegalArgumentException("Ring Buffer Overflow");
        }
    }
    public void enqueue(T x) {
        checkFull();
        rb[last] = x;
        fillCount += 1;
        last = moveIndex(last);
//        if (last < capacity - 1) {
//            last += 1;
//        }else {
//            last = 0;
//        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    private void checkEmpty() {
        if (fillCount == 0) {
            throw new IllegalArgumentException("Ring Buffer Underflow");
        }
    }
    public T dequeue() {
        checkEmpty();
        T dequeueItem = rb[first];
//        if (first < capacity - 1) {
//            first += 1;
//        }else {
//            first = 0;
//        }
        first = moveIndex(first);
        fillCount -= 1;
        return dequeueItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        checkEmpty();
        T peekItem = rb[first];
        return peekItem;
    }

    public Iterator<T> iterator() {
        return new BoundedQueueIterater();
    }

    private class BoundedQueueIterater implements Iterator<T> {
        private int count = fillCount, first = this.first;
        public boolean hasNext() {
            if (count > 0) {
                return true;
            }
            return false;
        }

        public T next() {
            T item = rb[first];
            count -= 1;
            first = moveIndex(first);
            return item;
        }
    }
}
