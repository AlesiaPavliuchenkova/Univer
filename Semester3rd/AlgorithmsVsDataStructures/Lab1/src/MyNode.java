/**
 * Created by apavliuchenkova on 25/09/2017.
 * 10 - Стек - Строковий (представляє цілі додатні числа у вісімковій системі числення)
 */


public class MyNode {
    private int element;
    private MyNode next;

    public MyNode () {

    }

    public MyNode (int obj) {
        this.element = obj;
        this.next = null;
    }

    public int getElement () {
        return element;
    }

    public MyNode getNext () {
        return next;
    }

    public void setElement (int element) {
        this.element = element;
    }

    public void setNext (MyNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "element=" + element +
                "\r\n, next=" + next +
                '}';
    }
}
