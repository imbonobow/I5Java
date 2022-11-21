package com.rioc.ws.services.account;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;

import java.util.List;

public interface iAccountService
{
	public AccountDto postAccount (AccountDto account);
	public Account delAccount (int id);
	public List<Account> getAllAccount ();
	public Account getAccountById (int id);
	public void delAllAccounts ();
}
