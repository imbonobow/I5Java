package com.rioc.ws.controllers;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.models.dao.BankDetail;
import com.rioc.ws.models.dto.BankDetailDto;
import com.rioc.ws.services.bankdetail.iBankDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankDetailController
{
	private iBankDetailService service;
	public BankDetailController(iBankDetailService service)
	{
		super();
		this.service = service;
	}

	@PostMapping("/bankdetails")
	public ResponseEntity<BankDetailDto> postBankDetail (@RequestBody BankDetailDto bankDetail, BindingResult bindingResult)
	{
		return new ResponseEntity<>(service.postBankDetail(bankDetail), HttpStatus.CREATED);
	}

	@GetMapping("/bankdetails/{id}")
	public ResponseEntity<BankDetail> getBankDetailById (@PathVariable int id)
	{
		return new ResponseEntity<>(service.getBankDetailById(id), HttpStatus.OK);
	}

	@DeleteMapping("/bankdetails/{id}")
	public ResponseEntity<BankDetail> delBankDetailById (@PathVariable int id)
	{
		return new ResponseEntity<>(service.delBankDetailById(id), HttpStatus.OK);
	}
}
