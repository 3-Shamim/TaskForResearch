package com.learningstuff.stockexchange_application.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Service
public class DataConversionAndDateHandlingService {

    public double getActualValueFromStringAsDouble(String value) {
        String[] token = value.split(",");
        String actualValue = "";

        for (String t : token) {
            actualValue += t;
        }

        return Double.parseDouble(actualValue);
    }

    public LocalDateTime getJustDateAndTime(String dateAndTime) {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("h:mm a").toFormatter(); // Time Formatter

        String dateTime = parseJustDateAndTime(dateAndTime);

        String[] tokens = dateTime.split("[, ]+"); // Parse date and time

        Month month = null;

        for (Month m : Month.values()) {
            if (m.toString().startsWith(tokens[0].trim().toUpperCase())) {
                month = m;  // Select month
            }
        }

        LocalDate date = LocalDate.of(Integer.parseInt(tokens[2]), month, Integer.parseInt(tokens[1])); // Create Local Date

        String sTime = tokens[4] + " " + tokens[5].toUpperCase(); // just time with AM/PM

        LocalTime time = LocalTime.parse(sTime, dateTimeFormatter); // Create Local Time with 24 hour

        LocalDateTime localDateTime = LocalDateTime.of(date, time); // Create Local Date And Time

        return localDateTime;
    }

    private String parseJustDateAndTime(String dateAndTime) {  // Parse Date And Time From Latest Share Date Line
        int index = dateAndTime.indexOf("on");
        return dateAndTime.substring(index + 3);
    }

//    public boolean isNumeric(String value) {
//
////        NumberFormat formatter = NumberFormat.getInstance();
////        ParsePosition pos = new ParsePosition(0);
////        formatter.parse(value, pos);
////        return value.length() == pos.getIndex();
//
//        for (char c : value.toCharArray()) {
//            if (!Character.isDigit(c)) {
//                return false;
//            }
//        }
//        return true;
//    }

}
