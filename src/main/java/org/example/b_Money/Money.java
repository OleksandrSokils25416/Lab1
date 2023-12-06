package org.example.b_Money;

public class Money implements Comparable<Money> {
	private int amount;
	private Currency currency;

	public Money(Integer amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Integer getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String toString() {
		// Convert the amount to a decimal representation based on the currency's decimal places.
		double decimalAmount = amount / 100.0; // Assuming the amount is in cents.
		return String.format("%.2f %s", decimalAmount, currency.getName());
	}

	public Integer universalValue() {
		// Convert the amount to the "universal currency" using the currency's exchange rate.
		return currency.universalValue(amount);
	}

	public Boolean equals(Money other) {
		// Check if the universal values of two Money objects are equal.
		return this.universalValue().equals(other.universalValue());
	}

	public Money add(Money other) {
		// Convert the other Money to this currency and then add the amounts.
		int convertedAmount = other.getCurrency().valueInThisCurrency(other.getAmount(), this.currency);
		return new Money(this.amount + convertedAmount, this.currency);
	}

	public Money sub(Money other) {
		// Convert the other Money to this currency and then subtract the amounts.
		int convertedAmount = other.getCurrency().valueInThisCurrency(other.getAmount(), this.currency);
		return new Money(this.amount - convertedAmount, this.currency);
	}

	public Boolean isZero() {
		return this.amount == 0;
	}

	public Money negate() {
		// Negate the amount and create a new Money object.
		return new Money(-this.amount, this.currency);
	}

	public int compareTo(Money other) {
		// Compare based on universal values.
		return this.universalValue().compareTo(other.universalValue());
	}
}
