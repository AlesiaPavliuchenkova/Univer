import java.util.LinkedList;

/**
 * Created by apavliuchenkova on 25/09/2017.
 * 10 - Односпрямований список - Цілий
 */
public class MyLinkedList {
    private MyNode head = null;
    private MyNode last = null;

    MyLinkedList () {    }

    public MyNode getHead () {
        return this.head;
    }

    public void setHead (MyNode node) {
        this.head = node;
    }

    public void addLast (int val) {
        if (isEmpty()) {
            head = new MyNode(val);
            last = head;
        } else {
            MyNode node = new MyNode(val);
            last.setNext(node);
            last = node;
        }
    }

    public void addFirst(int val) {
        MyNode node = new MyNode(val);
        if(isEmpty()) {
            head = node;
            last = head;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    public Integer remove () throws Exception{
        if(isEmpty()) {
            throw new Exception("List is empty.");
        }
        MyNode node = head;
        MyNode previousNode;

        while(node != null) {
            previousNode = getPrevious(node);

            if(node.getElement() < 0) {
                if(previousNode != null) {
                    previousNode.setNext(node.getNext());
                } else {
                    head = node.getNext(); //head is deleted
                }
                return node.getElement();
            }
            node = node.getNext();
        }

        return null;
    }

    private MyNode getPrevious(MyNode node) {
        if(node == head) return null;

        MyNode previousNode = head;
        while (previousNode.getNext() != node) {
            previousNode = previousNode.getNext();
        }

        return previousNode;
    }

    public boolean hasNext (MyNode node) {
        return node.getNext() != null;
    }

    private boolean isEmpty () {
        return head == null;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Linked list is empty";

        String out = "";
        MyNode node = head;

        while (node != null) {
            out += node + "     ";
            node = node.getNext();
        }

        return out;
    }
}
