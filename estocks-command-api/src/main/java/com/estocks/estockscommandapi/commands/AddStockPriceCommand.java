package com.estocks.estockscommandapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AddStockPriceCommand {
	
	@TargetAggregateIdentifier
	private final String companyCode;
	private final Float stockPrice;
	
	public AddStockPriceCommand(String companyCode, Float stockPrice) {
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public Float getStockPrice() {
		return stockPrice;
	}

	@Override
	public String toString() {
		return "AddStockPriceCommand [companyCode=" + companyCode + ", stockPrice=" + stockPrice + "]";
	}

}
