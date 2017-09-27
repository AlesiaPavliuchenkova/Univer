package com.railway;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Bill
{
	static private int billId = 0;
	private int price;
	
	public Bill(){
		++this.billId;

	}
//
//	public void setBillId() {
//		++this.billId;
//	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getBillId() {
		// TODO implement me
		return billId;
	}
	
	public int getPrice() {
		// TODO implement me
		return price;
	}

	@Override
	public String toString() {
		int billId = this.getBillId();
		return	"bill №: " + billId +
				"; price: " + price;
	}
}

