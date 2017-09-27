import java.util.Arrays;

/**
 * Created by apavliuchenkova on 25/09/2017.
 */
public class MyStack {
    private String [] elements;
    private int count;
    private int top = -1;

    MyStack (int count) {
        this.elements = new String[count];
    }

    public boolean push (String el) throws Exception{
        if(isFull()) {
            throw new Exception("Stack is full.");
        }
        elements[count] = el;
        top++;
        count++;
        return true;
    }


    public String pop () throws Exception{
        if(isEmpty()) {
            throw new Exception("Stack is empty.");
        }
        String removedElement = elements[top];
        elements[count - 1] = null;
        count--;
        top--;
        return removedElement;
    }


    private boolean isFull () {
        return elements.length == count;
    }

    private boolean isEmpty () {
        return count == 0;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "elements=" + Arrays.toString(elements) +
                ", count=" + count +
                ", top=" + top +
                '}';
    }
}
