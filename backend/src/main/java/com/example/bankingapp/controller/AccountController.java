package com.example.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.model.AccountData;
import com.example.bankingapp.model.ChangePassword;
import com.example.bankingapp.service.AccountService;

@RestController
@CrossOrigin("*")
public class AccountController {

	@Autowired
	AccountService accService;

	@PostMapping("/createAccount/{uname}")
	public String createAccount(@RequestBody AccountData account, @PathVariable("uname") String username) {
		String res = "";
		res = accService.createAccount(account, username);

		return res;

	}
	
	
	
	@PutMapping("/updatePasswordByAccountNo/{accountNo}")
	public String updatePasswordByAccountNo(@PathVariable ("accountNo") String accountNo,@RequestBody ChangePassword pass) throws ResourceNotFoundException{
		return accService.updatePasswordByAccountNo(accountNo,pass);
	}
	
	@PutMapping("/suspendAccount/{accountNo}")
	public String suspendAccount(@PathVariable ("accountNo") String accNo) throws ResourceNotFoundException{
		return accService.suspendAccount(accNo);
	}
	
	@GetMapping("/checkBalanceByAccNo/{accountNo}")
	public Integer checkBalanceByAccNo(@PathVariable("accountNo") String accNo) throws ResourceNotFoundException {
		return accService.checkBalanceByAccNo(accNo);
	}
	
	@GetMapping("/getCustomerName/{accountNo}")
	public String getCustomerName(@PathVariable("accountNo") String accNo) throws ResourceNotFoundException {
		return accService.getCustomerName(accNo);
	}
	
	
	@PutMapping("/activateAccount/{accountNo}")
	public String activateAccount(@PathVariable ("accountNo") String accNo) throws ResourceNotFoundException{
		return accService.activateAccount(accNo);
	}
}




