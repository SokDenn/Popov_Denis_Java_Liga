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
    static ArrayList<User> user_list = new ArrayList<>();
    static Manager manager = Manager.getInstance();

    public static void main(String[] args) {
        String csvFileUser = "C:\\Users\\denis\\Desktop\\user.csv";
        String csvFileTask = "C:\\Users\\denis\\Desktop\\task.csv";
        //String csvFileUser = "/home/denis/Рабочий стол/user.csv";
        //String csvFileTask = "/home/denis/Рабочий стол/task.csv";

        downloading_from_file(csvFileUser, 0);
        downloading_from_file(csvFileTask, 1);

        Сonsole_interface.start();
    }


    private static void downloading_from_file(String csvFile, int switch_to_task_loading){
        try (CSVReader rider = new CSVReader(new FileReader(csvFile))){

            String[] mass_str_csv;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            while ((mass_str_csv = rider.readNext()) != null){
                try {
                    int id = Integer.parseInt(mass_str_csv[0]);

                    if(switch_to_task_loading == 0) {
                        String name = mass_str_csv[1];
                        user_list.add(manager.createUser(id,name));}
                    else{
                        String heading = mass_str_csv[1];
                        String description = mass_str_csv[2];
                        Integer id_user = Integer.parseInt(mass_str_csv[3]);
                        LocalDate date_of_completion = LocalDate.parse(mass_str_csv[4],formatter);

                        Task task = manager.add_task(id, heading, description, id_user, date_of_completion);
                        user_list.stream().filter(user -> user.getID() == id_user).findFirst().orElse(null).addTask(task);
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