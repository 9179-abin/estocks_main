package com.estocks.estockscommandapi.events;

import java.beans.ConstructorProperties;

public class CompanyDeletedEvent {

	private final String companyCode;

	@ConstructorProperties({"companyCode"})
	public CompanyDeletedEvent(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	@Override
	public String toString() {
		return "CompanyDeletedEvent [companyCode=" + companyCode + "]";
	}

}
