package com.truckload.core.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Entity
public class Truck implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idt;
	
	
	public Long getIdt() {
		return idt;
	}
	public void setIdt(Long idt) {
		this.idt = idt;
	}
	private String idtruck;
	
	@Column
	private String owner;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Column
	private String mark;
	@Column
	private Long weight;
	@Column
	private Long numtires;
	@Column
	private Date datebuy;
	public String getIdtruck() {
		return idtruck;
	}
	public void setIdtruck(String idtruck) {
		this.idtruck = idtruck;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Long getWeight() {
		return weight;
	}
	public void setWeight(Long weight) {
		this.weight = weight;
	}
	public Long getNumtires() {
		return numtires;
	}
	public void setNumtires(Long numtires) {
		this.numtires = numtires;
	}
	public Date getDatebuy() {
		return datebuy;
	}
	public void setDatebuy(Date datebuy) {
		this.datebuy = datebuy;
	}


}
