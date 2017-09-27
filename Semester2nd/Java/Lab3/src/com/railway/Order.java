package com.railway;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Order
{
	private int orderNo;
	private Destination destination;
	private Date travelDate;
	private Bill bill;
	private Passenger passenger;
	private BookingClerk bookingClerk;
	private Train train;

	public Order(){

		super();
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	
	public void setBill(Bill bill) {

		this.bill = bill;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public void setBookingClerk(BookingClerk bookingClerk) {
		this.bookingClerk = bookingClerk;
	}
	
	public void setTrain(Train train) {
		this.train = train;
	}
	
	public int getOrderNo() {

		return orderNo;
	}
	
	public Destination getDestination() {

		return destination;
	}
	
	public Date getTravelDate() {
		return travelDate;
	}
	
	public Bill getBill() {

		return bill;
	}

	public Passenger getPassenger() {

		return passenger;
	}

	public BookingClerk getBookingClerk() {

		return bookingClerk;
	}

	public Train getTrain() {

		return train;
	}

	@Override
	public String toString() {
		return "Order №: " + orderNo + "\n" +
				"Destination: " + destination.getDestinationName() + "\n" +
				"Travel Date: " + travelDate + "\n" +
				"Bill Info: " + bill + "\n" +
				"Passenger: " + passenger.getName() + "\n" +
				"Booking Clerk: " + bookingClerk.getName() + "\n" +
				"Train: " + train.getTrainNo();
	}
}

