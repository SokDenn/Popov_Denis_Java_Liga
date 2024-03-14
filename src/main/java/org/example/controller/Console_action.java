package org.example.controller;

import org.example.model.Data_validation;
import org.example.model.Downloading;
import org.example.model.Manager;
import org.example.model.Status;
import org.example.view.Console_out;

import java.io.BufferedReader;
import java.time.LocalDate;

public class Console_action {
    public static void add_task_console(BufferedReader buf, Integer user_id_original, Integer task_id_original, int switch_to_edit){

        Integer id = null;
        String heading = null;
        String description = null;
        Integer user_id = null;
        LocalDate date_of_completion = null;
        Status status = null;

        while (true){
            try {

                if(id == null) {
                    System.out.println("Введите id задачи:");
                    if((id = Data_validation.checking_number(buf.readLine())) == null) throw new Exception();

                    if (Manager.findTaskById(id) && id != task_id_original) {
                        System.out.println("Такой id уже есть");
                        id = null; continue;
                    }
                }

                if(heading == null) {
                    System.out.println("Введите заголовок задачи:");
                    if((heading = Data_validation.checking_text(buf.readLine())) == null) {
                        System.out.println("Значение не может быть пустым");
                        continue;
                    }
                }

                if(description == null) {
                    System.out.println("Введите описание задачи:");
                    if((description = Data_validation.checking_text(buf.readLine())) == null) {
                        System.out.println("Значение не может быть пустым");
                        continue;
                    }
                }

                if(user_id == null) {
                    user_id = Сonsole_selection.selection_user(buf);
                }

                if(date_of_completion == null) {
                    System.out.println("Введите дату в формате 'dd.MM.yyyy':");
                    if((date_of_completion = Data_validation.checking_date(buf.readLine())) == null)
                        throw new Exception();
                }

                if(status == null) {
                    status = Сonsole_selection.selection_status(buf);
                    break;
                }

            } catch (Exception e){
                System.out.println("Ошибка ввода! Повторите ввод");
            }
        }
        if(switch_to_edit == 0) Manager.add_task(id, heading, description, user_id, date_of_completion, status);
        else Manager.edit_task(id, task_id_original, heading, description, user_id, user_id_original, date_of_completion, status);
    }

    public static void add_user_console(BufferedReader buf) {

        Integer id = null;
        String name = null;

        while (true) {
            try {

                if(name == null) {
                    System.out.println("Введите имя пользователя:");
                    if((name = Data_validation.checking_text(buf.readLine())) == null) throw new Exception();
                }

                if(id == null) {
                    Console_out.all_user();
                    System.out.println("Введите неповторимый id пользователя:");
                    if((id = Data_validation.checking_number(buf.readLine())) == null) throw new Exception();

                    if (Manager.createUser(id, name) == false) {
                        id = null;
                    } else return;
                }

            } catch (Exception e) {
                System.out.println("Ошибка ввода! Повторите ввод");
            }
        }
    }


    public static void clearing_task_tracker(BufferedReader buf){
        Console_out.сonfirmation_of_deletion();
        String action = "";

        while (!action.equalsIgnoreCase("back")) {
            try {
                if((action = Data_validation.checking_text(buf.readLine())) == null)
                    throw new Exception();

                else switch (action){
                    case "delete" -> {
                        Manager.delete_all_user_and_task();
                        Downloading.saving_to_file(0);
                        Downloading.saving_to_file(1);

                        System.out.println("Удаление завершено!");
                        action = "back";
                    }
                    case "back" -> {System.out.println("Удаление отменено");}
                    default -> {throw new Exception();}
                }

            } catch (Exception e) {
                System.out.println("Ошибка ввода! Повторите ввод");
            }
        }
    }
}
