package com.learningstuff.stockexchange_application.service;

import com.learningstuff.stockexchange_application.model.Share;
import com.learningstuff.stockexchange_application.model.SharePriceHistory;
import com.learningstuff.stockexchange_application.repository.SharePriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharePriceHistoryService {

    @Autowired
    private SharePriceHistoryRepository sharePriceHistoryRepository;

    public void saveSharePriceHistory(SharePriceHistory sharePriceHistory, Share share)
    {
        sharePriceHistory.setShare(share);
        sharePriceHistoryRepository.save(sharePriceHistory);
    }

}
