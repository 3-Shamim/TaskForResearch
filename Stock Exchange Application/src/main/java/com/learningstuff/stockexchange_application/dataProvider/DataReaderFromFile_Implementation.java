package com.learningstuff.stockexchange_application.dataProvider;

import com.learningstuff.stockexchange_application.model.Company;
import com.learningstuff.stockexchange_application.model.CompanyTradingHistoryForURLService;
import com.learningstuff.stockexchange_application.service.DataConversionAndDateHandlingService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class DataReaderFromFile_Implementation implements DataReaderFromFile {

    private DataConversionAndDateHandlingService dataConversionAndDateHandlingService = new DataConversionAndDateHandlingService();

    List<Company> companies = new ArrayList<>();
    List<CompanyTradingHistoryForURLService> companyInfos = new ArrayList<>();


    @Override
    public List<Company> getAllCompaniesFromFile() {
        try (RandomAccessFile companiesFile = new RandomAccessFile("Companies.html", "r")) {
            String line;
            while ((line = companiesFile.readLine()) != null) {
                int index = line.indexOf(" ");
                String code = line.substring(0, index);
                String name = line.substring(index + 2, line.length() - 2);
                companies.add(new Company(code, name));
            }
            companiesFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        companies.forEach(System.out::println);
        return companies;
    }

    @Override
    public List<CompanyTradingHistoryForURLService> getAllCompaniesTradingHistoryFromFile() {
        try (RandomAccessFile companiesFile = new RandomAccessFile("values.html", "r")) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = companiesFile.readLine()) != null) {

                list.add(line);

                if (list.size() == 11) {

                    CompanyTradingHistoryForURLService companyTradingHistoryForURLService = new CompanyTradingHistoryForURLService(
                            list.get(1).trim(),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(2).trim()),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(3).trim()),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(4).trim()),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(5).trim()),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(6).trim()),
                            list.get(7).trim(),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(8).trim()),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(9).trim()),
                            dataConversionAndDateHandlingService.getActualValueFromStringAsDouble(list.get(10).trim())
                            );
                    companyInfos.add(companyTradingHistoryForURLService);

                    list.clear();
                }
            }
            companiesFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return companyInfos;
    }


}

