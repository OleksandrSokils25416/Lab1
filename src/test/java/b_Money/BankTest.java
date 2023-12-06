package b_Money;

import static org.junit.Assert.*;

import org.example.b_Money.*;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		// Test opening an account
		SweBank.openAccount("Alice");
		assertTrue(SweBank.accountlist.containsKey("Alice"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		// Test depositing money to an account
		SweBank.deposit("Bob", new Money(500000, SEK));
		assertEquals(500000, SweBank.getBalance("Bob").intValue());
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		// Test withdrawing money from an account
		SweBank.withdraw("Ulrika", new Money(200000, SEK));
		assertEquals(-200000, SweBank.getBalance("Ulrika").intValue());
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		// Test getting the balance of an account
		assertEquals(0, SweBank.getBalance("Alice").intValue());
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		// Test transferring money between accounts
		SweBank.transfer("Bob", Nordea, "Bob", new Money(300000, SEK));
		assertEquals(300000, Nordea.getBalance("Bob").intValue());
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// Test adding a timed payment
		SweBank.addTimedPayment("Bob", "payment1", 2, 1, new Money(1000, SEK), Nordea, "Bob");
		assertTrue(SweBank.accountlist.get("Bob").timedPaymentExists("payment1"));
	}
}
