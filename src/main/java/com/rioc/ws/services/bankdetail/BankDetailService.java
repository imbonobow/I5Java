package com.rioc.ws.services.bankdetail;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.iAccountMapper;
import com.rioc.ws.mappers.iBankDetailMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.BankDetailDto;
import com.rioc.ws.repositories.iAccountRepository;
import com.rioc.ws.repositories.iBankDetailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankDetailService implements iBankDetailService
{
	private iAccountRepository repository;
	private iBankDetailRepository bankDetailRepository;
	private iBankDetailMapper mapper;

	public BankDetailService(iAccountRepository repository, iBankDetailRepository bankDetailRepository, iBankDetailMapper mapper)
	{
		super();
		this.repository = repository;
		this.bankDetailRepository = bankDetailRepository;
		this.mapper = mapper;
	}



	public BankDetailDto postBankDetail (BankDetailDto bankDetail)
	{

		//Transform the DTO into a DAO
		BankDetail bankDetailDao = mapper.toDao(bankDetail);
		System.out.println("bankDetail " + bankDetail);
		Account account = repository.findById(bankDetail.getAccount_id()).orElseThrow(()-> new ApiException("Invalid ID !!!", HttpStatus.NOT_FOUND));
		bankDetailDao.setAccount(account);

		//check if iban format is correct
		//You can test with the site http://randomiban.com/?country=France or other country for example
		if(!bankDetail.getIban().matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{4}[0-9]{7}([A-Z0-9]?){0,16}$"))
		{
			throw new ApiException("Invalid IBAN format !!!", HttpStatus.BAD_REQUEST);
		}
		else
		{
			//Save the DAO
			bankDetailDao = bankDetailRepository.save(bankDetailDao);
			return bankDetail;
		}


	}


	public BankDetail getBankDetailById (int id)
	{
		//Get the DAO from the database
		Optional<BankDetail> bankDetailDao = bankDetailRepository.findById(id);
		return bankDetailDao.orElseThrow(()-> new ApiException("Invalid ID !!!", HttpStatus.NOT_FOUND));
	}

	public BankDetail delBankDetailById (int id)
	{
		//Get the DAO from the database
		Optional<BankDetail> bankDetailDao = bankDetailRepository.findById(id);
		bankDetailRepository.deleteById(id);
		return bankDetailDao.orElseThrow(()-> new ApiException("Invalid ID !!!", HttpStatus.NOT_FOUND));
	}
}

