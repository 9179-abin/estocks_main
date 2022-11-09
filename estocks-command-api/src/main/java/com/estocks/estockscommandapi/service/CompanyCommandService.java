package com.estocks.estockscommandapi.service;

import java.util.concurrent.CompletableFuture;

import com.estocks.estockscommandapi.model.Company;

public interface CompanyCommandService {
	
	public CompletableFuture<String> registerCompany(Company company);
    public CompletableFuture<String> addStockPrice(String companyCode, Float stockPrice);
    public CompletableFuture<String> deleteCompany(String companyCode);

}
