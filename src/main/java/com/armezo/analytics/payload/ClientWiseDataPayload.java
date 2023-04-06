package com.armezo.analytics.payload;

public class ClientWiseDataPayload {
	
	private String applicationname;
	private String clientId;
	private Integer registered;
	private Integer assessment;
	private Integer passed;
	private Integer offered;
	private Integer recruited;
	public String getApplicationname() {
		return applicationname;
	}
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Integer getRegistered() {
		return registered;
	}
	public void setRegistered(Integer registered) {
		this.registered = registered;
	}
	public Integer getAssessment() {
		return assessment;
	}
	public void setAssessment(Integer assessment) {
		this.assessment = assessment;
	}
	public Integer getPassed() {
		return passed;
	}
	public void setPassed(Integer passed) {
		this.passed = passed;
	}
	public Integer getOffered() {
		return offered;
	}
	public void setOffered(Integer offered) {
		this.offered = offered;
	}
	public Integer getRecruited() {
		return recruited;
	}
	public void setRecruited(Integer recruited) {
		this.recruited = recruited;
	}
	@Override
	public String toString() {
		return "ClientWiseDataPayload [applicationname=" + applicationname + ", clientId=" + clientId + ", registered="
				+ registered + ", assessment=" + assessment + ", passed=" + passed + ", offered=" + offered
				+ ", recruited=" + recruited + "]";
	}
	
	
	
	

}
