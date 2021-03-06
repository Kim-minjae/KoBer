package model;

import java.io.Serializable;

public class CarDTO implements Serializable{
	int car_id;
	String car_type;
	int capacity;


	public CarDTO() {
		super();
	}
	
	public CarDTO(int id, String car_type, int capacity) {
		super();
		this.car_type = car_type;
		this.capacity = capacity;
	}
	
	

	public CarDTO(String car_type, int capacity) {
		super();
		this.car_type = car_type;
		this.capacity = capacity;
	}

	public int getId() {
		return car_id;
	}

	public void setId(int car_id) {
		this.car_id = car_id;
	}

	public String getCar_type() {
		return car_type;
	}

	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "CarDTO [car_id=" + car_id + ", car_type=" + car_type + ", capacity=" + capacity + "]";
	}
}
