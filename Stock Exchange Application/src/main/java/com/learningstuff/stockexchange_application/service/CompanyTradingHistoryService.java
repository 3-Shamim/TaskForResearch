package com.learningstuff.stockexchange_application.service;

import com.learningstuff.stockexchange_application.model.Company;
import com.learningstuff.stockexchange_application.model.CompanyTradingHistory;
import com.learningstuff.stockexchange_application.repository.CompanyTradingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyTradingHistoryService {
    @Autowired
    private CompanyTradingHistoryRepository companyTradingHistoryRepository;

    public List<CompanyTradingHistory> getAllCompanyTradingHistory()
    {
        return companyTradingHistoryRepository.findAll();
    }

    public void addCompanyTradingHistory(Company company, CompanyTradingHistory companyTradingHistory)
    {
        companyTradingHistory.setCompany(company);
        companyTradingHistoryRepository.save(companyTradingHistory);
    }

    public List<CompanyTradingHistory> findByLatestDate(LocalDateTime latestDate, String tradingCode) {

        return companyTradingHistoryRepository.findCompanyTradingHistoriesByDateTimeAndCompanyTradingCodeLike(latestDate, "%" + tradingCode + "%");
    }

    public List<CompanyTradingHistory> findByTradingCode(String tradingCode) {
        return companyTradingHistoryRepository.findCompanyTradingHistoriesByCompanyTradingCode(tradingCode);
    }
}
