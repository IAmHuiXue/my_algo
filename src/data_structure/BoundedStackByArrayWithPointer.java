package data_structure;

public class BoundedStackByArrayWithPointer {
    // implement a stack by an array
    private final int[] array;
    private int head; // one pointer can represent size
    // -1 --> empty stack
    // array.length - 1 --> full stack

    public BoundedStackByArrayWithPointer(int cap) {
        // check cap
        array = new int[cap];
        head = -1;
    }

    public boolean push(int ele) {
        if (head == array.length - 1) {
            return false;
        }
        array[++head] = ele;
        return true;
    }

    public Integer pop() {
        return head == -1 ? null : array[head--];
    }

    public Integer top() {
        return head == -1 ? null : array[head];
    }
}
