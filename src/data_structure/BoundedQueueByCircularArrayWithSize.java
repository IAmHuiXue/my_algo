package data_structure;

public class BoundedQueueByCircularArrayWithSize {
    // implement a queue by a circular array
    // for circular array, index of array.length <=> index of 0
    // need to maintain two pointers and initially point to the same position
    // when enqueue, tail++ (points to the next available position !)
    // when dequeue, head++ (points to the top element)
    // how to find out the next available position? the trick is -->
    // --> head = head + 1 == array.length ? 0 : head + 1
    // how to determine if the queue is full?
    // when size == array.length

    private final Integer[] array;
    private int head; // points to the top element
    private int tail; // points to the next available position
    private int size;

    public BoundedQueueByCircularArrayWithSize() {
        array = new Integer[10];
        head = tail = 0;
        size = 0;
    }

    public BoundedQueueByCircularArrayWithSize(int cap) {
        // check cap
        array = new Integer[cap];
        head = tail = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean enqueue(Integer element) {
        if (size == array.length) {
            return false;
        }
        array[tail] = element;
        tail = tail + 1 == array.length ? 0 : tail + 1;
        size++;
        return true;
    }

    public Integer dequeue() {
        if (size == 0) {
            return null;
        }
        Integer result = array[head];
        // (head + 1) % array.length
        head = head + 1 == array.length ? 0 : head + 1;
        size--;
        return result;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return array[head];
    }

    public Integer peekRear() {
        if (size == 0) {
            return null;
        }
        int rear = tail - 1 == -1 ? array.length - 1 : tail - 1; // !
        return array[rear];
    }
}
