package com.rioc.ws.services.account;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.AccountNoBankDto;

import java.util.List;

public interface iAccountService
{
	public AccountNoBankDto postAccount (AccountNoBankDto account);
	public Account delAccount (int id);
	public List<AccountDto> getAllAccount ();
	public Account getAccountById (int id);
	public void delAllAccounts ();
}
