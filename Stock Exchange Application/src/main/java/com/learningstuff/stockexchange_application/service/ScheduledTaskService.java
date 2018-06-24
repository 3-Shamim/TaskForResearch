package com.learningstuff.stockexchange_application.service;

import com.learningstuff.stockexchange_application.dataProvider.DataProviderFromURL;
import com.learningstuff.stockexchange_application.dataProvider.DataProviderFromURL_Implementation;
import com.learningstuff.stockexchange_application.model.Company;
import com.learningstuff.stockexchange_application.model.CompanyTradingHistory;
import com.learningstuff.stockexchange_application.model.CompanyTradingHistoryForURLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTaskService {

    @Autowired
    private CompanyTradingHistoryService companyTradingHistoryService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TextFileServiceForDateHandling dateHandling;

    @Autowired
    private DataConversionAndDateHandlingService dataConversionAndDateHandlingService;

    private DataProviderFromURL dataProviderFromURL = new DataProviderFromURL_Implementation();

    private Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        String previousDate = dateHandling.getPreviousDateFromFile();
        String currentDate = dataProviderFromURL.getLatestDateAndTimeFromURL();

        LocalDateTime previousLocalDateTime = dataConversionAndDateHandlingService.getJustDateAndTime(previousDate);
        LocalDateTime currentLocalDateTime = dataConversionAndDateHandlingService.getJustDateAndTime(currentDate);

        logger.info("Previous DateAndTime -> " + previousLocalDateTime + " || " + "Current DateAndTime -> " + currentLocalDateTime);

        if (!previousLocalDateTime.isEqual(currentLocalDateTime)) {
            addNewData(currentLocalDateTime);
            dateHandling.saveCurrentDateIntoFile(currentDate); // Writing date and time into file
        }

        try {
            TimeUnit.MINUTES.sleep(10); // Delay time for scheduler
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void addNewData(LocalDateTime date) {

        List<CompanyTradingHistoryForURLService> allCompanyTradingHistory = dataProviderFromURL.getAllCompanyInfoFromURL(); // Get all company information from url

        for (CompanyTradingHistoryForURLService history : allCompanyTradingHistory) {
            Company company = companyService.findByCode(history.getTradingCode());
            CompanyTradingHistory companyTradingHistory = new CompanyTradingHistory(
                    history.getLtp(),
                    history.getHigh(),
                    history.getLow(),
                    history.getClosep(),
                    history.getYcp(),
                    history.get_change(),
                    history.getTrade(),
                    history.getValue(),
                    history.getVolume(),
                    date
            );
            companyTradingHistoryService.addCompanyTradingHistory(company, companyTradingHistory); // Save company information with company details
        }
    }


}
