package com.pathtofitness.application;

public class orders {
	private int Order_id;
	private int customer_id;
	private String Item_Name;
	private int Item_no;
	private int Item_price;
	private int Shipping_Charges;
	private int Total_amount;
	private String Customer_name;
	private String Ship_to_address;
	private int Pincode;
	private String Payment_mode;
	private String Order_placed;
	private String Delivered_date;
	private String size;
	private String Item_detail;	
	
	

	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getItem_detail() {
		return Item_detail;
	}
	public void setItem_detail(String Item_detail) {
		this.Item_detail = Item_detail;
	}
	public int getOrder_id() {
		return Order_id;
	}
	public void setOrder_id(int order_id) {
		this.Order_id = order_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getItem_Name() {
		return Item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.Item_Name = item_Name;
	}
	public int getItem_no() {
		return Item_no;
	}
	public void setItem_no(int item_no) {
		this.Item_no = item_no;
	}
	public int getItem_price() {
		return Item_price;
	}
	public void setItem_price(int item_price) {
		this.Item_price = item_price;
	}
	public int getShipping_Charges() {
		return Shipping_Charges;
	}
	public void setShipping_Charges(int shipping_Charges) {
		this.Shipping_Charges = shipping_Charges;
	}
	public int getTotal_amount() {
		return Total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.Total_amount = total_amount;
	}
	public String getCustomer_name() {
		return Customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.Customer_name = customer_name;
	}
	public String getShip_to_address() {
		return Ship_to_address;
	}
	public void setShip_to_address(String ship_to_address) {
		this.Ship_to_address = ship_to_address;
	}
	public int getPincode() {
		return Pincode;
	}
	public void setPincode(int pincode) {
		this.Pincode = pincode;
	}
	public String getPayment_mode() {
		return Payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.Payment_mode = payment_mode;
	}
	public String getOrder_placed() {
		return Order_placed;
	}
	public void setOrder_placed(String order_placed) {
		Order_placed = order_placed;
	}
	public String getDelivered_date() {
		return Delivered_date;
	}
	public void setDelivered_date(String delivered_date) {
		Delivered_date = delivered_date;
	}
	
	
	

}
