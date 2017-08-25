package model;

public class Payment_CarDTO {

	private int price;
	private String car_type;
	
	
	public Payment_CarDTO() {
		super();
	}


	public Payment_CarDTO(int price, String car_type) {
		super();
		this.price = price;
		this.car_type = car_type;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getCar_type() {
		return car_type;
	}


	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment_Per_CarTypeDTO [price=").append(price).append(", car_type=").append(car_type)
				.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
