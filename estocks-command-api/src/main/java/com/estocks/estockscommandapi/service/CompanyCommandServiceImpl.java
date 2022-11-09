package com.estocks.estockscommandapi.service;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estocks.estockscommandapi.commands.AddStockPriceCommand;
import com.estocks.estockscommandapi.commands.DeleteCompanyCommand;
import com.estocks.estockscommandapi.commands.RegisterCompanyCommand;
import com.estocks.estockscommandapi.events.CompanyDeletedEvent;
import com.estocks.estockscommandapi.events.CompanyRegisteredEvent;
import com.estocks.estockscommandapi.events.StockPriceAddedEvent;
import com.estocks.estockscommandapi.model.Company;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {
	
	private final CommandGateway commandGateway;
	
	@Autowired
	CompanyEventProducer eventProducer;
	
	public CompanyCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

	@Override
	public CompletableFuture<String> registerCompany(Company company) {
		CompletableFuture<String> completableEvent = commandGateway.send(new RegisterCompanyCommand(company.getCompanyCode(), company.getCompanyName(), company.getCompanyCeo(), company.getTurnover(), company.getWebsite(), company.getStockExchange(), company.getStockPrice()));
		eventProducer.produce("CompanyRegisteredEvent", new CompanyRegisteredEvent(company.getCompanyCode(), company.getCompanyName(),
				company.getCompanyCeo(), company.getTurnover(), company.getWebsite(), company.getStockExchange(),
				company.getStockPrice()));
		return completableEvent;
	}

	@Override
	public CompletableFuture<String> addStockPrice(String companyCode, Float stockPrice) {
		CompletableFuture<String> completableEvent = commandGateway.send(new AddStockPriceCommand(companyCode, stockPrice));
		eventProducer.produceStockPriceUpdate("StockPriceAddedEvent", new StockPriceAddedEvent(companyCode, stockPrice));
		return completableEvent;
	}

	@Override
	public CompletableFuture<String> deleteCompany(String companyCode) {
		CompletableFuture<String> completableEvent = commandGateway.send(new DeleteCompanyCommand(companyCode));
		eventProducer.produceDelete("CompanyDeletedEvent", new CompanyDeletedEvent(companyCode));
		return completableEvent;
	}

}
