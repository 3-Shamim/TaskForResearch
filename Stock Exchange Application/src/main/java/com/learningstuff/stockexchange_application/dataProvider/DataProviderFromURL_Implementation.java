package com.learningstuff.stockexchange_application.dataProvider;

import com.learningstuff.stockexchange_application.model.CompanyTradingHistoryForURLService;
import com.learningstuff.stockexchange_application.service.DataConversionAndDateHandlingService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderFromURL_Implementation implements DataProviderFromURL {

    private DataConversionAndDateHandlingService dataConversionAndDateHandlingService = new DataConversionAndDateHandlingService();

    private List<CompanyTradingHistoryForURLService> allCompanyInfoFromURL;


    @Override
    public String getLatestDateAndTimeFromURL() {
        return parseDateAndTimeFromURL();
    }

    @Override
    public List<CompanyTradingHistoryForURLService> getAllCompanyInfoFromURL() {
        return parseAllCompanyTradingHistoryFromURL();
    }

    private String parseDateAndTimeFromURL() {
        Elements rows = null;
        try {
            Document doc = Jsoup.connect("http://www.dsebd.org/latest_share_price_scroll_l.php").get();
            rows = doc.select("b");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows.get(10).text().trim();
    }



    public List<CompanyTradingHistoryForURLService> parseAllCompanyTradingHistoryFromURL() {

        try {
            allCompanyInfoFromURL = new ArrayList<>();
            Document doc = Jsoup.connect("http://www.dsebd.org/latest_share_price_all.php").get();
            Elements rows = doc.select("td");

            for (int i = 11; i < rows.size(); i += 11) {

                CompanyTradingHistoryForURLService companyTradingHistoryForURLService = new CompanyTradingHistoryForURLService(
                        rows.get(i+1).text(),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+2).text().trim()),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+3).text().trim()),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+4).text().trim()),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+5).text().trim()),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+6).text().trim()),
                        rows.get(i+7).text().trim(),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+8).text().trim()),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+9).text().trim()),
                        dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(rows.get(i+10).text().trim())
                );

                allCompanyInfoFromURL.add(companyTradingHistoryForURLService);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allCompanyInfoFromURL;
    }
}

