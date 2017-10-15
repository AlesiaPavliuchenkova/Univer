import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by alesia on 9/29/17.
 * 10 - дослідження алгоритмів сортування та набуття прак-
 * тичних навичок із сортування лінійних структур даних.
 */
public class Main {

    public static void main(String[] args) {

        // Cтворити та ініціювати екземпляр лінійної структури даних
        Student [] students = generateStudents();

        // вивести вміст одновимірного масиву перед сортуванням;
        System.out.println("Students before sort:\r\n");
        printStudents(students);

        // сортувати одновимірний масив;
        sortStudentsInsertion(students);

        // вивести вміст одновимірного масиву після сортування.
        System.out.println("\r\nStudents after sort (За зростанням співвідношення пропущених і занять за планом):\r\n");
        printStudents(students);
    }

    /* Oписати метод, який сортує одновимірний масив студентів
    за заданим алгоритмом у заданому порядку сортування згідно з варіа-
    нтом завдання - Вставкою - За зростанням співвідношення пропущених і
    занять за планом */

    public static void sortStudentsInsertion(Student[] students) {
        for (int i = 1; i < students.length; i++) {
            Student key = students[i];
            int freeIndex = i;

            while (freeIndex > 0 &&
                    key.calculateMissedLessonPercent() < students[freeIndex-1].calculateMissedLessonPercent()) {

                students[freeIndex] = students[freeIndex-1];
                freeIndex--;
            }
            students[freeIndex] = key;
         }
    }

    public static void printStudents(Student[] students) {
        for (Student student: students) {
            System.out.println(student);
        }
    }

    public static Student[] generateStudents(){
        Student[] students;
        ArrayList<Student> studentsList = new ArrayList<Student>();
        String filePath = "src/students.json";

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new BufferedReader(new FileReader(filePath)));

            for (Object o: jsonArray) {
                JSONObject object = (JSONObject) o;

                studentsList.add(
                        new Student(
                                object.get("firstName").toString(),
                                Integer.parseInt(object.get("lessonCount").toString()),
                                Integer.parseInt(object.get("lessonMissedCount").toString())));

            }
        } catch (FileNotFoundException ex) {
            System.out.println("There is no file with provided path");
            System.exit(1);
        } catch (ParseException ex) {
            System.out.println("Provided file can not be parsed to JSONArray");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        students = new Student[studentsList.size()];
        studentsList.toArray(students);
        return students;

    }
}
