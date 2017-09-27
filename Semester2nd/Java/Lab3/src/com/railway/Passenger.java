package com.railway;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Passenger extends Person
{
	private Order order;
	
	public Passenger(String name){
		super(name);
		order = new Order();
	}

	public Order getOrder() {
		return order;
	}
	public void makeOrder(Order order) {
		this.order = order;
	}
	
}

