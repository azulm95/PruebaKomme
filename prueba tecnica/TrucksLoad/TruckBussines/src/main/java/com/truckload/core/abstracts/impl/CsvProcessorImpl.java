package com.truckload.core.abstracts.impl;

import com.truckload.core.abstracts.AbstractCsvProcessor;
import com.truckload.core.abstracts.exceptions.CsvProcessorException;

public class CsvProcessorImpl<T> extends AbstractCsvProcessor<T>{

	
	public CsvProcessorImpl(T data) throws CsvProcessorException {
		super(data);
		this.convertToFile(new GenericConvertToFile<>());
	}

	//thats is an initialization of the strategy 
	public static class GenericConvertToFile<T> implements GetFile<T>{

		@Override
		public void convertToFile(T data) throws CsvProcessorException {
			
		}
		
		
	}
	
}
