import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alesia on 9/29/17.
 * 10 - дослідження алгоритмів пошуку та набуття практич-
 них навичок з пошуку елементів у лінійних та нелінійних структу-
 рах даних.
 * 10 - Алгоритм пошуку - Послідовний
 */

public class Main {
    public static void main(String[] args) {

        // 1. створити та ініціювати екземпляр лінійної структури даних
        //(одновимірний масив студентів розміром не менш 20 елементів) з
        //урахуванням правила формування - Невпорядкований
        Student[] students = generateStudents();

        // 2. вивести вміст одновимірного масиву;
        printStudents(students);

        //3. Видалити студентів-чоловіків 6-го курсу, що проживають у гуртожитку
        int course = 6;
        String gender = "male";
        boolean isFromDormitory = true;

        while (true) {
             Student studentDelete = linearSearchStudent(students, course, gender, isFromDormitory);

            if(studentDelete == null) { break; }
            int indexDelete = Arrays.asList(students).indexOf(studentDelete);
            students[indexDelete] = null;
        }

        //4. вивести вміст одновимірного масиву в разі його зміни
        System.out.println("\r\nStudents list after changes (Видалити студентів-чоловіків 6-го курсу" +
                ", що проживають у гуртожитку):\r\n");
        printStudents(students);
    }

    /*описати метод, який виконує пошук в одновимірному масиві
    студентів за заданим алгоритмом згідно з варіантом завдання - Послідовний.
    Метод пошуку повертає посилання на об’єкт, що відповідає
    критерію пошуку, або значення null у разі неуспішного пошуку.*/
    public static Student linearSearchStudent(Student[] students,
                                        int course,
                                        String gender,
                                        boolean isFromDormitory) {
        for (int i = 0; i < students.length; i++) {
            if(students[i] == null) continue;
            if (students[i].getCourse() == course &&
                    students[i].getGender().equals(gender) &&
                    students[i].isFromDormitory() == isFromDormitory) {
                return students[i];
            }
        }
        return null;
    }

    /*print students*/
    public static void printStudents(Student[] students){
        int i = 1;
        for(Student student: students) {
            System.out.println(String.format("%1$-5d %2$-5s", i++, student));
        }
    }

    /*generate students*/
    public static Student[] generateStudents(){
        Student[] students;
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        String filePath = "/Users/alesia/IdeaProjects/Univer/Semester3rd/AlgorithmsVsDataStructures/Lab5/src/main/resources/students.json";

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

            for(Object o: jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                studentArrayList.add(
                        new Student(
                                jsonObject.get("lastName").toString(),
                                jsonObject.get("firstName").toString(),
                                Integer.parseInt(jsonObject.get("course").toString()),
                                jsonObject.get("gender").toString(),
                                Boolean.valueOf(jsonObject.get("fromDormitory").toString())));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("There is no file with provided name.");
            System.exit(1);
        } catch (ParseException ex) {
            System.out.println("Provided file can not be parsed.");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        students = new Student[studentArrayList.size()];
        studentArrayList.toArray(students);

        return students;
    }
}
