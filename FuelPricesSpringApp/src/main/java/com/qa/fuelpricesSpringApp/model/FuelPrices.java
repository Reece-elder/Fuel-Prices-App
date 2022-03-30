package com.qa.fuelpricesSpringApp.model;

import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FuelPrices {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@Column(nullable = false, length = 50) 
	private String managerName;
	
	@Column(nullable = false, length = 50) 
	private String competitorName;
	
	@Column(nullable = false, length = 80)
	private String location;
	
	@Column(nullable = false)
	private String fueltype;
	
	@Column(nullable = false)
	private float price;

	
	// super constructor
	
	public FuelPrices() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FuelPrices(String managerName, String competitorName, String location, String fueltype, float price) {
		super();
		this.managerName = managerName;
		this.competitorName = competitorName;
		this.location = location;
		this.fueltype = fueltype;
		this.price = price;
		
	}


	public FuelPrices(long id, String managerName, String competitorName, String location, String fueltype,
			float price) {
		super();
		this.id = id;
		this.managerName = managerName;
		this.competitorName = competitorName;
		this.location = location;
		this.fueltype = fueltype;
		this.price = price;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getManagerName() {
		return managerName;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public String getCompetitorName() {
		return competitorName;
	}


	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getFueltype() {
		return fueltype;
	}


	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}

	//hashcode and equals
	@Override
	public int hashCode() {
		return Objects.hash(competitorName, fueltype, id, location, managerName, price);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuelPrices other = (FuelPrices) obj;
		return Objects.equals(competitorName, other.competitorName) && Objects.equals(fueltype, other.fueltype)
				&& id == other.id && Objects.equals(location, other.location)
				&& Objects.equals(managerName, other.managerName)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}
	
	
	
	
}
	
	

	