/**
 * Created by Derek on 3/21/2017.
 */
public class MyQueue {

    Node head;
    Object monitor;
    int count = 0;

    public MyQueue() {
        head = null;
        monitor = null;
    }

    public void add(Object input) {
        Node newNode = new Node(input);
        if (count == 0) {
            this.head = newNode;
            count++;
            return;
        }
        Node last = this.head;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        last.setNext(newNode);
        count++;
        return;
    }

    public Node remove() {
        if (count == 0) {
            return null;
        }
        Node prev = this.head;
        this.head = this.head.getNext();
        count--;
        return prev;
    }

    public Node peek() {
        return this.head;
    }

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return count;
    }
}
