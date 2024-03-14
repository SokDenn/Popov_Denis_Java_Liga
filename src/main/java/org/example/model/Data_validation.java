package org.example.model;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data_validation {

    public static String checking_text(String text){
        if (text.isEmpty())
            return null;
         else
             return text;
    }

    public static Integer checking_number(String number){
        BigInteger number_to_check = new BigInteger(number);
        Integer result;

        try {
            result = Integer.parseInt(number);

            if (number_to_check.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0 &&
                    number_to_check.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0) {
                return result;

            } else return null;

        } catch (Exception e){
            return null;
        }
    }

    public static LocalDate checking_date(String data_str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currentDate = LocalDate.now();

        try {
            LocalDate date_of_completion = LocalDate.parse(data_str,formatter);

            //if (date_of_completion.isBefore(currentDate)) return null; else
                return date_of_completion;

        }catch (Exception e){
            return null;
        }
    }
}
