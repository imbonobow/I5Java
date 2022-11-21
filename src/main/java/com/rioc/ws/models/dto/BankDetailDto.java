package com.rioc.ws.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BankDetailDto
{
	private String iban;
	//link to account
	int account_id;
}
