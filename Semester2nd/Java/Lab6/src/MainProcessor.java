import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

/**
 * Created by alesia on 6/9/17.
 */
public class MainProcessor {
    /*Відкрити будь-який текстовий файл та поміняти місцями перше і останнє слова в
    кожному рядку.*/

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide path to file");
        //  /Users/alesia/testFile.txt - file path for test
        String filePath = scanner.nextLine();

        // 1. read all file
        ArrayList<String> lines = new ArrayList<String>();
        readFileToArray(lines, filePath);

        // 2. show file data in console
        showData(lines);

        // 3. change first last word places
        String newText = "";
        for (String line: lines) {
            newText += switchFirstLastWord(line) + "\r\n ";
        }
        System.out.println();

        // 4. write updated data to file
        writeDataToFile(newText, filePath);

        // 5. read updated data file
        lines.clear();
        readFileToArray(lines, filePath);

        // 6. show updated file data in console
        System.out.println("New data of file:");
        showData(lines);


    }

    private static void readFileToArray(ArrayList<String> lines, String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){ //don't need finally
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            if (lines.size() == 0) {
                System.out.println("Provided file is empty. System exit.");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The provided file path " + filePath + " isn't valid. Please run program once again.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataToFile(String text, String filePath) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showData(ArrayList<String> lines) {
        for (String line: lines) {
            System.out.println(line);
        }
    }

    private static String switchFirstLastWord(String line) {
        String[] words = line.split(" ");
        if (words.length > 1 /*no point to change if there is only 1 word*//*!= 0*/) {
            String firstWord = words[0];
            String lastWord = words[words.length - 1];
            //line = line.replaceFirst(firstWord, lastWord);
            line = lastWord + line.substring(line.indexOf(" ") + 1,line.length());
            line = line.substring(0, line.lastIndexOf(" ") + 1) + firstWord;
        }
        return line;
    }

}
