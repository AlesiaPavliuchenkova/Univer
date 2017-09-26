import java.util.LinkedList;

/**
 * Created by apavliuchenkova on 25/09/2017.
 * 10 - Односпрямований список - Цілий
 */
public class MyLinkedList {
    private MyNode head = null;
    private MyNode last = null;

    MyLinkedList () {

    }

    public MyNode getHead () {
        return this.head;
    }

    public void setHead (MyNode node) {
        this.head = node;
    }

    public void add (int val) {
        if (isEmpty()) {
            setHead(new MyNode(val));
            last = head;
        } else {
            if (head.getNext() == null) {
                last = new MyNode(val);
                head.setNext(last);
                return;
            }
            MyNode node = new MyNode(val);
            last.setNext(node);
            last = node;
        }
    }

    public int remove (int index) throws Exception{
        if(isEmpty()) {
            throw new Exception("List is empty.");
        }

        if ((index != 0 && !hasNext(head)) || index < 0) {
            throw new Exception("There is no element with index " + index + ".");
        }

        Integer deletedVal = null;
        MyLinkedList list = new MyLinkedList();
        int counter = 0;

        while(head != null) {
            if (counter != index) {
                list.add(head.getElement());
            } else {
                deletedVal = head.getElement();
            }
            counter++;
            head = head.getNext();
        }

        if(deletedVal == null) throw new Exception("There is no element with index " + index + ".");
        head = list.head;
        return (int) deletedVal;
    }

    public boolean hasNext (MyNode node) {
        return node.getNext() != null;
    }

    private boolean isEmpty () {
        return head == null;
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "current=" + head +
                '}';
    }
}
