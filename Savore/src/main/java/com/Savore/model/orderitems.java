package com.Savore.model;

public class orderitems {
	private Integer orderItems_Id;
	private String Quality;
	private String price;
	
	public orderitems() {
	}

	public orderitems(Integer orderItems_Id) {
		super();
		this.orderItems_Id = orderItems_Id;
	}

	public orderitems(Integer orderItems_Id, String quality, String price) {
		super();
		this.orderItems_Id = orderItems_Id;
		this.Quality = quality;
		this.price = price;
	}

	public orderitems(String quality, String price) {
		super();
		this.Quality = quality;
		this.price = price;
	}

	public Integer getOrderItems_Id() {
		return orderItems_Id;
	}

	public void setOrderItems_Id(Integer orderItems_Id) {
		this.orderItems_Id = orderItems_Id;
	}

	public String getQuality() {
		return Quality;
	}

	public void setQuality(String quality) {
		Quality = quality;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
