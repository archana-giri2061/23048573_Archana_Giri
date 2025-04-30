package com.Savore.model;

public class fooditems {
	private Integer foodId;
	private String food_Name;
	private String Description;
	private String Price;
	private String Country;
	private String Image_url;
	private String Availability;
	
	public fooditems() {
	}

	public fooditems(String food_Name, String description, String price, String country, String image_url,
			String availability) {
		super();
		this.food_Name = food_Name;
		this.Description = description;
		this.Price = price;
		this.Country = country;
		this.Image_url = image_url;
		this.Availability = availability;
	}

	public fooditems(Integer foodId, String food_Name, String description, String price, String country,
			String image_url, String availability) {
		super();
		this.foodId = foodId;
		this.food_Name = food_Name;
		this.Description = description;
		this.Price = price;
		this.Country = country;
		this.Image_url = image_url;
		this.Availability = availability;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getFood_Name() {
		return food_Name;
	}

	public void setFood_Name(String food_Name) {
		this.food_Name = food_Name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		this.Price = price;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		this.Country = country;
	}

	public String getImage_url() {
		return Image_url;
	}

	public void setImage_url(String image_url) {
		this.Image_url = image_url;
	}

	public String getAvailability() {
		return Availability;
	}

	public void setAvailability(String availability) {
		this.Availability = availability;
	}
	
	
	
}
