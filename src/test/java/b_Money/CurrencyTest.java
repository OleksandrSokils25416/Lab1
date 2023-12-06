package b_Money;

import static org.junit.Assert.*;

import org.example.b_Money.Currency;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}

	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0.001);
		assertEquals(0.20, DKK.getRate(), 0.001);
		assertEquals(1.5, EUR.getRate(), 0.001);
	}

	@Test
	public void testSetRate() {
		SEK.setRate(0.18);
		assertEquals(0.18, SEK.getRate(), 0.001);
	}

	@Test
	public void testGlobalValue() {
		assertEquals(Optional.of(1000), SEK.universalValue(100));
		assertEquals(Optional.of(1500), EUR.universalValue(100));
	}

	@Test
	public void testValueInThisCurrency() {
		assertEquals(Optional.of(1000), SEK.valueInThisCurrency(100, SEK));
		assertEquals(Optional.of(75), DKK.valueInThisCurrency(100, SEK));
	}
}
