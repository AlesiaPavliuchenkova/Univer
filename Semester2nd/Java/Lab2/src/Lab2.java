import java.util.Scanner;

/**
 * Created by apavliuchenkova on 08/06/2017.
 */
public class Lab2 {
    // вивчити можливості мови Java для обробки текстових типів даних.
    public static void main(String[] args) {
        /* 1. Напишіть програму на мові Java, яка задає довільний текст, що складається з
        декількох речень і рядків, та виконує його зміну (обробку) відповідно варіанту завдання.*/
        Scanner scanner = new Scanner(System.in);
        String str = "";
        System.out.println("Hello! Input some text. If you've finished to input text write \"exit\"");
        while(scanner.hasNextLine()) {
            String txt = scanner.nextLine();
            if(txt.equals("exit")) break;
            str += txt + "\r\n ";
        }

        System.out.println("String before changes:");
        System.out.println(str);

        System.out.println("Input position of symbol that should be changed.");
        int k = scanner.nextInt();

        System.out.println("Input new symbol for replacement.");
        char symbol = scanner.next().charAt(0);
        scanner.close();

        System.out.println("Change symbol in word on position " + k + " with symbol '" + symbol + "'.");
        System.out.println();

        /* 2. Змініть текст наступним чином: в кожному слові тексту замінити букву на k-ій
        позиції заданим символом. Якщо довжина слова менше k, то зміну не проводити */
        String [] strArr = str.split(" "); //add for symbols //[ .,?!'"();:]

        for (int i = 0; i < strArr.length; i++) {
            StringBuilder word = new StringBuilder(strArr[i]);
            if ( word.length() >= k) {
                word.setCharAt(k - 1, symbol);
            }
            str = str.replace(strArr[i],word.toString()); //replaceAll
        }

        System.out.println("String after changes:");
        System.out.println(str);
    }

}
