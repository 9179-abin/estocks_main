package com.estocks.estockscommandapi.model;

public class Company {

	private String id;

	private String companyCode;
	private String companyName;
	private String companyCeo;
	private Integer turnover;
	private String website;
	private String stockExchange;
	private Float stockPrice;

	public Company(String companyCode, String companyName, String companyCeo, Integer turnover, String website,
			String stockExchange, Float stockPrice) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.turnover = turnover;
		this.website = website;
		this.stockExchange = stockExchange;
		this.stockPrice = stockPrice;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCeo() {
		return companyCeo;
	}

	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}

	public Integer getTurnover() {
		return turnover;
	}

	public void setTurnover(Integer turnover) {
		this.turnover = turnover;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public Float getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Float stockPrice) {
		this.stockPrice = stockPrice;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyCode=" + companyCode + ", companyName=" + companyName + ", companyCeo="
				+ companyCeo + ", turnover=" + turnover + ", website=" + website + ", stockExchange=" + stockExchange
				+ ", stockPrice=" + stockPrice + "]";
	}
	
}
