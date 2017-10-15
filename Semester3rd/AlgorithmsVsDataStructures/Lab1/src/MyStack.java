import java.util.Arrays;

/**
 * Created by apavliuchenkova on 25/09/2017.
 */
public class MyStack {
    private String [] elements;
    private int top = -1;

    MyStack (int count) {
        this.elements = new String[count];
    }

    public boolean push (String el) throws Exception{
        if(isFull()) {
            throw new Exception("Stack is full.");
        }
        top++;
        elements[top] = el;
        return true;
    }


    public String pop () throws Exception{
        if(isEmpty()) {
            throw new Exception("Stack is empty.");
        }
        String removedElement = elements[top];
        elements[top] = null;
        top--;
        return removedElement;
    }


    private boolean isFull () {
        return elements.length == top - 1;
    }

    private boolean isEmpty () {
        return top == -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Stack is empty";
        String out = "";
        int index = top;

        while (index >= 0) {
            out += elements[index] + "    ";
            index--;
        }
        return out;
    }
}
