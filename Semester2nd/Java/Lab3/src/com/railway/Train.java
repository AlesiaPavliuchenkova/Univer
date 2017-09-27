package com.railway;


import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Train
{

	private int trainNo;
	private ArrayList<Destination> destinations;

	public Train(int trainNo, ArrayList<Destination> destinations){
		this.trainNo = trainNo;
		this.destinations = destinations;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}
	
	public void setDestinations(ArrayList<Destination> destinations) {
		this.destinations = destinations;
	}
	
	public int getTrainNo() {
		return trainNo;
	}
	
	public ArrayList<Destination> getDestinations() {
		return destinations;
	}
	
}

