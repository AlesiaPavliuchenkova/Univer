package com.railway;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alesia on 6/10/17.
 */
public class Railway {
    static ArrayList<Train> trains = new ArrayList<Train>(); //train info should be unique

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Passenger comes to railway to make an order

            System.out.println("Hi, Passenger! What is your name?");
            String passengerName = scanner.nextLine();
            Passenger passenger = new Passenger(passengerName);

            // 2. Passenger initiates an order
            System.out.println("Hello, " + passenger.getName() + ". Please provide destination name.");
            String destinationName = scanner.nextLine();
            Destination destination = new Destination(destinationName);
            Order order = passenger.getOrder();
            order.setPassenger(passenger);
            order.setDestination(destination);
            String dateFormat = "dd/MM/yyyy";
            Date travelDate = null;
            boolean flag = true;
            while (flag) {
                try {
                    System.out.println("Provide Travel Date in format " + dateFormat);
                    travelDate = new SimpleDateFormat(dateFormat).parse(scanner.nextLine());
                    order.setTravelDate(travelDate);
                    flag = false;
                } catch (ParseException e) {
                    System.out.println("You've provided date in incorrect format. " +
                            "Do you want to skip this step? y/n");
                    flag = (!scanner.nextLine().equals("y"));
                }

            }
            System.out.println("Thanks, " + passenger.getName() + ". We'll proceed your order.");
            System.out.printf("%n%n");

            // 3. System register an order
            RailwayTicketOffice railwayTicketOffice = new RailwayTicketOffice();
            railwayTicketOffice.setOrder(order);
            System.out.println("Hello booking clerk! Please provide your name.");
            String bookingClerkName = scanner.nextLine();
            railwayTicketOffice.setBookingClerk(new BookingClerk(bookingClerkName));
            BookingClerk bookingClerk = railwayTicketOffice.getBookingClerk();
            order.setBookingClerk(bookingClerk);

            // 4. Booking Clerk provide train info (train number, all destinations and their prices
            System.out.println(bookingClerk.getName() + ", you want to create trains auto - 1; manual - 2");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    bookingClerk.autoGenerateTrain(trains);
                    break;
                case 2:
                    bookingClerk.manualGenerateTrains(trains, scanner);
                    break;
                default:
                    System.out.println("There is no such option. Program will be stopped.");
                    System.exit(1);
            }

            // 5. Booking clerk shows all trains
            System.out.println("Below are the list of all created trains:");
            bookingClerk.showTrains(trains);

            // 6. System search trains
            ArrayList<Train> suitableTrains = new ArrayList<Train>();
            searchSuitableTrains(order.getDestination().getDestinationName(), suitableTrains);
            if (suitableTrains.size() == 0) {
                System.out.println("There is no available trains to "
                        + order.getDestination().getDestinationName() +
                        ". Execute program once again.");
                System.exit(1);
            }
            System.out.println("Suitable trains that were found by destination name:");
            bookingClerk.showTrains(suitableTrains);

            // 7.
            System.out.println("Passenger choose train and System validate provided train number.");
            System.out.println("Please provide train number from the Above Suitable Trains:");
            int trainNo = scanner.nextInt();
            if (validateTrainExistance(trainNo, suitableTrains)) {
                order.setTrain(getTrainFromSet(trainNo, suitableTrains));
            } else {
                System.out.println("You've provided invalid train number. You should run program once again.");
                System.exit(1);
            }

            // 8.
            System.out.println("System generate bill for current order.");
            order.setBill(generateBill(order.getDestination(), order.getTrain()));

            // 9.
            System.out.println("Thank you " + passenger.getName() + ". Below is your order info:");
            System.out.println(passenger.getOrder().toString());
        } catch (InputMismatchException ex) {
            System.out.println("You provided invalid data. You should run program once again.");
        }

    }

    public static void searchSuitableTrains(String destinationName, ArrayList<Train> suitableTrains) {

        for (int i = 0; i < trains.size(); i++)  {
            Train train = trains.get(i);
            ArrayList<Destination> destinations = train.getDestinations();
            for (int j = 0; j < destinations.size(); j++) {
                String destinationNameTrain = destinations.get(j).getDestinationName().toLowerCase();
                if (destinationName.toLowerCase().equals(destinationNameTrain)) {
                    suitableTrains.add(train);
                    // there is no point to search correct destination in train, that is already suitable
                    break;
                }
            }
        }

    }

    public static boolean validateTrainExistance(int trainNo, ArrayList<Train> suitableTrains) {
        Iterator<Train> iterator = suitableTrains.iterator();
        while (iterator.hasNext()) {
            Train train = iterator.next();
            if (train.getTrainNo() == trainNo) {
                return true;
            }
        }
        return false;
    }

    public static Train getTrainFromSet(int trainNo, ArrayList<Train> suitableTrains) {

        for (int i = 0; i < suitableTrains.size(); i++) {
            Train train = suitableTrains.get(i);
            if (train.getTrainNo() == trainNo) {
                return train;
                //break; //stop loop when system have found chosen train
            }
        }
        return null; // no train from provided trainNo wasn't found
    }


    public static Bill generateBill(Destination destination, Train train) {
        ArrayList<Destination> destinations = train.getDestinations();
        Bill bill = new Bill();
        for (int i = 0; i < destinations.size(); i++) {
            String destinationNameSearched = destination.getDestinationName().toLowerCase();
            String destinationNameTrain = destinations.get(i).getDestinationName().toLowerCase();
            if (destinationNameSearched.equals(destinationNameTrain)) {
                int price = destinations.get(i).getPrice();
                bill.setPrice(price);
            }
        }
        return bill;
    }

}
