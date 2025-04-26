package com.thegoldenbook.model;

public class Address extends AbstractValueObject{
	
	private Long id;
	private String streetName;
	private String addressLine2;
	private Integer localityId;
	private Long userId;
	private Long employeeId;
	private String localityName;
	private Integer regionId;
	private String regionName;
	private Integer countryId;
	private String countryName;
	
	
	public Address (){
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public Integer getLocalityId() {
		return localityId;
	}


	public void setLocalityId(Integer localityId) {
		this.localityId = localityId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}


	public String getLocalityName() {
		return localityName;
	}


	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}


	public Integer getRegionId() {
		return regionId;
	}


	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public Integer getCountryId() {
		return countryId;
	}


	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}
