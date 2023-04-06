package com.armezo.analytics.payload;

public class TaskwiseRequestPayload {
	
	private String registrationDate;
	private String applicationName;
	private String clientId;
	private String recruiterId;
	private String stateId;
	private String cityId;
	private String accesskey;
	private String assessmentStatus;
	private String passFailStatus;
	private Double percentage;
	private String assessmentDate;
	private String interviewStatus;
	private String interviewDate;
	private String offerStatus;
	private String documentUploadStatus;
	private String approvedStatus;
	
	public String getAccesskey() {
		return accesskey;
	}
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getAssessmentStatus() {
		return assessmentStatus;
	}
	public void setAssessmentStatus(String assessmentStatus) {
		this.assessmentStatus = assessmentStatus;
	}
	public String getPassFailStatus() {
		return passFailStatus;
	}
	public void setPassFailStatus(String passFailStatus) {
		this.passFailStatus = passFailStatus;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public String getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(String assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	public String getInterviewStatus() {
		return interviewStatus;
	}
	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	public String getDocumentUploadStatus() {
		return documentUploadStatus;
	}
	public void setDocumentUploadStatus(String documentUploadStatus) {
		this.documentUploadStatus = documentUploadStatus;
	}
	public String getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "TaskwiseRequestPayload [applicationName=" + applicationName + ", clientId=" + clientId
				+ ", recruiterId=" + recruiterId + ", stateId=" + stateId + ", cityId=" + cityId + ", accesskey="
				+ accesskey + ", assessmentStatus=" + assessmentStatus + ", passFailStatus=" + passFailStatus
				+ ", percentage=" + percentage + ", assessmentDate=" + assessmentDate + ", interviewStatus="
				+ interviewStatus + ", interviewDate=" + interviewDate + ", offerStatus=" + offerStatus
				+ ", documentUploadStatus=" + documentUploadStatus + ", approvedStatus=" + approvedStatus + "]";
	}
	
	
	

}
