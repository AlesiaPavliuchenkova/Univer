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
            int val = i + N / (i + 1) * N * i * (i%2 == 0 ? (-1) : 1);
            //System.out.print(val + "  ");
            //list.addLast(val);
            list.addFirst(val);
        }
        System.out.println("\r\nSingle Linked List after its creation: \r\n");
        System.out.println(list.toString() + "\r\n");

        //Remove negative values and save them to Stack Data Structure
        Integer removedVal;
        try {
            while ((removedVal = list.remove()) != null) {
                System.out.println("removed value inverted = " + convertTo8(removedVal*(-1)));
                stack.push(convertTo8(removedVal*(-1)));  //Integer.toOctalString(removedVal*(-1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Single Linked List after removing negative values from it: \r\n");
        System.out.println(list + "\r\n");

        System.out.println("Stack after its creation:");
        System.out.println(stack);

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
