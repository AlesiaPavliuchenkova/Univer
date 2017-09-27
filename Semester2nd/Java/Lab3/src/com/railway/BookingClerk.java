package com.railway;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static com.railway.Railway.getTrainFromSet;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class BookingClerk extends Person
{
	public BookingClerk(String name){

		super(name);
	}


	public Train createTrain(String trainInfo) throws NumberFormatException{
		String[] trainInfoData = trainInfo.split(";");
		//just quick check - not correct to throw NumberFormatException here
		if (trainInfoData.length != 3) throw new NumberFormatException();

		int trainNo = Integer.parseInt(trainInfoData[0]);
		String[] destinationNames = trainInfoData[1].split(",");
		String[] prices = trainInfoData[2].split(",");
		//just quick check - not correct to throw NumberFormatException here
		if (destinationNames.length != prices.length) throw new NumberFormatException();

		ArrayList<Destination> destinations = new ArrayList<Destination>();
		for (int i = 0; i < destinationNames.length; i++) {
			destinations.add(new Destination(destinationNames[i], Integer.parseInt(prices[i])));
		}

		Train train = new Train(trainNo, destinations);

		return train;
	}

	public void autoGenerateTrain(ArrayList<Train> trains) {
		File file = new File("/Users/alesia/IdeaProjects/Lab3/src/trainInfo.txt");

		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				trains.add(createTrain(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void manualGenerateTrains(ArrayList<Train> trains, Scanner scanner) {

		System.out.println(this.getName() + ", how many trains you want to create?");
		int trainCount = scanner.nextInt();

		for (int i = 0; i < trainCount; i++) {
			System.out.printf("Provide train number, destinations, prices " +
					"in format: 1;Kyiv,Lviv,Lutsk;100,150,200");
			String trainInfo = scanner.next();
			try {
				//check if provided Train info already exists
				Train train = createTrain(trainInfo);
				if (getTrainFromSet(train.getTrainNo(),trains) != null) {
					System.out.println("Train with number " + train.getTrainNo() + " already exists. Try again.");
					i--;
					continue;
				}
				trains.add(train);
			} catch (NumberFormatException ex) {
				System.out.println("You've provided invalid data. Execute program once again.");
				System.exit(1);
			}
		}
	}

	public void showTrains(ArrayList<Train> trains) {

		for(int j = 0; j < trains.size(); j++) {
			Train train = trains.get(j);
			System.out.println("Train Number: " + train.getTrainNo());
			String format = "%-40s%s%n";
			ArrayList<Destination> destinations = train.getDestinations();
			for (int i = 0; i < destinations.size(); i++) {
				System.out.printf(format,"Destination Name: " + destinations.get(i).getDestinationName(),
				" Price: " + destinations.get(i).getPrice());
			}
			System.out.printf("%n");
		}

	}

	
}

