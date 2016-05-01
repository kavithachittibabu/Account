package com.accounts;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fakemongo.Fongo;
import com.mongodb.FongoDB;
import com.mongodb.Mongo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AccountsBoot.class)
@WebIntegrationTest
public class AccountTests{
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private FongoDB db;
	
	@Before
	public void setUp() {
	    db = (FongoDB) new Fongo("test").getDB("test");
	    db.getMongo();
	}

	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = { AccountsRepo.class })
	static class MongoConfiguration extends AbstractMongoConfiguration {

		@Override
		protected String getDatabaseName() {
			return "testdb";
		}

		@Override
		public Mongo mongo() {
			return new Fongo("testdb").getMongo();
		}

		@Override
		protected String getMappingBasePackage() {
			return "com.spring.data.mongo";
		}
	}
	@Test
	public void testAccountNewService() throws Exception {
		
		RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        rt.getMessageConverters().add(new StringHttpMessageConverter());

		Accounts accountToSave = new Accounts();
		accountToSave.setInitialBalance(500.00);
		accountToSave.setAccountId("123");
		Accounts accountnew = rt.postForObject("http://localhost:8888/accounts/new", accountToSave, Accounts.class);
		
		assertEquals("Account id didnt match", "123", accountnew.getAccountId());
		assertNotNull("Id should not be null", accountnew.getId());
	}
}