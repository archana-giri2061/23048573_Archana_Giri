package com.Savore.model;

public class orderModel {
	private Integer orderId;
	private String orderDate;
	private String Status;
	private String totalAmount;
	private String deliveryAddress;
	
	public orderModel() {
	}
	
	public orderModel(Integer orderId) {
		super();
		this.orderId = orderId;
	}

	public orderModel(Integer orderId, String orderDate, String status, String totalAmount, String deliveryAddress) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.Status = status;
		this.totalAmount = totalAmount;
		this.deliveryAddress = deliveryAddress;
	}

	public orderModel(String orderDate, String status, String totalAmount, String deliveryAddress) {
		super();
		this.orderDate = orderDate;
		this.Status = status;
		this.totalAmount = totalAmount;
		this.deliveryAddress = deliveryAddress;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
}
