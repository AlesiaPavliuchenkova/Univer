package com.railway;


import sun.security.krb5.internal.crypto.Des;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Destination

{
	private static int count = 0;
	private int destinationNo;
	private String destinationName;
	private int price;

	public Destination(){
		super();
	}

	public Destination(String destinationName) {
		this.destinationNo = ++count;
		this.destinationName = destinationName;

	}

	public Destination(String destinationName, int price) {
		this.destinationNo = ++count;
		this.destinationName = destinationName;
		this.price = price;

	}

	public void setDestinationNo(int destinationNo) {
		this.destinationNo = destinationNo;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDestinationNo() {

		return destinationNo;
	}
	
	public String getDestinationName() {

		return destinationName;
	}
	
	public int getPrice() {

		return price;
	}
	
}

