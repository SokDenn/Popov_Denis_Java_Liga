package org.example.controller;

import org.example.model.Data_validation;
import org.example.model.Manager;
import org.example.model.Status;
import org.example.view.Console_out;

import java.io.BufferedReader;

public class Сonsole_selection {

    public static Status selection_status(BufferedReader buf) {
        Console_out.available_status();

        while (true) {
            try {
                Integer number_status;
                Status status;

                if((number_status = Data_validation.checking_number(buf.readLine())) == null)
                    throw new Exception();

                if ((status = Status.number_of_status(number_status)) == null)
                    throw new Exception();

                else return status;

            } catch (Exception e) {
                System.out.println("Ошибка выбора статуса. Повторите ввод:");
            }
        }
    }

    public static Integer selection_task_user(Integer user_id, BufferedReader buf) {
        Integer id_task;
        Console_out.available_task(user_id);

        while (true) {
            try {
                if((id_task = Data_validation.checking_number(buf.readLine())) == null) throw new Exception();

                if (Manager.findTaskById(id_task, user_id) == null) {
                    System.out.println("Такого id задачи нет. Повторите ввод номера:");
                    id_task = null;
                    continue;
                }

                return id_task;
            } catch (Exception e) {
                System.out.println("Ошибка выбора задачи. Повторите ввод номера:");
            }
        }
    }

    public static Integer selection_user(BufferedReader buf) {
        Integer id_user;
        if(Manager.return_user_list().size() == 0) return null;

        Console_out.all_user();

        while (true) {
            System.out.println("Введите id нужного пользователя:");

            try {
                if((id_user = Data_validation.checking_number(buf.readLine())) == null)
                    throw new Exception();

                if (!Manager.findUserById(id_user)) {
                    System.out.println("Такого id пользователя нет");
                    id_user = null;
                    continue;
                }

                return id_user;
            } catch (Exception e) {
                System.out.println("Ошибка поиска пользователя с данным id. Повторите ввод:");
            }
        }
    }

}
