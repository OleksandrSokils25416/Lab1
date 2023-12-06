package org.example.b_Money;

public class Currency {
	private String name;
	private Double rate;

	public Currency(String name, Double rate) {
		this.name = name;
		this.rate = rate;
	}

	public Integer universalValue(Integer amount) {
		// Implement the conversion of the given amount to the "universal currency"
		// using the exchange rate of this currency.
		return (int) Math.round(amount * rate);
	}

	public String getName() {
		return name;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		// Implement the ability to set a new exchange rate for this currency.
		this.rate = rate;
	}

	public Integer valueInThisCurrency(Integer amount, Currency otherCurrency) {
		// Implement the conversion of the given amount from another currency
		// to this currency using the exchange rates of both currencies.
		double convertedAmount = amount * (otherCurrency.getRate() / this.rate);
		return (int) Math.round(convertedAmount);
	}
}
