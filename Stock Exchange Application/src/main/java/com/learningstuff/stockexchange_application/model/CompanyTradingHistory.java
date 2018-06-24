package com.learningstuff.stockexchange_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CompanyTradingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private int id;
    private double ltp;
    private double high;
    private double low;
    private double closep;
    private double ycp;
    private String _change;
    private double trade;
    private double value;
    private double volume;
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_tradingCode")
    private Company company;

    public CompanyTradingHistory(double ltp, double high, double low, double closep, double ycp, String _change, double trade, double value, double volume, LocalDateTime dataTime) {
        this.ltp = ltp;
        this.high = high;
        this.low = low;
        this.closep = closep;
        this.ycp = ycp;
        this._change = _change;
        this.trade = trade;
        this.value = value;
        this.volume = volume;
        this.dateTime = dataTime;
    }
}
