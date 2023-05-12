package com.pathtofitness.application;

public class cart {

	private int Customer_id;
	private int Item_no;
	private String Item_Name;
	private String colour;
	private String size;
	private int quantity;
	private int Item_price;
	private String Item_detail;
	private String EDD;
	
	
	public String getItem_detail() {
		return Item_detail;
	}
	public void setItem_detail(String Item_detail) {
		this.Item_detail = Item_detail;
	}
	public String getEDD() {
		return EDD;
	}
	public void setEDD(String EDD) {
		this.EDD = EDD;
	}
	public int getCustomer_id() {
		return Customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.Customer_id = customer_id;
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
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getItem_price() {
		return Item_price;
	}
	public void setItem_price(int item_price) {
		this.Item_price = item_price;
	}
	
	
	
}

