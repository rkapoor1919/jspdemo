package com.B1.FHIRWebApp;



public class PatientClass {
	private String id;
	private String idUse;
	private String idSystem;
	private String idValue;
	private Boolean active;
	private String fname;
	private String gname;
	private String gender;
	private String address;
	private String city;
	private String state;
	private String postal;
	private String country;
	private String email;
	private String birthDate;
	private String phone;
	private String generalPractitioner;
	


	public PatientClass(String id, String idUse, String idSystem, String idValue, Boolean active, String fname,
			String gname, String gender, String address, String city, String state, String postal, String country,
			String email, String birthDate, String phone, String generalPractitioner) {
		super();
		this.id = id;
		this.idUse = idUse;
		this.idSystem = idSystem;
		this.idValue = idValue;
		this.active = active;
		this.fname = fname;
		this.gname = gname;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.country = country;
		this.email = email;
		this.birthDate = birthDate;
		this.phone = phone;
		this.generalPractitioner = generalPractitioner;
	}

	public String getIdUse() {
		return idUse;
	}

	public void setIdUse(String idUse) {
		this.idUse = idUse;
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getIdSystem() {
		return idSystem;
	}

	public void setIdAssigner(String idSystem) {
		this.idSystem = idSystem;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getGeneralPractitioner() {
		return generalPractitioner;
	}
	
	public void setGeneralPractitioner(String generalPractitioner) {
		this.generalPractitioner = generalPractitioner;
	}
	

	
	
}