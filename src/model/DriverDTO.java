package model;

import java.util.Date;

public class DriverDTO {
	
	private int driver_id;		 
	private String driver_name; 
	private String driver_phone; 
	private char driver_gender;	 
	private String licence_num;	 
	private int range;		 
	private String c_position;	 
	private Date c_time;		 
	private String d_possible;	 
	
	private int passenger_id;	 
	private int car_id;			 

	
	public DriverDTO() { 
		super();
	}


	public DriverDTO(int driver_id, String driver_name, String driver_phone, char driver_gender, String licence_num,
			int range, String c_position, Date c_time, String d_possible, int passenger_id, int car_id) {
		super();
		this.driver_id = driver_id;
		this.driver_name = driver_name;
		this.driver_phone = driver_phone;
		this.driver_gender = driver_gender;
		this.licence_num = licence_num;
		this.range = range;
		this.c_position = c_position;
		this.c_time = c_time;
		this.d_possible = d_possible;
		this.passenger_id = passenger_id;
		this.car_id = car_id;
	}//������


	public int getDriver_id() {
		return driver_id;
	}


	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}


	public String getDriver_name() {
		return driver_name;
	}


	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}


	public String getDriver_phone() {
		return driver_phone;
	}


	public void setDriver_phone(String driver_phone) {
		this.driver_phone = driver_phone;
	}


	public char getDriver_gender() {
		return driver_gender;
	}


	public void setDriver_gender(char driver_gender) {
		this.driver_gender = driver_gender;
	}


	public String getLicence_num() {
		return licence_num;
	}


	public void setLicence_num(String licence_num) {
		this.licence_num = licence_num;
	}


	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}


	public String getC_position() {
		return c_position;
	}


	public void setC_position(String c_position) {
		this.c_position = c_position;
	}


	public Date getC_time() {
		return c_time;
	}


	public void setC_time(Date c_time) {
		this.c_time = c_time;
	}


	public String getD_possible() {
		return d_possible;
	}


	public void setD_possible(String d_possible) {
		this.d_possible = d_possible;
	}


	public int getPassenger_id() {
		return passenger_id;
	}


	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}


	public int getCar_id() {
		return car_id;
	}


	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	//**********getter, setter**************


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DriverDTO [driver_id=").append(driver_id).append(", driver_name=").append(driver_name)
				.append(", driver_phone=").append(driver_phone).append(", driver_gender=").append(driver_gender)
				.append(", licence_num=").append(licence_num).append(", range=").append(range).append(", c_position=")
				.append(c_position).append(", c_time=").append(c_time).append(", d_possible=").append(d_possible)
				.append(", passenger_id=").append(passenger_id).append(", car_id=").append(car_id).append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
}
