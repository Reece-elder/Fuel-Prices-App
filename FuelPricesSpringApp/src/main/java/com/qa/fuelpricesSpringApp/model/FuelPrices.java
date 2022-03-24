package com.qa.fuelpricesSpringApp.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FuelPrices {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@Column(nullable = false, length = 50) 
	private String manager_name;
	
	@Column(nullable = false, length = 50) 
	private String competitor_name;
	
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

	// constructor WITHOUT ID (for posting data into the database)
	public FuelPrices(String manager_name, String competitor_name, String location, String fueltype, float price) {
		super();
		this.manager_name = manager_name;
		this.competitor_name = competitor_name;
		this.location = location;
		this.fueltype = fueltype;
		this.price = price;
	}

	//constructor WITH id (for retrieving data from the database)
	public FuelPrices(long id, String manager_name, String competitor_name, String location, String fueltype,
			float price) {
		super();
		this.id = id;
		this.manager_name = manager_name;
		this.competitor_name = competitor_name;
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

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public String getCompetitor_name() {
		return competitor_name;
	}

	public void setCompetitor_name(String competitor_name) {
		this.competitor_name = competitor_name;
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

	
	
	// Objects already have a built in hashcode method, hashcodes are generated 
	// randomly and equal this objects memory reference
	// hashcode - com.qa.car@26vbfhf
	
	// override hashcode makes two objects with the same values have the same hashcode
	
@Override
public int hashCode() {
	return Objects.hash(competitor_name, fueltype, id, location, manager_name, price);
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
	return Objects.equals(competitor_name, other.competitor_name) && Objects.equals(fueltype, other.fueltype)
			&& id == other.id && Objects.equals(location, other.location)
			&& Objects.equals(manager_name, other.manager_name)
			&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	
	
	
}
}
