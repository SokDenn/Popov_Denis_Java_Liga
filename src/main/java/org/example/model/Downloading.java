package org.example.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.example.model.Data_validation;
import org.example.model.Manager;
import org.example.model.Status;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Downloading {
    //String csvFileUser = "/home/denis/Рабочий стол/user.csv";
    //String csvFileTask = "/home/denis/Рабочий стол/task.csv";
    static String csvFileUser = "C:\\Users\\denis\\Desktop\\user.csv";
    static String csvFileTask = "C:\\Users\\denis\\Desktop\\task.csv";

    public static void downloading_from_file(int switch_to_task_loading){
        String csvFile;
        if (switch_to_task_loading == 0) csvFile = csvFileUser;
        else csvFile = csvFileTask;

        try (CSVReader rider = new CSVReader(new FileReader(csvFile))){

            String[] mass_str_csv;

            while ((mass_str_csv = rider.readNext()) != null){
                try {
                    Integer id = Data_validation.checking_number(mass_str_csv[0]);

                    if(switch_to_task_loading == 0) {
                        String name = Data_validation.checking_text(mass_str_csv[1]);
                        if(id == null || name == null) throw new NumberFormatException();
                            else Manager.createUser(id,name);}
                    else{
                        String heading = Data_validation.checking_text(mass_str_csv[1]);
                        String description = Data_validation.checking_text(mass_str_csv[2]);
                        Integer id_user = Data_validation.checking_number(mass_str_csv[3]);
                        LocalDate date_of_completion = Data_validation.checking_date(mass_str_csv[4]);
                        Status status;
                        if (mass_str_csv.length > 5) status = Status.title_of_status(mass_str_csv[5]);
                                else status = Status.NEW;

                        if(id == null || heading == null || id_user == null || date_of_completion == null || status == null)
                            throw new NumberFormatException();

                        else if(Manager.add_task(id, heading, description, id_user, date_of_completion, status) == false)
                            throw new NumberFormatException();
                    }

                } catch (Exception e) {
                    System.out.println("Ошибка загрузки из файла строки:");
                    String result = Arrays.stream(mass_str_csv)
                            .map(element -> element + ", ")
                            .collect(Collectors.joining());
                    System.out.println(result);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка открытия файла");
        }
    }

    public static void saving_to_file(int switch_to_task_loading){
        String csvFile;
        if (switch_to_task_loading == 0) csvFile = csvFileUser;
        else csvFile = csvFileTask;

        try (FileWriter writer_file = new FileWriter(csvFile)){

            if(switch_to_task_loading == 0) {

                for(User user : Manager.return_user_list()) {
                    writer_file.write(user.get_full_info_str());
                }
            } else{

                for(User user : Manager.return_user_list()) {
                    for(Task task : user.getTaskList()){
                        writer_file.write(task.get_full_info_str());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка записи в исходный файл");
        }
        if (switch_to_task_loading == 0) System.out.println("Пользователи сохранены!");
        else System.out.println("Задачи сохранены!");
    }
}
