package model;

import java.sql.Date;

public class LogDTO {
	private int log_id;
	private int log_user;
	private String log_action;
	private Date log_time;
	
	
	public LogDTO() {
	
	}
	
	

	public LogDTO(int log_user, String log_action) {
		super();
		this.log_user = log_user;
		this.log_action = log_action;
	}



	public LogDTO(int log_id, int log_user, String log_action, Date log_time) {
		super();
		this.log_id = log_id;
		this.log_user = log_user;
		this.log_action = log_action;
		this.log_time = log_time;
	}

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public int getLog_user() {
		return log_user;
	}

	public void setLog_user(int log_user) {
		this.log_user = log_user;
	}

	public String getLog_action() {
		return log_action;
	}

	public void setLog_action(String log_action) {
		this.log_action = log_action;
	}

	public Date getLog_time() {
		return log_time;
	}

	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogDTO [log_id=").append(log_id).append(", log_user=").append(log_user).append(", log_action=")
				.append(log_action).append(", log_time=").append(log_time).append("]");
		return builder.toString();
	}
	
	
}
