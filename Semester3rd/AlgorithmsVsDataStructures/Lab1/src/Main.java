/**
 * Created by apavliuchenkova on 25/09/2017.
 * 10 - Видалити зі списку від’ємні елементи, інвертувати їх, перетворити
 * у вісімкову систему числення та перемістити у стек.
 */
public class Main {

    public static void main (String args []) {
        final int N = 13;

        //Create instances of Single Linked List and Stack Data Structures
        MyLinkedList list = new MyLinkedList();
        MyStack stack = new MyStack(N);

        // Fill in Single Linked List with values
        for (int i = 0; i < N; i++) {
            list.add(i + N / (i + 1) * N * i * (i%2 == 0 ? (-1) : 1));
        }
        System.out.println("Single Linked List after its creation: \r\n");
        System.out.println(list.toString() + "\r\n");

        //Remove negative values and save them to Stack Data Structure
        MyNode node = list.getHead();
        int counter = 0;
        try {
            //stack.pop(); check pop empty stack
            while (node != null) {
                if (node.getElement() < 0) {
                    int removedVal = list.remove(counter);
                    //System.out.println("removed = " + removedVal + "; 8 = " + convertTo8(removedVal*(-1)));
                    stack.push(convertTo8(removedVal*(-1)));
                    counter--;
                }
                counter++;
                node = node.getNext();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Single Linked List after removing negative values from it: \r\n");
        System.out.println(list + "\r\n");

        System.out.println("Stack after its creation:");
        System.out.println(stack);

        /*try {
            stack.pop();
            System.out.println(stack);
            stack.pop();
            System.out.println(stack);
            stack.pop();
            System.out.println(stack);
            stack.pop();
            System.out.println(stack);
            stack.pop();
            System.out.println(stack);
            stack.pop();
            System.out.println(stack);
            stack.pop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }

    private static String convertTo8(int val) {
        String result = "";
        int i = val;

        do {
            result = i%8 + result;
            i = i/8;

        } while (i != 0 );

        return result;
    }

}
