package com.accounts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="accounts")
public class Accounts {
 
    @Id
    private String id; 
     
    private String accountId;
    private Double initialBalance;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Double getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}
	
}
