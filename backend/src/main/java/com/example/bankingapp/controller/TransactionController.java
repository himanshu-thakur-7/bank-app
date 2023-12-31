package com.example.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.model.Transaction;
import com.example.bankingapp.model.TransactionModel;
import com.example.bankingapp.service.TransactionService;

@RestController
@CrossOrigin("*")
public class TransactionController {
	@Autowired
	TransactionService transSer;
	@PostMapping("/transaction")
	public String transaction(@RequestBody TransactionModel trans) throws ResourceNotFoundException{
		String t=transSer.transaction(trans);
		return t;
	}
	
	@GetMapping("/fetchTransactions/{custId}")
	public List<Transaction>fetchTransactions(@PathVariable("custId") String uname) throws ResourceNotFoundException{
		List<Transaction> txnList=transSer.fetchTransactions(uname);
		return txnList;
	}
	@GetMapping("/fetchTransactionsByAccNo/{accountNo}")
	public List<Transaction>fetchTransactionsByAccNo(@PathVariable("accountNo") String accNo) throws ResourceNotFoundException{
		List<Transaction> txnList=transSer.fetchTransactionsByAccNo(accNo);
		return txnList;
	}
	
}
