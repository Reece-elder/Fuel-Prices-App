package com.qa.fuelpricesSpringApp.services;

import java.util.List;
import java.util.Optional;
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
		List<FuelPrices> FuelPrices = repo.findAll();
		return FuelPrices;
	}
	public boolean remove(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	public FuelPrices delete(Long id) {
		Optional<FuelPrices> toDelete = this.repo.findById(id);
		this.repo.deleteById(id);
		return toDelete.orElse(null);
	}
	
	// Update takes in an ID and a request body
		public FuelPrices update(long id, FuelPrices price) {
			
	// Find the object we want to update 
			Optional<FuelPrices> optPrice =  this.repo.findById(id);
			FuelPrices updatedPrice = optPrice.get();
			updatedPrice.setManagerName(price.getManagerName());
			updatedPrice.setCompetitorName(price.getCompetitorName());
			updatedPrice.setLocation(price.getLocation());
			updatedPrice.setFueltype(price.getFueltype());
			updatedPrice.setPrice(price.getPrice());
			return this.repo.save(updatedPrice);
			
		}

		public boolean deleteAll() {
			repo.deleteAll();
			return true;
		
		}
}

