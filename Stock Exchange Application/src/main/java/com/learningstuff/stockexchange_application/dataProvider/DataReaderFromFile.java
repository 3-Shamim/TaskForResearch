package com.learningstuff.stockexchange_application.dataProvider;

import com.learningstuff.stockexchange_application.model.Company;
import com.learningstuff.stockexchange_application.model.CompanyTradingHistoryForURLService;

import java.util.List;

public interface DataReaderFromFile {
    List<Company> getAllCompaniesFromFile();

    List<CompanyTradingHistoryForURLService> getAllCompaniesTradingHistoryFromFile();
}
