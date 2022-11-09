package com.estocks.estockscommandapi.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estocks.estockscommandapi.exception.InvalidInputException;
import com.estocks.estockscommandapi.model.Company;
import com.estocks.estockscommandapi.service.CompanyCommandService;


@RestController
@RequestMapping("/estocks/command/api")
public class CompanyCommandController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyCommandController.class.getName());
	
	@Autowired
	CompanyCommandService companyCommandService;
	
	@Autowired
	EventStore eventStore;
	
	@PostMapping("/company/register")
	public CompletableFuture<Object> registerCompany(@RequestBody Company company) throws InvalidInputException {
		LOGGER.info("Registering a Company");
		validateInput(company);
		List<Object> companyEventList = eventStore.readEvents(company.getCompanyCode()).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
		if(company.getTurnover() < 100000000) {
			throw new InvalidInputException("We are currently not taking registrations for companies with turnover less than 10Cr. We would like to see you soon in future.");
		} else if (!companyEventList.isEmpty()) {
			throw new InvalidInputException("Company Code is already taken. Let's try another code.");
		}
		return companyCommandService.registerCompany(company).thenApply(result -> new ResponseEntity<String>(result, HttpStatus.CREATED));
	}

	@GetMapping("/stock/add/{companyCode}/{stockPrice}")
	public CompletableFuture<String> addStockPrice(@PathVariable String companyCode, @PathVariable Float stockPrice) {
		LOGGER.info("Adding stock price for a company");
		return companyCommandService.addStockPrice(companyCode, stockPrice);
	}

	@DeleteMapping("/company/delete/{companyCode}")
	public CompletableFuture<Object> deleteCompany(@PathVariable String companyCode) {
		LOGGER.info("Deleting a company");
		return companyCommandService.deleteCompany(companyCode).thenApply(result -> new ResponseEntity<String>(result, HttpStatus.OK));
	}
	
	@GetMapping("/events/{id}")
	public List<Object> getEvents(@PathVariable String id) {
		LOGGER.info("Fetching events for a company");
		return eventStore.readEvents(id).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}
	
	void validateInput(Company company) throws InvalidInputException {
		LOGGER.info("Validating user input for company registration");
		if (company.getCompanyCode() == null || company.getCompanyCode().isBlank())
			throw new InvalidInputException("Company Code is required for your registration");
		if (company.getCompanyName() == null || company.getCompanyName().isBlank())
			throw new InvalidInputException("Company Name is required for your registration");
		if (company.getCompanyCeo() == null || company.getCompanyCeo().isBlank())
			throw new InvalidInputException("CEO Name is required for your registration");
		if (company.getTurnover() == null)
			throw new InvalidInputException("Company Turnover is required for your registration");
		if (company.getWebsite() == null || company.getWebsite().isBlank())
			throw new InvalidInputException("Company Website is required for your registration");
		if (company.getStockExchange() == null || company.getStockExchange().isBlank())
			throw new InvalidInputException("Stock Exchange is required for your registration");
	}

}
