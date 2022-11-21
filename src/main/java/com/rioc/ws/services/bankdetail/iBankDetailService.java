package com.rioc.ws.services.bankdetail;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.BankDetailDto;

public interface iBankDetailService
{
	public BankDetailDto postBankDetail (BankDetailDto bankDetail);
	public BankDetail getBankDetailById(int id);
	public BankDetail delBankDetailById(int id);
}
