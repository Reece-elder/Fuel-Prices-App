package com.qa.fuelpricesSpringApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.fuelpricesSpringApp.model.FuelPrices;


//for basic methods stored we may need to access our database
public interface Repo extends JpaRepository<FuelPrices, Long>{}
