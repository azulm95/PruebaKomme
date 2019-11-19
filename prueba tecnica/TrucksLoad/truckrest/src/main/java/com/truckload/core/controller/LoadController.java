package com.truckload.core.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.truckload.core.abstracts.exceptions.BussinesException;
import com.truckload.core.entity.Trucks;
import com.truckload.core.services.LoadService;




@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.POST,RequestMethod.GET})
public class LoadController {

	@Autowired
	LoadService service;
	
	
	//That is the starts of the api
	 // tr an simple dto no it no be a model//
	@PostMapping("/trucksapi/add")
	public ResponseEntity<String> addTruck(Trucks tr) {
		ResponseEntity<String> respuesta = ResponseEntity.ok("Ok");
		try{
			this.getService().loadCSV(tr);
			
		}catch(BussinesException e) {
			
			respuesta = ResponseEntity.badRequest().body(e.getMessage()+ " "+e.getLocalizedMessage());
		}
		return respuesta; 
	}
	
	public LoadService getService() {
		return service;
	}

	public void setService(LoadService service) {
		this.service = service;
	}
	
}
