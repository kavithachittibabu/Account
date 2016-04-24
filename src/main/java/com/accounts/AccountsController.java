package com.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
@RestController
@RequestMapping("/accounts")
public class AccountsController {
     
    @Autowired
    AccountsRepo employeeRepository;
 
    @RequestMapping(method = RequestMethod.POST, value="/new")
    public Accounts create(@RequestBody Accounts account){
         
        Accounts result = employeeRepository.save(account);
        return result;
    }
    
    /*@RequestMapping(method = RequestMethod.POST, value="/update/{amount}/{type}")
    public Accounts debitAccount(@RequestBody Accounts account, 
    		@PathVariable String amount,@PathVariable String type){
         
        Accounts result = employeeRepository.save(account);
        return result;
    }*/
     
    @RequestMapping(method = RequestMethod.GET, value="/{accountno}")
    public Accounts get(@PathVariable String accountno){
        
    	List<Accounts> allAccounts= employeeRepository.findAll();
    	
    	if(allAccounts  != null && allAccounts.size() > 0){
    		for(Accounts account : allAccounts){
    			if(account.getAccountId().equals(accountno)){
    				return account;
    			}
    		}
    	}
    	return null;
    }
     
     
}