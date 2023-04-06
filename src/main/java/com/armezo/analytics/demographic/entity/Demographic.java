package com.armezo.analytics.demographic.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demographic")
public class Demographic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private String applicationName;
	private String clientId;
	private String recruiterId;
	private String stateId;
	private String cityId;
	private String accesskey;
	private String registrationStatus;
	private Date registrationDate;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDate;
	private Date modifiedDate;
	private String recruitmentSource;
	private String profile;
	private String designation;
	private Integer age;
	private String gender;
	private Long mobile;
	private Integer experienceInMonths;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getAccesskey() {
		return accesskey;
	}
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	public String getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getRecruitmentSource() {
		return recruitmentSource;
	}
	public void setRecruitmentSource(String recruitmentSource) {
		this.recruitmentSource = recruitmentSource;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public Integer getExperienceInMonths() {
		return experienceInMonths;
	}
	public void setExperienceInMonths(Integer experienceInMonths) {
		this.experienceInMonths = experienceInMonths;
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
		return "Demographic [id=" + id + ", applicationName=" + applicationName + ", clientId=" + clientId
				+ ", recruiterId=" + recruiterId + ", stateId=" + stateId + ", cityId=" + cityId + ", accesskey="
				+ accesskey + ", registrationStatus=" + registrationStatus + ", registrationDate=" + registrationDate
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", modifiedDate=" + modifiedDate + ", recruitmentSource=" + recruitmentSource
				+ ", profile=" + profile + ", designation=" + designation + ", age=" + age + ", gender=" + gender
				+ ", mobile=" + mobile + ", experienceInMonths=" + experienceInMonths + "]";
	}
	
	

}
