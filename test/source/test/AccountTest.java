package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.Account;

public class AccountTest {
	
	Account account;
	
	public void setupOne() throws Exception{
		account =new Account();  
		account.deposit(200_000);
	}
	
	@Test
	public void testDeposit() throws Exception{
		setupOne();
		account.deposit(45_000);
		assertEquals(245_000,account.getResidue(),0.1);
	}
	
	@Test
	public void testRetirement() throws Exception{  
		setupOne();
		assertTrue(account.retirement(220_000));
	}

}
