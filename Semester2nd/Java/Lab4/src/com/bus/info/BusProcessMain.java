package com.bus.info;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by apavliuchenkova on 08/06/2017.
 */
public class BusProcessMain {
    private static final int N = 2;
    public static ArrayList<Bus> buses = new ArrayList<Bus>();

    public static void main(String[] args) throws Exception {
        /* 1. Автобус: Прізвище та Ім’я водія, Номер
                       автобуса, Номер маршруту, Марка,
                       Рік початку експлуатації, Пробіг */
        try {

            Field[] fields = Bus.class.getDeclaredFields();
            Scanner scanner = new Scanner(System.in);
            System.out.println("You want to create buses manual - 1; auto - 2");
            String  option = scanner.next();
            switch (option) {
                case "1": {
                    for (int i = 0; i < N; i++) {
                        Bus bus = new Bus();
                        manualBusCreation(bus, fields);
                        buses.add(bus);
                    }
                }
                break;
                case "2":
                    generateBusAutomatically();
                    break;
                default:
                    System.out.println("You've provided invalid option. System exit.");
                    System.exit(1);
            }
            showBusesInfo(buses);
            System.out.println();

            // 2. Отримати список автобусів для заданого номера маршруту.
            ArrayList<Bus> busesFiltered = new ArrayList<Bus>();
            System.out.println("Please input route number for search:");
            boolean flag = false;
            int routeNumber = 0;
            do {
                try {
                    routeNumber = scanner.nextInt();
                    flag = false;
                } catch (InputMismatchException exception) {
                    System.out.println("Integers only, please.");
                    scanner.nextLine();
                    flag = true;
                }

            } while (flag);

            getBusFilteredByRouteNumber(routeNumber, buses, busesFiltered);
            System.out.println("Отримати список автобусів для заданого номера маршруту.");
            showBusesInfo(busesFiltered);
            busesFiltered.clear();
            System.out.println();

            // 3. Отримати список автобусів, які знаходяться в експлуатації більше 10 років.
            System.out.println("Отримати список автобусів, які знаходяться в експлуатації більше 10 років.");
            getBusFilteredByYears(buses, busesFiltered);
            showBusesInfo(busesFiltered);
            busesFiltered.clear();
            System.out.println();

            // 4. Отримати список автобусів, пробіг яких  більше 100000 км.
            System.out.println("Отримати список автобусів, пробіг яких  більше 100000 км.");
            getBusFilteredByMileage(buses, busesFiltered);
            showBusesInfo(busesFiltered);
        } catch (InputMismatchException ex) {
            System.out.println("You've provided invalid data. System exit.");
        }
    }

    private static void showBusesInfo(ArrayList<Bus> buses) {
        if (buses.size() == 0) {
            System.out.println("There are no buses to show!");
        } else {
            String format = "%-35s%-15s%-15s%-15s%-10s%-20s%n";
            System.out.printf(format,"firstLastDriverName","busNumber","routeNumber","busModel","year","mileage");
            for (/*Bus bus : buses*/int i = 0; i < buses.size(); i++) {
                //System.out.println(buses.get(i).toString());
                System.out.printf(format,buses.get(i).getFirstLastDriverName()
                        ,buses.get(i).getBusNumber(),buses.get(i).getRouteNumber()
                        ,buses.get(i).getBusModel(),buses.get(i).getWorksSinceYear()
                        ,buses.get(i).getMileage());
            }
        }
    }

    private static ArrayList<Bus> getBusFilteredByRouteNumber(int routeNumber, ArrayList<Bus> buses, ArrayList<Bus> busesFltr) {
        for (Bus bus: buses) {
            if(bus.getRouteNumber() == routeNumber) {
                busesFltr.add(bus);
            }
        }
        return buses;
    }

    private static ArrayList<Bus> getBusFilteredByYears(ArrayList<Bus> buses, ArrayList<Bus> busesFltr) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (Bus bus: buses) {
            if((currentYear - bus.getWorksSinceYear()) > 10) {
                busesFltr.add(bus);
            }
        }
        return buses;
    }

    private static ArrayList<Bus> getBusFilteredByMileage(ArrayList<Bus> buses, ArrayList<Bus> busesFltr) {
        for (Bus bus: buses) {
            if(bus.getMileage() > 100000) {
                busesFltr.add(bus);
            }
        }
        return buses;
    }

    public static void manualBusCreation(Bus bus, Field[] fields) throws Exception {
        Scanner scanner = new Scanner(System.in);
        for (int j = 0; j < fields.length; j++) {
            try {
                String fullFieldName = fields[j].toString();
                String fieldName = fullFieldName.substring(fullFieldName.lastIndexOf('.') + 1);

                System.out.println("Input data for " + fieldName + " property:");
                String fieldValue = scanner.nextLine();

                bus.setFieldValue(fieldName, fieldValue);
            } catch (BusException ex) {
                System.out.println(ex.getMessage() + " Please input valid data.");
                j--;
            }
        }
    }

    public static void generateBusAutomatically() {

        String filePath = "/Users/alesia/IdeaProjects/Lab4/src/Buses.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { //don't need finally
            String line;
            while ((line = br.readLine()) != null) {
                String[] busInfo = line.split(";");
                buses.add(new Bus(busInfo[0]
                                    ,Integer.parseInt(busInfo[1])
                                    ,Integer.parseInt(busInfo[2])
                                    ,busInfo[3]
                                    ,Integer.parseInt(busInfo[4])
                                    ,Integer.parseInt(busInfo[5])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("The provided file path " + filePath + " isn't valid. Please run program once again.");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("IOException");

        }
    }

}
