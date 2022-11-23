package com.rioc.ws.mappers;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.AccountNoBankDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface iAccountNoBankMapper
{
	AccountNoBankDto accountToAccountDto (Account account);
	Account accountDtoToAccount (AccountNoBankDto account);
}
