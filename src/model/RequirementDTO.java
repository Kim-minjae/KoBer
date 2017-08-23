package model;

public class RequirementDTO {
	
	private int demand_id;
	private String start_point;
	private String destination;
	private int fellow_num;
	

	public RequirementDTO() {
		super();
	}



	public RequirementDTO(int demand_id, String start_point, String destination, int fellow_num) {
		super();
		this.demand_id = demand_id;
		this.start_point = start_point;
		this.destination = destination;
		this.fellow_num = fellow_num;
	}



	public int getDemand_id() {
		return demand_id;
	}



	public void setDemand_id(int demand_id) {
		this.demand_id = demand_id;
	}



	public String getStart_point() {
		return start_point;
	}



	public void setStart_point(String start_point) {
		this.start_point = start_point;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public int getFellow_num() {
		return fellow_num;
	}



	public void setFellow_num(int fellow_num) {
		this.fellow_num = fellow_num;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("requirementDTO [demand_id=").append(demand_id).append(", start_point=").append(start_point)
				.append(", destination=").append(destination).append(", fellow_num=").append(fellow_num).append("]");
		return builder.toString();
	}
	
	
	
	
	
	

}
