package com.learningstuff.stockexchange_application.repository;

import com.learningstuff.stockexchange_application.model.SharePriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharePriceHistoryRepository extends JpaRepository<SharePriceHistory, Integer>{
}
