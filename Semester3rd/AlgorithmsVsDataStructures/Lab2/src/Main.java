import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by alesia on 9/26/17.
 * 10 - Трикутник: координати вершин, конструктор, методи обчислення
 площини, периметру, виведення об'єкта
 * 10 - Метод вирішення колізій - Відкрита адресація – лінійне зондування
 * 10 - Критерій видалення елементів - Елементи зі значенням периметра, більшого від заданого
 */
public class Main {
    public static void main (String[] args) {
        //розмір хеш-таблиці, який задається користувачем уведенням з клавіатури.
        System.out.println("Input Hash Table size");
        Integer N = null;
        boolean inputAgain = true;
        while (inputAgain) {
            try {
                Scanner scanner = new Scanner(System.in);
                N = scanner.nextInt();
                if (N <= 0) throw new InputMismatchException();
                inputAgain = false;
            } catch (InputMismatchException e) {
                System.out.println("Input value should be integer and > 0. Input again");
            }
        }

        //fill in MyHashTable with Triangles
        MyHashTable myHashTable = new MyHashTable(N);
        int checker = 0; //is used for generate not triangle once
        for (int i = 0; i < N - 2; i++) {
            try {
                if(checker == 0) {                  //check that not triangle throws error
                    checker++;
                    double p = Math.random() * 10;
                    Triangle notTriangle = new Triangle(p,p,p,p,p,p);
                }
                // generate triangle values
                double x1 = Math.random() * 10;
                double y1 = Math.random() * 10;
                double x2 = Math.random() * 10;
                double y2 = Math.random() * 10;
                double x3 = Math.random() * 10;
                double y3 = Math.random() * 10;
                Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);

                if (!myHashTable.hashInsert(triangle)) i--;
                if(checker == 1) {
                    if (!myHashTable.hashInsert(triangle)) i--; //check triangle already exists
                    System.out.println("Triangle " + triangle + " already exists.");
                    checker++;
                }

            } catch (TriangleException ex) {
                System.out.println("Not a triangle. Continue execution...");
                i--;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.println("Hash Table Data:\r\n");
        System.out.printf(myHashTable.toString() + "\r\n");

        //Критерій видалення елементів - Елементи зі значенням периметра, більшого від заданого
        System.out.println("Input perimeter value for deletion Triangles with perimeter > input perimeter");
        inputAgain = true;
        Integer perimeterRestriction = null;
        while (inputAgain) {
            try {
                Scanner scanner = new Scanner(System.in);
                perimeterRestriction = scanner.nextInt();
                if (perimeterRestriction <= 0) throw new InputMismatchException();
                inputAgain = false;
            } catch (InputMismatchException e) {
                System.out.println("Input value should be integer and > 0. Input again");
            }
        }

        while (myHashTable.hashDelete(perimeterRestriction)) {  } //delete while can be deleted

        System.out.println("Hash Table Data after deletion Triangles with perimeter > " + perimeterRestriction + "\r\n");
        System.out.printf(myHashTable.toString() + "\r\n");

    }
}
