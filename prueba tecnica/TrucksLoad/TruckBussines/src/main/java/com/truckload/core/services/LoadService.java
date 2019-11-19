package com.truckload.core.services;

import com.truckload.core.abstracts.exceptions.BussinesException;
import com.truckload.core.entity.Trucks;

public interface LoadService {
	
	public String loadCSV(Trucks t) throws BussinesException ;

}
