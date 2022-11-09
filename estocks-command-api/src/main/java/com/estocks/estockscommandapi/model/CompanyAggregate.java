package com.estocks.estockscommandapi.model;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import com.estocks.estockscommandapi.commands.AddStockPriceCommand;
import com.estocks.estockscommandapi.commands.DeleteCompanyCommand;
import com.estocks.estockscommandapi.commands.RegisterCompanyCommand;
import com.estocks.estockscommandapi.events.CompanyDeletedEvent;
import com.estocks.estockscommandapi.events.CompanyRegisteredEvent;
import com.estocks.estockscommandapi.events.StockPriceAddedEvent;
import com.estocks.estockscommandapi.service.CompanyEventProducer;

@Aggregate
public class CompanyAggregate {

	@AggregateIdentifier
	private String companyCode;
	@AggregateMember
	private String companyName;
	@AggregateMember
	private String companyCeo;
	@AggregateMember
	private Integer turnover;
	@AggregateMember
	private String website;
	@AggregateMember
	private String stockExchange;
	@AggregateMember
	private Float stockPrice;
	
	@Autowired
	CompanyEventProducer eventProducer;

	public CompanyAggregate() {
	}

	@CommandHandler
	public CompanyAggregate(RegisterCompanyCommand command) {
		AggregateLifecycle.apply(new CompanyRegisteredEvent(command.getCompanyCode(), command.getCompanyName(),
				command.getCompanyCeo(), command.getTurnover(), command.getWebsite(), command.getStockExchange(),
				command.getStockPrice()));
	}

	@CommandHandler
	public void handle(AddStockPriceCommand command) {
		AggregateLifecycle.apply(new StockPriceAddedEvent(command.getCompanyCode(), command.getStockPrice()));
	}

	@CommandHandler
	public void handle(DeleteCompanyCommand command) {
		AggregateLifecycle.apply(new CompanyDeletedEvent(command.getCompanyCode()));
	}

	@EventSourcingHandler
	public void on(CompanyRegisteredEvent event) {
		this.companyCode = event.getCompanyCode();
		this.companyName = event.getCompanyName();
		this.companyCeo = event.getCompanyCeo();
		this.turnover = event.getTurnover();
		this.website = event.getWebsite();
		this.stockExchange = event.getStockExchange();
		this.stockPrice = event.getStockPrice();
	}

	@EventSourcingHandler
	public void on(StockPriceAddedEvent event) {
		this.companyCode = event.getCompanyCode();
		this.stockPrice = event.getStockPrice();
	}

	@EventSourcingHandler
	public void on(CompanyDeletedEvent event) {
		this.companyCode = event.getCompanyCode();
		AggregateLifecycle.markDeleted();
		eventProducer.produceDelete(event.getClass().getSimpleName(), event);
	}

}
