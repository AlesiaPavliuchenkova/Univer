package com.railway;


import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RailwayTicketOffice
{
	private ArrayList<Order> orders;
	private BookingClerk bookingClerk;
	
	public RailwayTicketOffice(){

		this.orders = new ArrayList<Order>();
	}

	public void setOrder(Order order) {
		this.orders.add(order);
	}
	
	public void setBookingClerk(BookingClerk bookingClerk) {
		this.bookingClerk = bookingClerk;
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	public BookingClerk getBookingClerk() {
		return bookingClerk;
	}
	
}

