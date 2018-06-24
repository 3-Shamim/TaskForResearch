package com.learningstuff.stockexchange_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyTradingHistoryForURLService {
    private String tradingCode;
    private double ltp;
    private double high;
    private double low;
    private double closep;
    private double ycp;
    private String _change;
    private double trade;
    private double value;
    private double volume;
}
