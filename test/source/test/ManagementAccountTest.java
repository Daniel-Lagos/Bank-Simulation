package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.Account;
import logic.ETypeAccount;
import logic.ManagementAccount;

public class ManagementAccountTest {

	ManagementAccount mngAccount;
	Account account;
	
	public void setupOne() throws Exception{
		mngAccount=new ManagementAccount();
		account=new Account();
		mngAccount.addAccount("12345", 200_000, ETypeAccount.CURRENT);
		mngAccount.addAccount("67890", 25_000, ETypeAccount.DEPOSIT);
	
	}
	
	@Test
	public void testAddAccount() throws Exception{
		setupOne();
		assertFalse(mngAccount.addAccount("12345", 200_000, ETypeAccount.CURRENT));
		assertFalse(mngAccount.addAccount("67890", 25_000, ETypeAccount.DEPOSIT));
		assertTrue(mngAccount.addAccount("98432", 450_000, ETypeAccount.CURRENT));
		assertTrue(mngAccount.addAccount("23461", 1_250_000, ETypeAccount.DEPOSIT));
	}
	
	@Test
	public void testFindAccount() throws Exception{
		setupOne();
		assertNotNull(mngAccount.findAccount("12345"));
		assertNotNull(mngAccount.findAccount("67890"));
		assertNull(mngAccount.findAccount("98432"));
		assertNull(mngAccount.findAccount("99574"));
	}
	
	@Test
	public void testDeposity() throws Exception{
		setupOne();
		mngAccount.deposity("12345", 50_000);
		assertEquals(250_000, mngAccount.findAccount("12345").getResidue(),0.1);
		mngAccount.deposity("67890", 1_000_000);
		assertEquals(1_025_000, mngAccount.findAccount("67890").getResidue(),0.1);
		mngAccount.addAccount("32567", 300_000, ETypeAccount.CURRENT);
		mngAccount.deposity("32567", 20_000);
		assertNotEquals(345_000,mngAccount.findAccount("32567").getResidue());
		assertEquals(320_000,mngAccount.findAccount("32567").getResidue(),0.1);
	}
	
	@Test
	public void testRetirement() throws Exception{
		setupOne();
		assertTrue(mngAccount.retirement("12345", 100_000));
		assertTrue(mngAccount.retirement("67890", 5_000));
		assertEquals(true, mngAccount.retirement("12345",200_000));
	}
}