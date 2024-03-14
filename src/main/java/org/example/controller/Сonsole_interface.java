package org.example.controller;

import org.example.model.Data_validation;
import org.example.model.Downloading;
import org.example.model.Manager;
import org.example.model.Status;
import org.example.view.Console_out;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;


public class Сonsole_interface {
    public static void start(){
        String action = "";

        try (BufferedReader buf = new BufferedReader(new InputStreamReader(System.in))){
            while (!action.equalsIgnoreCase("exit")) {

                Console_out.start_interface();
                action = buf.readLine();
                switch (action.toLowerCase()) {
                    case "1" -> {
                        if(Manager.return_user_list().isEmpty()) {
                            System.out.println("Список пользователей пуст...");
                        } else Console_out.all_user_and_task();
                    }
                    case "2" -> {
                        if(Manager.return_user_list().isEmpty()) {
                            System.out.println("Список пользователей пуст...");
                        } else {
                        Integer user_id = Сonsole_selection.selection_user(buf);
                        Сonsole_interface.user_task_console(user_id, buf);}
                    }
                    case "3" -> {
                        if(Manager.return_user_list().isEmpty()) {
                            System.out.println("Список пользователей пуст...");
                        } else {
                        Console_out.all_task();
                        Console_action.add_task_console(buf, null, null, 0);}}

                    case "4" -> {
                        Console_action.add_user_console(buf);
                        }

                    case "delete" -> {
                        Console_action.clearing_task_tracker(buf);
                    }

                    case "save" -> {
                        Downloading.saving_to_file(0);
                        Downloading.saving_to_file(1);
                    }
                    case "exit" -> System.out.println("Пока!");
                    default -> System.out.println("Команда не распознана. Повтори ввод!");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка консоли");
        }
    }


    public static void user_task_console(Integer user_id, BufferedReader buf) {
        Console_out.user_all_task(user_id);

        String action = "";
        try {

            while (!action.equalsIgnoreCase("back")) {
                if(Manager.findTaskByUserId(user_id) == false) {
                    Console_out.no_task();
                    break;
                }

                Console_out.available_actions();
                action = buf.readLine();

                switch (action.toLowerCase()) {

                    case "1" -> {
                        Integer task_id = Сonsole_selection.selection_task_user(user_id, buf);
                        Status status = Сonsole_selection.selection_status(buf);

                        Manager.set_status_task(user_id, task_id, status);
                        System.out.println("Статус задачи успешно изменен!");
                    }
                    case "2" -> {
                        Status status = Сonsole_selection.selection_status(buf);
                        Console_out.all_user_and_task(user_id, status);
                    }
                    case "3" -> {
                        Console_out.user_all_task(user_id);
                    }
                    case "4" -> {
                        Integer task_id = Сonsole_selection.selection_task_user(user_id, buf);
                        Console_action.add_task_console(buf, user_id,task_id, 1);}
                    case "5" -> {
                        Integer task_id = Сonsole_selection.selection_task_user(user_id, buf);
                        Manager.delete_task(user_id, task_id);
                        System.out.println("Удаление задачи выполнено успешно!");}

                    case "back" -> System.out.println("Возврат к выбору пользователя");
                    default -> System.out.println("Команда не распознана. Повтори ввод!");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка консоли");
        }
    }


}
