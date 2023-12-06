package b_Money;

import org.example.b_Money.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}

	@Test
	public void testAddRemoveTimedPayment() {
		// Test adding and removing a timed payment
		testAccount.addTimedPayment("payment1", 2, 1, new Money(1000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// Test that timed payments trigger properly
		testAccount.addTimedPayment("payment2", 1, 1, new Money(500, SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
	}

	@Test
	public void testAddWithdraw() {
		// Test depositing and withdrawing money from the account
		testAccount.deposit(new Money(2000000, SEK));
		assertEquals(12000000, testAccount.getBalance().getAmount().intValue());
		testAccount.withdraw(new Money(5000000, SEK));
		assertEquals(7000000, testAccount.getBalance().getAmount().intValue());
	}

	@Test
	public void testGetBalance() {
		// Test getting the balance of the account
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
	}
}
