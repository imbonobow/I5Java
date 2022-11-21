package com.rioc.ws.services.account;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.iAccountMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.repositories.iAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountService implements iAccountService
{
	private iAccountRepository repository;
	private iAccountMapper mapper;

	public AccountService (iAccountRepository repository, iAccountMapper mapper)
	{
		super();
		this.mapper = mapper;
		this.repository = repository;
	}

	//post account
	public AccountDto postAccount (AccountDto account)
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
				repository.save(mapper.accountDtoToAccount(account));
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
	public List<Account> getAllAccount ()
	{
		return repository.findAll();
	}

	//get Account by id
	public Account getAccountById (int id)
	{
		return repository.findById(id).orElse(null);
	}


}
