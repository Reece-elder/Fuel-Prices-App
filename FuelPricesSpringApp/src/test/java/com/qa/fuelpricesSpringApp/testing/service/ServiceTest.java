package com.qa.fuelpricesSpringApp.testing.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.fuelpricesSpringApp.model.FuelPrices;
import com.qa.fuelpricesSpringApp.repo.Repo;
import com.qa.fuelpricesSpringApp.services.ServiceDB;


@SpringBootTest
@ActiveProfiles("test")
public class ServiceTest {

	// Adding the class we are Mocking (Repo)
	// Creating a Mock bean object of our Repo, for us to plug into our service 
	@MockBean
	private Repo repo;

	// Autowired - Spring automatically plugs in the beans into the object
	@Autowired
	private ServiceDB service; // Create a service object, plug in the mock repo automatically 

	
	// Test Objects I can pass into methods and have my repo return them 
	FuelPrices newprice1 = new FuelPrices("managerName1", "competitorName1", "location1", "fueltype1", 1);
	FuelPrices newprice2 = new FuelPrices("managerName2", "competitorName2", "location2", "fueltype1", 2);
	// Objects WITH id are for when we return them from our repo 
	FuelPrices newprice1Id = new FuelPrices(1l,"managerName1", "competitorName1", "location1", "fueltype1", 1);
	FuelPrices newprice2Id = new FuelPrices(2l, "managerName2", "competitorName2", "location2", "fueltype1", 2);

	
	@Test
	public void testCreate() {

		// Arrange 
		// When we tell our repo to save data, it should return the object with id
		// When our mock repo runs the save method taking in newprice1
		// it should return newprice1d
		Mockito.when(repo.save(newprice1)).thenReturn(newprice1Id); 

		// Act
		// our boolean result equals to whatever our createBooking returns (true)
		FuelPrices result = service.createPrices(newprice1);
		System.out.println(result);
		System.out.println(newprice1Id);

		// Assert
		Assertions.assertEquals(newprice1Id, result);

//		Assertions.assertTrue(result);
		// Behvaiour Testing

		// Verifying if the mock Repo, was triggered (1) times, and what methods are we checking
		Mockito.verify(repo, Mockito.times(1)).save(newprice1);

	}

	@Test
	public void testGetById() {

		// Arrange
		// Returns an Optional of an object, an object wrapped up in a box (so we dont cause a null pointer exception)
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(newprice1Id));

		// Act
		FuelPrices result = service.getById(1l);

		// Assert
		Assertions.assertEquals(newprice1Id, result);

		// Our mocked object NEVER runs the .count()
		Mockito.verify(repo, Mockito.never()).count();

	}
	
	@Test
	public void testGetPrices() {

		// Arrange
		// Returns an Optional of an object, an object wrapped up in a box (so we dont cause a null pointer exception)
        List<FuelPrices> newprices = new ArrayList();
       newprices.add(newprice1);
       
		
		Mockito.when(this.repo.findAll()).thenReturn(newprices);

		
		// Act
		List<FuelPrices> expected =newprices;
		assertThat(this.service.getPrices()).isEqualTo(newprices);
		

		// Assert
		Assertions.assertEquals(expected, newprices);

		// Our mocked object NEVER runs the .count()
		Mockito.verify(this.repo, Mockito.times(1)).findAll();

	}
	
	@Test
	public void testRemove() {
		// GIVEN
		Long id = 1L;
		// WHEN
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		// THEN
		assertThat(this.service.remove(id)).isTrue();
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	
	}
	
	@Test
	public void testDelete() {
		// GIVEN
		Long id = 1L;//user input
		// Optional Chocolate
		Optional<FuelPrices> optChoco = Optional.of(newprice1Id);
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(optChoco);
		// THEN
		assertThat(this.service.delete(id)).isEqualTo(newprice1Id);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	void testUpdate() {
		// GIVEN - id, object
		Long id = 1L;
		FuelPrices toUpdate = new FuelPrices("managerName1", "competitorName1", "location1", "fueltype1", 1);
		// METHOD USES AN OPTIONAL VERSION OF THE ANIMAL OBJECT
		Optional<FuelPrices> opt = Optional.of(newprice1Id);
		// UPDATED VERSION:
		FuelPrices updated = new FuelPrices(id, toUpdate.getManagerName(), toUpdate.getCompetitorName(), toUpdate.getLocation(), toUpdate.getFueltype(), toUpdate.getPrice());
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(opt);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		// THEN
		assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	}
	
}
