package com.learningstuff.stockexchange_application.service;

import com.learningstuff.stockexchange_application.model.Company;
import com.learningstuff.stockexchange_application.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies()
    {
        return companyRepository.findAll();
    }

    public void createCompany(Company company)
    {
        companyRepository.save(company);
    }

    public void saveAllCompanies(List<Company> companies) {
        companyRepository.saveAll(companies);
    }

    public Company findByCode(String code) {
        return companyRepository.getOne(code);
    }

    public List<Company> getAllCompaniesByName(String name) {
        return companyRepository.findCompaniesByTradingCodeLikeOrCompanyNameLike("%" +name+ "%", "%" +name+ "%");

    }
}
