package model;

public class Payment_Per_CarTypeDTO {
	int small;
	int medium;
	int big;
	
	public Payment_Per_CarTypeDTO(int small, int medium, int big) {
		super();
		this.small = small;
		this.medium = medium;
		this.big = big;
	}
	public Payment_Per_CarTypeDTO() {
		super();
	}
	public int getSmall() {
		return small;
	}
	public void setSmall(int small) {
		this.small = small;
	}
	public int getMedium() {
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	public int getBig() {
		return big;
	}
	public void setBig(int big) {
		this.big = big;
	}
	
}
