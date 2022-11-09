package com.estocks.estockscommandapi.events;

import java.beans.ConstructorProperties;

public class StockPriceAddedEvent {
	
	private final String companyCode;
	private final Float stockPrice;
	
	@ConstructorProperties({"companyCode", "stockPrice"})
	public StockPriceAddedEvent(String companyCode, Float stockPrice) {
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
		return "StockPriceAddedEvent [companyCode=" + companyCode + ", stockPrice=" + stockPrice + "]";
	}


}
