/**
 * Created by Derek on 3/21/2017.
 */
public class Node {
    private Object data;
    private Node next;
    private Node prev;

    public Node(Object obj) {
        this.data = obj;
        this.next = null;
        this.prev = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public Object getData() {
        return this.data;
    }
}
