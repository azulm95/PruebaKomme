package com.truckload.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckload.core.models.Truck;
import com.truckload.core.repository.TruckRepository;
import com.truckload.core.services.TruckService;
@Service
public class TruckServiceImpl implements TruckService {

	@Autowired
	TruckRepository repo;

	@Override
	public List<Truck> getAllTrucks() {

		return this.repo.findAll();
	}

	@Override
	public Truck getTruckById(Long id) {

		return this.repo.findByIdtruck(id);
	}

	@Override
	public void addTruck(Truck t) {
		this.repo.saveAndFlush(t);

	}

	public TruckRepository getRepo() {
		return repo;
	}

	public void setRepo(TruckRepository repo) {
		this.repo = repo;
	}

}
