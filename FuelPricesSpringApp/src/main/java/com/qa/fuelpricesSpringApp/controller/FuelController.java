package com.qa.fuelpricesSpringApp.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.fuelpricesSpringApp.model.FuelPrices;
import com.qa.fuelpricesSpringApp.services.ServiceDB;


// If you have multiple classes with annotation @RestController, Spring will use the first one it finds

@RestController
public class FuelController {
	// Not using the arrayList in the controller anymore!!!
//		private ArrayList<FuelPrices> bookingList = new ArrayList<>(); 
		
		// Response Entities offer more info when sending a response back
		// Response includes a message AND a status code we can specify AND the data it requested
		
		// Sending a Delete Request, we respond with "Deleted ID of x" AND code 202 
		// Sending a Get request, we respond with the Data requested AND a status code 200
		
	// Tell our Controller to use the Services Object
		// When Spring creates our Controller, it passes in the Service object
		private ServiceDB service;
		
		public FuelController(ServiceDB service) {
			super();
			this.service = service;
		}
		@PostMapping("/createPrices")
		public ResponseEntity<String> createPrices(@RequestBody FuelPrices price) {
			
			// run the method in the Services class, passing in the object recieved via HTTP Request
			service.createPrices(price);
			
			// returns a response entity<data it contains>
			ResponseEntity<String> response = new ResponseEntity<>("new price added with ID: " + price.getId(), HttpStatus.CREATED);
			return response;
		}
		
		@GetMapping("/getId/{id}")
		public ResponseEntity<FuelPrices> getById(@PathVariable("id") long id) {
			
			// Making an object variable called result = the data we're retrieving
			FuelPrices result = service.getById(id);
			
			// Making a ResponseEntity that contains the data we're sending
			ResponseEntity<FuelPrices> response = new ResponseEntity<>(result, HttpStatus.ACCEPTED);
			
			return response;
		}
		
		@GetMapping("/getPrices")
		public ResponseEntity<List<FuelPrices>> getPrices() {
			
			List<FuelPrices> response = service.getPrices();
			
			// Either one of these returns will work in the same way
			// ResponseEntity<ArrayList<FuelPrices>> response = new ResponseEntity<>(price List, HttpStatus.ACCEPTED);
			// return response;
			
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteByid(@PathVariable("id") long id) {
			service.remove(id);
			String response = "Booking of id: " + id + " deleted";
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		}
		
		// Update by id
		@PutMapping("/update/{id}")
		public ResponseEntity<String> updateByid(@PathVariable("id") long id, @RequestBody FuelPrices price) {
			
			service.update(id, price); 
			
			String response = "Updating booking of id: " + id;
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}
		
		// Delete all method within the controller 
		@DeleteMapping("/delete") // <-- Handling the request coming in
		public ResponseEntity<String> deleteAll() {
			
			//clear all price entries();  <-- Business logic, removing all data 
			service.deleteAll();
			
			//Handling the response to be sent back
			return new ResponseEntity<>("all bookings deleted", HttpStatus.ACCEPTED);
		}
}
