package com.learningstuff.stockexchange_application.repository;

import com.learningstuff.stockexchange_application.model.CompanyTradingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompanyTradingHistoryRepository extends JpaRepository<CompanyTradingHistory, Integer>{

    List<CompanyTradingHistory> findCompanyTradingHistoriesByCompanyTradingCode(String tradingCode);

    List<CompanyTradingHistory> findCompanyTradingHistoriesByDateTimeAndCompanyTradingCodeLike(LocalDateTime date, String tradingCode);
}
