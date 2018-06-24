package com.learningstuff.stockexchange_application;

import com.learningstuff.stockexchange_application.dataProvider.DataProviderFromURL;
import com.learningstuff.stockexchange_application.dataProvider.DataProviderFromURL_Implementation;
import com.learningstuff.stockexchange_application.dataProvider.DataReaderFromFile;
import com.learningstuff.stockexchange_application.dataProvider.DataReaderFromFile_Implementation;
import com.learningstuff.stockexchange_application.model.*;
import com.learningstuff.stockexchange_application.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
public class StockExchangeApplicationTests {

    private static boolean oneTimeSetUpDone = false;

	private DataProviderFromURL dataProviderFromURL = new DataProviderFromURL_Implementation();

	private DataReaderFromFile dataReaderFromFile = new DataReaderFromFile_Implementation();

	@Autowired
    private CompanyService companyService;

	@Autowired
    private CompanyTradingHistoryService companyTradingHistoryService;

	@Autowired
    private DataConversionAndDateHandlingService dataConversionAndDateHandlingService;

	@Autowired
    private UserService userService;

	@Autowired
    private RoleService roleService;

	@Autowired
    private ShareService shareService;

	@Before
    public void init()
    {
        if(!oneTimeSetUpDone)
        {
//            companyService.saveAllCompanies(dataReaderFromFile.getAllCompaniesFromFile());

            DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("h:mm a").toFormatter();
//            LocalTime time = LocalTime.parse("2:45 PM", dtf);
//            System.out.println(time);
//            List<CompanyTradingHistoryForURLService> infos = dataReaderFromFile.getAllCompaniesTradingHistoryFromFile();
//            for (CompanyTradingHistoryForURLService info : infos) {
//                Company company1 = companyService.findByCode(info.getTradingCode());
//                CompanyTradingHistory companyInfo = new CompanyTradingHistory(
//                        info.getLtp(),
//                        info.getHigh(),
//                        info.getLow(),
//                        info.getClosep(),
//                        info.getYcp(),
//                        info.get_change(),
//                        info.getTrade(),
//                        info.getValue(),
//                        info.getVolume(),
//                        LocalDateTime.of(LocalDate.of(2018, Month.MAY, 30), LocalTime.parse("2:45 PM", dtf))
//                );
//                companyTradingHistoryService.addCompanyTradingHistory(company1, companyInfo);
//            }

//            companyService.createCompany(new Company("1STPRIMFMF","whatEver"));

//            roleService.createRole(new Role("ADMIN"));
//            roleService.createRole(new Role("USER"));

            List<Role> roles = new ArrayList<>();

//            roles.add(new Role("ADMIN"));
//            roles.add(new Role("USER"));

            roles.add(roleService.findByRole("USER"));
//            User user = new User("admin@mail.com","admin", new Name(), Gender.MALE, "01687430265",new Address(), new Date().toString(), roles);



//            User user = new User("user@mail.com","user", new Name(), Gender.MALE,
//                    "017546446--",new Address(), new Date().toString(), roles);
//
//            userService.createUser(user);

//            Share share = new Share("1STPRIMFMF", 2, 33);

            User user = userService.findUserByEmail("user@mail.com");

//            shareService.saveShare(share);

            oneTimeSetUpDone = true;
        }
    }

	@Test
	public void contextLoads() {
//	    Company company = companyService.findByCode("1STPRIMFMF");
//
//        System.out.println(company.getCompanyName());
//        System.out.println(company);
//	    assertNotNull(company);
//	    assertEquals("1STPRIMFMF", company.getTradingCode());
//        List<Company> list = dataReaderFromFile.getAllCompaniesFromFile();
//
//        for (Company company : list) {
//            if(!isPresent(company.getTradingCode()))
//            {
//                System.out.println(company.getTradingCode());
//            }
//        }


//        LocalDateTime dateAndTime = dataConversionAndDateHandlingService.getJustDateAndTime(dataProviderFromURL.getLatestDateAndTimeFromURL());
//        String dateAndTime1 = getJustDateAndTime(dataProviderFromURL.getLatestDateAndTimeFromURL());


//        String[] tokens = dateAndTime1.split("[, ]+");
//        for (String token : tokens) {
//            System.out.println(token);
//        }

//        System.out.println(dateAndTime);

        List<CompanyTradingHistoryForURLService> infos = dataProviderFromURL.getAllCompanyInfoFromURL();

        infos.forEach(System.out::println);


	}

	private String getJustDateAndTime(String dateAndTime)
    {
        int index = dateAndTime.indexOf("on");
        System.out.println(index);
        return dateAndTime.substring(index+3);
    }

//    private boolean isPresent(String tradingCode) {
//
//	    Optional s = dataReaderFromFile.getAllCompaniesCodeFromFile().stream().filter(code -> code.equals(tradingCode)).findAny();
//
//	    return s.isPresent();
//    }

    @Test
    public void testUser()
    {
        User user = userService.findUserByEmail("user@mail.com");

        Assert.assertNotNull(user);
    }

    @Test
    public void testShare()
    {
        User user = userService.findUserByEmail("user@mail.com");

    }

}
