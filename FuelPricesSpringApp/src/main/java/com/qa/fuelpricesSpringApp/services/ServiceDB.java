package com.qa.fuelpricesSpringApp.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.qa.fuelpricesSpringApp.model.FuelPrices;
import com.qa.fuelpricesSpringApp.repo.Repo;



@Service
public class ServiceDB {
	// Takes in Repo as an attribute
	private Repo repo;


	
	public ServiceDB(Repo repo) {
	super();
	this.repo = repo;
}
	
//CRUD Functionality
	// Creates a new record and saves it in the DB
 	// Making all records lowercase BEFORE adding to the db
 	public FuelPrices createPrices(FuelPrices prices) {
 		FuelPrices savedPrices= repo.save(prices); // Takes in an entity, and puts in the DB 
 		return savedPrices;
 	}
 	
 	// Returning a record by searched by ID
public FuelPrices getById(long id) {
	// .get() if there is a value return it, otherwise throw an exception 
	return repo.findById(id).get();
	
}
//Returns all the objects as a List
	public List<FuelPrices> getPrices() {
		return repo.findAll();
	}
	public boolean remove(long id) {
		repo.deleteById(id);
		return true;
	}
	
	public boolean deleteAll() {
		repo.deleteAll();
		return true;
	}
	// Update takes in an ID and a request body
		public boolean update(long id, FuelPrices price) {
			
	// Find the object we want to update 
		FuelPrices oldPrice = getById(id);
			
	// Update the properties of this object 
	// running the setProperty method of the old price, replacing with entries for new price
		oldPrice.setManagerName(price.getManagerName());
		oldPrice.setCompetitorName(price.getCompetitorName());
		oldPrice.setLocation(price.getLocation());
		oldPrice.setFueltype(price.getFueltype());
		oldPrice.setPrice(price.getPrice());
			
	// Creating a new price with the value of old price
	    FuelPrices updatedPrice = oldPrice;
			
			// Saving this old Booking into the DB
			repo.save(updatedPrice);
			
			return true;
			
		}
}

