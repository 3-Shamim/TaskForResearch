package com.learningstuff.stockexchange_application.dataProvider;

import com.learningstuff.stockexchange_application.model.CompanyTradingHistoryForURLService;

import java.util.List;

public interface DataProviderFromURL {
     String getLatestDateAndTimeFromURL();

     List<CompanyTradingHistoryForURLService> getAllCompanyInfoFromURL();


}
