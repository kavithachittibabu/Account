package com.accounts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountsRepo extends MongoRepository<Accounts, String> {
 
}
