package com.learningstuff.stockexchange_application.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Share {

    @Id
    private String tradingCode;
    private String companyName;
    private double faceValue;
    private LocalDateTime launchingDate;

    @OneToMany(mappedBy = "share", cascade = CascadeType.ALL)
    private List<SharePriceHistory> sharePriceHistoryList;

}
