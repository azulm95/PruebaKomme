package com.truckload.core.services;

import java.util.List;

import com.truckload.core.models.Truck;


public interface TruckService {
	
	public List<Truck> getAllTrucks();
	
	public Truck getTruckById(Long id);
	
	public void addTruck(Truck t);

}
