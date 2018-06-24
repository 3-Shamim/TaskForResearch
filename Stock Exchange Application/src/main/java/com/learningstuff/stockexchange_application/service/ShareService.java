package com.learningstuff.stockexchange_application.service;

import com.learningstuff.stockexchange_application.model.Share;
import com.learningstuff.stockexchange_application.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareService {

    @Autowired
    private ShareRepository shareRepository;

    public void saveShare(Share share)
    {
        shareRepository.save(share);
    }

}
