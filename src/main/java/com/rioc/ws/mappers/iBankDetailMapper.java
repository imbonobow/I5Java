package com.rioc.ws.mappers;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.BankDetailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface iBankDetailMapper
{
	BankDetailDto toDto (BankDetail bankDetail);
	BankDetail toDao (BankDetailDto bankDetailDao);

	BankDetailDto bankDetailToBankDetailDto (BankDetail bankDetail);
	BankDetail bankDetailDtoToBankDetail (BankDetailDto bankDetail);

}
