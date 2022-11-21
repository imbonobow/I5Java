package com.rioc.ws.controllers;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.BankDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.rioc.ws.services.account.iAccountService;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController
{
	private iAccountService service;

	public AccountController(iAccountService service)
	{
		super();
		this.service = service;
	}

	@PostMapping("/accounts")
	public ResponseEntity<AccountDto> postAccount (@RequestBody @Valid AccountDto account, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()){
			throw new ApiException("Invalid account", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(service.postAccount(account), HttpStatus.CREATED);
	}

	@DeleteMapping("/accounts/{id}")
	public Account delAccount (@PathVariable int id)
	{
		return service.delAccount(id);
	}
	@GetMapping("/accounts")
	public List<Account> getAllAccounts ()
	{
		return service.getAllAccount();
	}
	@GetMapping("/accounts/{id}")
	public Account getAccountById (@PathVariable int id)
	{
		return service.getAccountById(id);
	}
	@DeleteMapping("/accounts")
	public void delAllAccounts ()
	{
		service.delAllAccounts();
	}



}
