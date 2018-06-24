package com.learningstuff.stockexchange_application.controller;

import com.learningstuff.stockexchange_application.model.CompanyTradingHistory;
import com.learningstuff.stockexchange_application.service.CompanyTradingHistoryService;
import com.learningstuff.stockexchange_application.service.CompanyService;
import com.learningstuff.stockexchange_application.service.DataConversionAndDateHandlingService;
import com.learningstuff.stockexchange_application.service.TextFileServiceForDateHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private CompanyTradingHistoryService companyTradingHistoryService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TextFileServiceForDateHandling dateHandling;

    @Autowired
    private DataConversionAndDateHandlingService dataConversionAndDateHandlingService;

    @GetMapping(value = "index")
    public ModelAndView indexView()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","Home");
        modelAndView.setViewName("view/Index");

        return modelAndView;
    }

    @GetMapping(value = "latestshareprice")
    public ModelAndView latestSharePriceView(@RequestParam(value = "tradingCode", defaultValue = "") String tradingCode)
    {
        ModelAndView modelAndView = new ModelAndView();

        String latestDate = dateHandling.getPreviousDateFromFile();

        LocalDateTime latestLocalDateTime = dataConversionAndDateHandlingService.getJustDateAndTime(latestDate);

        modelAndView.addObject("title", "Latest Share Price");
        modelAndView.addObject("latestSharePrice", "Latest Share Price on " + latestDate);
        modelAndView.addObject("sharePrices", companyTradingHistoryService.findByLatestDate(latestLocalDateTime, tradingCode));
        modelAndView.setViewName("view/Latestshareprice");

        return modelAndView;
    }

    @GetMapping(value = "chart/{tradingCode}")
    public ModelAndView getByTradingCode(@PathVariable(value = "tradingCode") String tradingCode)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Chart");
        modelAndView.addObject("tradingCode", tradingCode);
        modelAndView.addObject("companyName", companyService.findByCode(tradingCode).getCompanyName());
        modelAndView.setViewName("view/ChartByTradingCode");
        return modelAndView;
    }

    @GetMapping(value = "latestshareprice/{tradingCode}")
    @ResponseBody
    public List<CompanyTradingHistory> getApiByTradingCode(@PathVariable(value = "tradingCode") String tradingCode)
    {
        List<CompanyTradingHistory> tradingHistories = companyTradingHistoryService.findByTradingCode(tradingCode);

        for (CompanyTradingHistory companyTradingHistory : tradingHistories) {
            companyTradingHistory.setTrade(Math.log10(companyTradingHistory.getTrade()));
            companyTradingHistory.setValue(Math.log10(companyTradingHistory.getValue()));
            companyTradingHistory.setVolume(Math.log10(companyTradingHistory.getVolume()));
        }

        return tradingHistories;
    }

    @GetMapping(value = "companies")
    public ModelAndView companyView(@RequestParam(value = "name", defaultValue = "") String name)
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("title", "Company");
        modelAndView.addObject("companies", companyService.getAllCompaniesByName(name));
        modelAndView.setViewName("view/Companies");

        return modelAndView;
    }

}
