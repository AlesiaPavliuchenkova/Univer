package com.bus.homework;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by apavliuchenkova on 13/06/2017.
 */
public class BusProcessor {

    private static String filePath;
    private static File file;
    private static final Object lock = new Object();
    private static boolean isLocked = false;
    private static boolean isLastElement = false;

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide file path:");
        filePath = scanner.nextLine();
        file = new File(filePath);

        if(file.exists()) {
            System.out.println("Provided file is already exists. Are you sure you want to overwrite it content? y/n");
            String continueExecution = scanner.nextLine();
            if(continueExecution.equals("n")) {
                System.out.println("You've stopped execution. System exit");
                System.exit(1);
            } else {
                file.delete();
            }
        }

        // 1) описує потік для запису об’єктів класу згідно з варіантом завдання до деякого файлу;
        Thread threadWriteToFile = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        // 1.1. Create buses
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("You want to create buses manual - 1; auto - 2");
                        String option = scanner.next();
                        switch (option) {
                            case "1": manualBusCreation();
                                break;
                            case "2":generateBusAutomatically();
                                break;
                            default:
                                System.out.println("You've provided invalid option. System exit.");
                                System.exit(1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        });

        // 2) описує потік для читання з файлу об’єктів та виведення їх у табличному вигляді;
        Thread threadReadFromFile = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        readBusInfo();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        });

        // 3) створює декілька потоків для читання та запису даних;
        threadWriteToFile.start();
        threadReadFromFile.start();
    }

    /*generateBusAutomatically*/
    private static void generateBusAutomatically() throws InterruptedException {
        String fileGeneratorPath = "BusesForAutoGeneration.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileGeneratorPath))) { //don't need finally
            String line;
            ArrayList<Bus> buses = new ArrayList<Bus>();
            while ((line = br.readLine()) != null) {
                String[] busInfo = line.split(";");
                buses.add(new Bus(busInfo[0]
                        ,Integer.parseInt(busInfo[1])
                        ,Integer.parseInt(busInfo[2])
                        ,busInfo[3]
                        ,Integer.parseInt(busInfo[4])
                        ,Integer.parseInt(busInfo[5])));
            }

            for (int i = 0; i < buses.size(); i++) {
                isLastElement = (i == buses.size() - 1);
                writeBusInfo(buses.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("The provided file path " + fileGeneratorPath + " isn't valid. Please run program once again.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*manualBusCreation*/
    private static void manualBusCreation() throws Exception {
        Field[] fields = Bus.class.getDeclaredFields();
        Scanner sc = new Scanner(System.in);
        System.out.println("How many buses you want to create?");
        int n = Integer.parseInt(sc.nextLine());
        Bus bus = new Bus();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < fields.length; j++) {
                try {
                    String fullFieldName = fields[j].toString();
                    String fieldName = fullFieldName.substring(fullFieldName.lastIndexOf('.') + 1);

                    System.out.println("Input data for " + fieldName + " property:");
                    String fieldValue = sc.nextLine();

                    bus.setFieldValue(fieldName, fieldValue);
                } catch (BusException ex) {
                    System.out.println(ex.getMessage() + " Please input valid data.");
                    j--;
                }
            }
            isLastElement = (i == n - 1);
            writeBusInfo(bus);
        }
    }

    private static void writeBusInfo(Bus bus) throws InterruptedException {
        synchronized (lock) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                 AppendingObjectOutputStream out = new AppendingObjectOutputStream(fileOutputStream);) { // 2 write - java.io.StreamCorruptedException: invalid type code: AC
                isLocked = (!isLocked) ? true : isLocked;
                System.out.println("Thread 1: Write bus to file ... ");
                Thread.sleep(500);
                out.writeObject(bus);
                lock.notify();
                if(isLastElement) isLocked = false; //set to false when all Objects was written to file
                if(isLocked)lock.wait();
            } catch (FileNotFoundException e) {
                System.out.println("File with path " + filePath + " wasn't found. System exit");
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readBusInfo() throws InterruptedException {
        synchronized (lock) {
            Bus bus = null;
            if(!isLocked)lock.wait();
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 AppendingObjectInputStream in = new AppendingObjectInputStream(fileInputStream)) {
                while ((bus = (Bus) in.readObject()) != null) {
                    System.out.println("Thread 2: Read bus from file ...");
                    showBusInfo(bus);
                    Thread.sleep(500);
                    lock.notify();
                    if (isLocked) lock.wait();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File with path " + file + " wasn't found. System exit");
                System.exit(1);
            } catch (EOFException e) {
                System.out.println("No more rows to read from file ... ");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println(bus.getClass() + " not found. System exit.");
                System.exit(1);
            }
        }
    }

    /*showBusInfo*/
    private static void showBusInfo(Bus bus) {
        String format = "%-35s%-15s%-15s%-15s%-10s%-20s%n";
        //System.out.printf(format,"firstLastDriverName","busNumber","routeNumber","busModel","year","mileage");
        System.out.printf(format,bus.getFirstLastDriverName(),bus.getBusNumber(),bus.getRouteNumber()
                ,bus.getBusModel(),bus.getWorksSinceYear(),bus.getMileage());

    }

}
