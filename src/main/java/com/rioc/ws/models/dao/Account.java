package com.rioc.ws.models.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rioc.ws.models.dto.BankDetailDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "accounts")

public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false)
	private int accountId;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
	private Address address;

	//link to bankdetail (when account is deleted, all bankdetails linked to this account are deleted)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@JsonManagedReference //forward part of the reference
	private List<BankDetail> bankDetails;

	public Account (int accountId, String firstName, String lastName, Address address, List<BankDetail> bankDetails)
	{
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.bankDetails = bankDetails;
	}

	public int getAccountId ()
	{
		return accountId;
	}

	public void setAccountId (int accountId)
	{
		this.accountId = accountId;
	}

	public String getFirstName ()
	{
		return firstName;
	}

	public void setFirstName (String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName ()
	{
		return lastName;
	}

	public void setLastName (String lastName)
	{
		this.lastName = lastName;
	}

	public Address getAddress ()
	{
		return address;
	}

	public void setAddress (Address address)
	{
		this.address = address;
	}

	public List<BankDetail> getBankDetails ()
	{
		return bankDetails;
	}

	public void setBankDetails (List<BankDetail> bankDetails)
	{
		this.bankDetails = bankDetails;
	}

	public Account () {
		// TODO Auto-generated constructor stub
	}

	public void setBankDetail (BankDetail bankDetailDao)
	{
		// TODO Auto-generated method stub

	}
}

