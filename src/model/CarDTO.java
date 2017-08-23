package model;

public class CarDTO {
	int id=1;
	String car_type;
	int capacity;
	
	@Override
	public String toString() {
		return "CarDTO [id=" + id + ", car_type=" + car_type + ", capacity=" + capacity + "]";
	}



	public CarDTO(int id, String car_type, int capacity) {
		super();
		this.id = id;
		this.car_type = car_type;
		this.capacity = capacity;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
