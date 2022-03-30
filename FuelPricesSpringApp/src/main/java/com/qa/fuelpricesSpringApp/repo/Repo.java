package com.qa.fuelpricesSpringApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.fuelpricesSpringApp.model.FuelPrices;



@Repository
//for basic methods stored we may need to access our database
public interface Repo extends JpaRepository<FuelPrices, Long> {

// Abstract Method - Takes in no method body

public List<FuelPrices> findByManagerName(String manager_name);

public List<FuelPrices> findByCompetitorName(String competitor_name);
// If you call a method findBy<column header>  -> Returns a List of objects with that value

public List<FuelPrices> findByLocation(String location);

public List<FuelPrices> findByFueltype(String location);

public List<FuelPrices> findByPrice(int price);

}
