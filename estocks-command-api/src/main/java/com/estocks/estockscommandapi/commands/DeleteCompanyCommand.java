package com.estocks.estockscommandapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteCompanyCommand {
	
	@TargetAggregateIdentifier
    private final String companyCode;
	
	public DeleteCompanyCommand(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	@Override
	public String toString() {
		return "DeleteCompanyCommand [companyCode=" + companyCode + "]";
	}

}
