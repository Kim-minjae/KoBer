package model;

import java.io.Serializable;

public class DriverDTO implements Serializable {
	int driver_id;
	String driver_name;
	String drver_phone;
	String driver_gender;
	String licence_num;
	int range;
	String current_pos;
	int drive_possible;
	int passenger_id;
	int car_id;
	
	public DriverDTO(int driver_id, String driver_name, String drver_phone, String driver_gender, int range,String current_pos,int car_id) {
		super();
		this.driver_id = driver_id;
		this.driver_name = driver_name;
		this.drver_phone = drver_phone;
		this.driver_gender = driver_gender;
		this.range=range;
		this.current_pos = current_pos;
		this.car_id=car_id;
	}
	public DriverDTO() {
		super();
	}
	public DriverDTO(String driver_name, String drver_phone, String driver_gender, String licence_num, int range,
			String current_pos, int drive_possible) {
		super();
		this.driver_name = driver_name;
		 this.drver_phone = drver_phone;
		this.driver_gender = driver_gender;
		this.licence_num = licence_num;
		this.range = range;
		this.current_pos = current_pos;
		this.drive_possible = drive_possible;
	}



	public DriverDTO(int driver_id, String driver_name, String drver_phone, String driver_gender, String licence_num,
			int range, String current_pos, int drive_possible, int passenger_id, int car_id) {
		super();
		this.driver_id = driver_id;
		this.driver_name = driver_name;
		this.drver_phone = drver_phone;
		this.driver_gender = driver_gender;
		this.licence_num = licence_num;
		this.range = range;
		this.current_pos = current_pos;
		this.drive_possible = drive_possible;
		this.passenger_id = passenger_id;
		this.car_id = car_id;
	}

	public int getDriver_id() {
		return driver_id;
	}
	@Override
	public String toString() {
		return "탑승가능차량 [driver_id=" + driver_id + ", driver_name=" + driver_name + ", drver_phone=" + drver_phone
				+ ", driver_gender=" + driver_gender + ", current_pos=" + current_pos + "]";
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
	public String getDrver_phone() {
		return drver_phone;
	}
	public void setDrver_phone(String drver_phone) {
		this.drver_phone = drver_phone;
	}


	public String getDriver_gender() {
		return driver_gender;
	}
	public void setDriver_gender(String driver_gender) {
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
	public String getCurrent_pos() {
		return current_pos;
	}
	public void setCurrent_pos(String current_pos) {
		this.current_pos = current_pos;
	}

	public int getPassenger_id() {
		return passenger_id;
	}

	public int getDrive_possible() {
		return drive_possible;
	}
	public void setDrive_possible(int drive_possible) {
		this.drive_possible = drive_possible;
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
	
}

