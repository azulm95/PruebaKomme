package com.truckload.core.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.truckload.core.models.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
@Query("SELECT idt,idtruck,owner,mark,weight,numtires,datebuy FROM Truck")
public List<Truck> findAll();
	
@Query("SELECT idt,idtruck,owner,mark,weight,numtires,datebuy FROM Truck WHERE idt =:idt")
public Truck findByIdtruck(@Param("idt") Long id);


	
	
}
