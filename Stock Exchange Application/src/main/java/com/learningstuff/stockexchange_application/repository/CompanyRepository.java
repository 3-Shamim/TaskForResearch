package com.learningstuff.stockexchange_application.repository;

import com.learningstuff.stockexchange_application.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    List<Company> findCompaniesByTradingCodeLikeOrCompanyNameLike(String name, String tradingCode);
}
