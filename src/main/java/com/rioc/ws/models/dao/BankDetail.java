package com.rioc.ws.models.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bankdetails")


public class BankDetail implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BANKDETAIL_ID", unique = true, nullable = false)
	private int bankDetailId;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IBAN")
	private String iban;

	@JsonBackReference //bidirectional relationship
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
	private Account account;

	public BankDetail (int bankDetailId, String iban, Account account)
	{
		this.bankDetailId = bankDetailId;
		this.iban = iban;
		this.account = account;
	}

	public int getBankDetailId ()
	{
		return bankDetailId;
	}

	public void setBankDetailId (int bankDetailId)
	{
		this.bankDetailId = bankDetailId;
	}

	public String getIban ()
	{
		return iban;
	}

	public void setIban (String iban)
	{
		this.iban = iban;
	}

	public Account getAccount ()
	{
		return account;
	}

	public void setAccount (Account account)
	{
		this.account = account;
	}

	public BankDetail ()
	{
		// TODO Auto-generated constructor stub
	}

}
