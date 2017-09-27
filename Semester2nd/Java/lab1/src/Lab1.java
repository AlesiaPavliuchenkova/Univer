/**
 * Created by apavliuchenkova on 06/06/2017.
 */
public class Lab1 {
    public static final int N = 5;

    public static void main(String [] args) {
        //1. Напишіть програму на мові Java, яка виводить на екран прізвище та ініціали розробника
        System.out.println("Alesia Pavliuchenkova");
        System.out.println();

        //2. виконує обробку матриці A[N][N] відповідно варіанту 11
        //2.1. Значення елементів матриці A[N][N] задаються за допомогою генератору випадкових чисел.
        float[][] A = new float[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = (float) (Math.random() * 100 + ((N + 1) / N));
            }
        }

        //2.2. вивести на екран матрицю A[N][N] до її обробки
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[i][j] + "   ");
            }
            System.out.println();
        }


        //2.3. Виконайте циклічний зсув матриці на n позицій вправо
        int n = 3;
        System.out.println("Move for " + n + " position");
        int lastIndex = N - 1;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < n; i++) {
                float temp = A[j][lastIndex];
                for (int k = lastIndex - 1; k >= 0; k--) {
                    A[j][k + 1] = A[j][k];
                }
                A[j][0] = temp;
            }
        }

        System.out.println();
        //2.4. вивести на екран матрицю A[N][N] після її обробки
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[i][j] + "   ");
            }
            System.out.println();
        }

    }
}
