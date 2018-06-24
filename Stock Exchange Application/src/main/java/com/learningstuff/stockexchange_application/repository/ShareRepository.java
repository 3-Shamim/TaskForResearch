package com.learningstuff.stockexchange_application.repository;

import com.learningstuff.stockexchange_application.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, String>{

}
