package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String csvFileUser = "C:\\Users\\denis\\Desktop\\user.csv";
        String csvFileTask = "C:\\Users\\denis\\Desktop\\task.csv";
        //String csvFileUser = "/home/denis/Рабочий стол/user.csv";
        //String csvFileTask = "/home/denis/Рабочий стол/task.csv";

        Downloading.downloading_from_file(csvFileUser, 0);
        Downloading.downloading_from_file(csvFileTask, 1);

        Сonsole_interface.start();
    }



}