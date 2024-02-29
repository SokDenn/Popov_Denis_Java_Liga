package org.example;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Downloading {

    public static void downloading_from_file(String csvFile, int switch_to_task_loading){
        try (CSVReader rider = new CSVReader(new FileReader(csvFile))){

            String[] mass_str_csv;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            while ((mass_str_csv = rider.readNext()) != null){
                try {
                    int id = Integer.parseInt(mass_str_csv[0]);

                    if(switch_to_task_loading == 0) {
                        String name = mass_str_csv[1];
                        Manager.createUser(id,name);}
                    else{
                        String heading = mass_str_csv[1];
                        String description = mass_str_csv[2];
                        Integer id_user = Integer.parseInt(mass_str_csv[3]);
                        LocalDate date_of_completion = LocalDate.parse(mass_str_csv[4],formatter);

                        Manager.add_task(id, heading, description, id_user, date_of_completion);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Ошибка загрузки из файла строки:");
                    Arrays.stream(mass_str_csv)
                            .forEach(System.out::println);
                    throw new RuntimeException(e);
                }
            }

        } catch (Exception e) {
            System.out.println("Файл не соответвует нужному формату");
            throw new RuntimeException(e);
        }
    }
}
