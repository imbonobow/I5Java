package com.rioc.ws.models.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "addresses")

public class Address implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID", unique = true, nullable = false)
	private int addressId;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STREET")
	private String street;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CITY")
	private String city;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY")
	private String country;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ZIP_CODE")
	private int zipCode;

	public Address (int addressId, String street, String city, String country, int zipCode)
	{
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	public int getAddressId ()
	{
		return addressId;
	}

	public void setAddressId (int addressId)
	{
		this.addressId = addressId;
	}

	public String getStreet ()
	{
		return street;
	}

	public void setStreet (String street)
	{
		this.street = street;
	}

	public String getCity ()
	{
		return city;
	}

	public void setCity (String city)
	{
		this.city = city;
	}

	public String getCountry ()
	{
		return country;
	}

	public void setCountry (String country)
	{
		this.country = country;
	}

	public int getZipCode ()
	{
		return zipCode;
	}

	public void setZipCode (int zipCode)
	{
		this.zipCode = zipCode;
	}

	public Address () {
		// TODO Auto-generated constructor stub
	}
}
