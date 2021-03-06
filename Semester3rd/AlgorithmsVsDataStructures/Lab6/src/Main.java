import java.sql.Array;

/**
 * Created by alesia on 9/30/17.
 * 10 - Мета – дослідження методів аналізу ефективності алгоритмів
 * та набуття практичних навичок з емпіричного дослідження швид-
 * кодії алгоритмів залежно від обсягу та структурованості вхідних
 * даних.
 */

public class Main {

    public static void main(String[] args) {
        // реалізувати два алгоритми для одного набору - Одновимірний масив
        // отримати час виконання алгоритмів для наборів даних
        // розміром N, N^2, N^3, де N = 100;

        // 1. розміром N
        int N1 = 100;
        int[] array = generateArray(N1);
        //for (int i: array){ System.out.print(i + "  "); };
        calculateSortMethodsTime(array);

        // 2. розміром N^2
        int N2 = (int)Math.pow(N1,2);
        array = generateArray(N2);
        calculateSortMethodsTime(array);

        // 3. розміром N^3
        int N3 = (int) Math.pow(N1,3);
        array = generateArray(N3);
        calculateSortMethodsTime(array);

        // побудувати графіки залежностей часу виконання від кіль-
        // кості елементів набору даних для двох реалізацій алгоритму
    }

    public static void calculateSortMethodsTime(int[] array) {
        System.out.println("N = " + array.length);


        long startNanoTime = System.nanoTime(); //nanoTime() is not a clock but CPU cycle counter.
        int[] arrayShell = array.clone();
        sortShell(arrayShell);
        long executionTime = System.nanoTime() - startNanoTime;
        System.out.println("Shell sort:     " + executionTime);

        startNanoTime = System.nanoTime();
        sortInsertion(array);
        executionTime = System.nanoTime() - startNanoTime;
        System.out.println("Insertion sort: " + executionTime);
        System.out.println();
    }

    // generate Array
    public static int[] generateArray (int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random()*100);
        }
        return array;
    }

    // 1.1. Сортування – Шелла (за Кнутом)
    public static void sortShell (int[] array) {
        int length = array.length;
        int h = 1;
        // каждое последующее значение на единицу больше, чем утроенное предыдущее значение
        while( h < length / 3) {
            h = 3 * h + 1;
        }

        while(h > 0) {
            hSort(array, h);
            h = h/3;
        }
    }

    private static void hSort(int[] array, int h) {
        int length = array.length;
        //System.out.println(h);
        for(int i = h; i < length; i++) {
            for(int j = i; j >= h; j = j - h) {
                /*if (array.length == 100 ) {
                    System.out.print(j+"  ");
                }*/
                if (array[j] < array[j - h]) {
                    int temp = array[j];
                    array[j] = array[j - h];
                    array[j - h] = temp;
                } else { break; }
            }
        }
    }

    // 1.2. Сортування – вставкою
    public static void sortInsertion (int[] array) {
        for(int i = 1; i < array.length; i++){
            int key = array[i];
            int indexFree = i;

            while(indexFree > 0 && key < array[indexFree - 1]) {
                array[indexFree] = array[indexFree - 1];
                indexFree--;
            }
            array[indexFree] = key;
        }
    }
}
