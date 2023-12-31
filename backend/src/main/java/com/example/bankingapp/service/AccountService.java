package com.example.bankingapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapp.dao.AccountRepository;
import com.example.bankingapp.dao.AddressRepository;
import com.example.bankingapp.dao.CustomerRepository;
import com.example.bankingapp.dao.OccupationRepository;
import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.model.Account;
import com.example.bankingapp.model.AccountData;
import com.example.bankingapp.model.Address;
import com.example.bankingapp.model.ChangePassword;
import com.example.bankingapp.model.Customer;
import com.example.bankingapp.model.Occupation;

@Service
public class AccountService {
	
	@Autowired
	CustService custService;

	@Autowired
	AccountRepository accRepo;
	
	@Autowired
	CustomerRepository userRepo;
	
	@Autowired
	AddressRepository addressRepo;

	@Autowired
	OccupationRepository occupationRepo;

	public String createAccount(AccountData accountData, String username) {

		try {
			Customer u = userRepo.findById(username).get();
			Account account = accountData.getAccount();
			Occupation occupation = accountData.getOccupation();
			List<Address> address = accountData.getAddress();

			account.setCustomer(u);
			occupation.setCustomer(u);
			for (Address a : address)
				a.setCustomer(u);

			occupationRepo.save(occupation);
			addressRepo.saveAll(address);
			accRepo.save(account);

			return "Account Created";
		} catch (Error e) {
			return "Error creating account: " + e.toString();
		}
	}

	
	
	public String updatePasswordByAccountNo(String accountNo,ChangePassword pass) throws ResourceNotFoundException{
		Optional<Account> acc1=accRepo.findById(accountNo);
		if(!acc1.isPresent()) {
			throw new ResourceNotFoundException("Account not found.");
		}
		Account acc=acc1.get();
		Customer cust=acc.getCustomer();
		String custId=cust.getCustId();
		String s=custService.updatePassword(custId,pass);
		return s;
	}



	public String suspendAccount(String accNo) throws ResourceNotFoundException{
		Optional<Account> acc1=accRepo.findById(accNo);
		if(!acc1.isPresent()) {
			throw new ResourceNotFoundException("Account not found.");
		}
		Account acc=acc1.get();
		if(acc.getDateClosed()!=null) throw new ResourceNotFoundException("Account already suspended.");
		int rows=accRepo.updateDateClosed(LocalDate.now(),accNo);
		if(rows>0) return "Account Suspended.";
		throw new ResourceNotFoundException("Error.");
	}
	public Integer checkBalanceByAccNo(String accNo) throws ResourceNotFoundException {
		Optional<Account> acc1=accRepo.findById(accNo);
		if(!acc1.isPresent()) {
			throw new ResourceNotFoundException("Account not found.");
		}
		int z=accRepo.getBalance(accNo);
		return z;
}
  
  public String activateAccount(String accNo) throws ResourceNotFoundException{
	  Optional<Account> acc1=accRepo.findById(accNo);
		if(!acc1.isPresent()) {
			throw new ResourceNotFoundException("Account not found.");
		}
	  	Account acc=accRepo.findById(accNo).get();
	  	if(acc.getDateClosed()==null) throw new ResourceNotFoundException("Account already activated.");
		int rows=accRepo.updateDateClosed(null,accNo);
		if(rows>0) return "Account Activated.";

		throw new ResourceNotFoundException("Error");
	}
  
	public String getCustomerName(String accNo) throws ResourceNotFoundException{
		Optional<Account> acc1=accRepo.findById(accNo);
		if(!acc1.isPresent()) {
			throw new ResourceNotFoundException("Account not found.");
		}
		List<String> custName = accRepo.getCustomerName(accNo);
		String name = String.join("",custName);
		name = name.replace(",", " ");
		return name;
	}
}





