package com.invsol.getfoodyc.dataobjects;

public class Restaurant {
	private int restaurant_id, zipcode;
	private long phonenumber;
	private String name, email, address, city, state, service_starttime, service_endtime;
	
	public Restaurant(int restaurant_id ){
		this.restaurant_id = restaurant_id;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getService_starttime() {
		return service_starttime;
	}

	public void setService_starttime(String service_starttime) {
		this.service_starttime = service_starttime;
	}

	public String getService_endtime() {
		return service_endtime;
	}

	public void setService_endtime(String service_endtime) {
		this.service_endtime = service_endtime;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}
	
	
}
