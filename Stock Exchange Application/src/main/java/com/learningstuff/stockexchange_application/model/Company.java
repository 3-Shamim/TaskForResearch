package com.learningstuff.stockexchange_application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Company {

    @Id
    @Column(unique = true)
    private String tradingCode;
    private String companyName;
    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CompanyTradingHistory> companyTradingHistories;

    public Company(String tradingCode, String companyName) {
        this.tradingCode = tradingCode;
        this.companyName = companyName;
    }
}
