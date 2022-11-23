package com.rioc.ws.services.account;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.iAccountMapper;
import com.rioc.ws.mappers.iAccountNoBankMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.AccountNoBankDto;
import com.rioc.ws.repositories.iAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements iAccountService
{
	private iAccountRepository repository;
	private iAccountMapper mapper;
	private iAccountNoBankMapper mapperNoBank;

	public AccountService (iAccountRepository repository, iAccountMapper mapper, iAccountNoBankMapper mapperNoBank)
	{
		super();
		this.repository = repository;
		this.mapper = mapper;
		this.mapperNoBank = mapperNoBank;
	}


	//post account
	public AccountNoBankDto postAccount (AccountNoBankDto account)
	{
		if (!repository.findAccountByFirstNameAndLastName(account.getFirstName(), account.getLastName()).isEmpty())
		{
			throw new ApiException("Account already exists", HttpStatus.NOT_ACCEPTABLE);
		}

		//send get request to api https://adresse.data.gouv.fr/api-doc/adresse/ to check if the address is valid
		//https://api-adresse.data.gouv.fr/search/?q=2%20rue%20de%20la%20gare%20paris
		//with RestTemplate

		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api-adresse.data.gouv.fr/search/?q=" + account.getAddress().getStreet() + "+" + account.getAddress().getZipCode() + "&limit=1";
		System.out.println("OLD: " + url);

		//delete the space in the url and replace it with +
		url = url.replaceAll(" ", "+");
		System.out.println("NEW: " + url);

		//result is a json
		String result = restTemplate.getForObject(url, String.class);
		//print result
		System.out.println("JSON result :" + result);

		//first verify if contains "features"
		assert result != null;
		if (result.contains("features"))
		{
			//second verify if score > 0,7
			System.out.println("Score : " + result.substring(result.indexOf("score") + 7, result.indexOf("score") + 10));
			if (Double.parseDouble(result.substring(result.indexOf("score") + 7, result.indexOf("score") + 10)) > 0.7)
			{
				//if score > 0,7 then it's ok, save the account
				repository.save(mapperNoBank.accountDtoToAccount(account));
				return account;
			}
			//send error and don't save the address
			else throw new ApiException("Score too low, try to be more precise", HttpStatus.NOT_ACCEPTABLE);
		} else
		{
			//send error because no features found
			throw new ApiException("Invalid address", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	//delete account by id
	public Account delAccount (int id)
	{

		Account account = repository.findById(id).orElse(null);
		System.out.println(account);
		if (account != null)
		{
			repository.delete(account);
		}
		else {
			throw new ApiException("Account not found", HttpStatus.NOT_FOUND);
		}
		return account;
	}

	//delete all accounts
	public void delAllAccounts ()
	{
		repository.deleteAll();
	}

	//get all accounts
	public List<AccountDto> getAllAccount ()
	{
		return repository.findAll().stream().map(mapper::accountToAccountDto).toList();
	}

	public Account getAccountById (int id)
	{
		return repository.findById(id).orElse(null);
	}
}
