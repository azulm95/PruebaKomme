package com.truckload.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.truckload.core.abstracts.exceptions.FileUtilException;
public class FileUtils {

	  private static final Logger LOGGER=LoggerFactory.getLogger(FileUtils.class);
	  
	private FileUtils() {
		super();
	}

	public static String convertStringtoFile(String file,String filename)throws FileUtilException{
		
		File csv = new File("D:\\csvs\\"+filename);
		 try ( FileOutputStream fos = new FileOutputStream(csv); ) {
			byte[] fileArray  = Base64.getDecoder().decode(file);
			fos.write(fileArray);
			fos.flush();
		} catch (Exception e) {
			throw new FileUtilException("Fallo al convertir de base 64 a archivo",e);
		}
		return csv.getPath();
	}
	
	
	
	

	
	
}
