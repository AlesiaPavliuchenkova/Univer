package com.java.lab5;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by apavliuchenkova on 09/06/2017.
 */
public class MainProcess {
    private static int A;
    private static int B;
    private static int C;

    public static void main(String[] args) {
        try {
            HashSet<Point> points = new HashSet<Point>(); //there is no point to use hash set here!!! fix later

            String flag = "yes";
            Scanner scanner = new Scanner(System.in);

            System.out.println("Create Set of Points");
            do {
                System.out.println("Input x int value for point.");
                int x = scanner.nextInt();
                System.out.println("Input y int value for point.");
                int y = scanner.nextInt();
                Point point = new Point(x, y);

                points.add(point);

                System.out.println("Do you want to create one more point? y/n");
                flag = scanner.next();

            } while (flag.toLowerCase().equals("y"));

            // 2. Всі колекції після створення потрібно вивести.
            System.out.println("Всі колекції після створення потрібно вивести.");
            showPoints(points);
            System.out.println();

            // 3. Знайти точки, що найбільш і найменш віддалені від заданої прямої. Пряма визначається рівнянням: A*x+B*y+C=0
            System.out.println("Знайти точки, що найбільш і найменш віддалені від заданої прямої. Пряма визначається рівнянням: A*x+B*y+C=0");
            System.out.println("Input A int value:");
            A = scanner.nextInt();
            System.out.println("Input B int value:");
            B = scanner.nextInt();
            System.out.println("Input C int value:");
            C = scanner.nextInt();

            // 3.1. Найменш віддалені від заданої прямої
            System.out.println("Найменш віддалені від заданої прямої:");
            showPointMinDistanceToLine(points);
            System.out.println();

            // 3.2. Найбільш віддалені від заданої прямої
            System.out.println("Найбільш віддалені від заданої прямої:");
            showPointMaxDistanceToLine(points);
        } catch (InputMismatchException ex) {
            System.out.println("You've provided invalid data. Please try again.");
        }
    }

    private static void showPoints(HashSet<Point> points) {
        for (Point point:points) {
            System.out.println(point.toString());
        }

    }

    private static void showPointMinDistanceToLine(HashSet<Point> points) {
        Iterator<Point> iterator = points.iterator();
        if (iterator.hasNext()) {
            Point point = iterator.next();
            double minDistance = calculateDistance(point.getX(), point.getY());

        for (Point p: points) {
            if (minDistance > calculateDistance(p.getX(), p.getY())) {
                point = p;
            }
        }
        System.out.println(point.toString());
        }
    }

    private static void showPointMaxDistanceToLine(HashSet<Point> points) {
        Iterator<Point> iterator = points.iterator();
        if (iterator.hasNext()) {
            Point point = iterator.next();
            double maxDistance = calculateDistance(point.getX(), point.getY());

            for (Point p: points) {
                if (maxDistance < calculateDistance(p.getX(), p.getY())) {
                    point = p;
                }
            }
            System.out.println(point.toString());
        }
    }

    private static double calculateDistance(int x, int y){
        double d = (Math.abs(A*x + B*y + C) / (Math.sqrt((Math.pow(A,2) + Math.pow(B,2)))));
        return d;
    }


}
