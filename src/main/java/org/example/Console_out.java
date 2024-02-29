package org.example;

import java.util.ArrayList;

public class Console_out {

    public static void all_user_and_task(){
            for (User user : Manager.return_user_list()) {
                System.out.println(user);
                user.getTaskList().stream().forEach(System.out::println);
            }
    }
    public static void all_user_and_task(int user_id){
        System.out.println(Manager.return_user_list().get(user_id - 1));
        Manager.return_user_list()
                .get(user_id - 1)
                .getTaskList().stream()
                .forEach(System.out::println);
    }
    public static void all_user_and_task(int user_id, Status status){
        System.out.println(Manager.return_user_list().get(user_id - 1));
        Manager.return_user_list()
                .get(user_id - 1)
                .getTaskList().stream()
                .filter(task -> task.getStatus() == status)
                .forEach(System.out::println);
    }

    public static void all_user_and_task(int user_id, int id_task){
        System.out.println(Manager.return_user_list().get(user_id - 1));
        Manager.return_user_list()
                .get(user_id - 1)
                .getTaskList().stream()
                .filter(task -> task.getId() == id_task)
                .forEach(System.out::println);
    }

    public static void start_interface(){

        System.out.println("----------------------------------------------"
                            + "\nВведи цифру нужного действия\n"
                            + "1 - Отобразить всех пользоватлей и их задачи" + "\n"
                            + "2 - Все задачи выбранного пользователя" + "\n"
                            + "exit - Выход" + "\n"
                            + "----------------------------------------------");
    }

    public static void available_user(){
        System.out.println("Введите id нужного пользоватлея ");
        Manager.return_user_list().stream().forEach(System.out::println);
        System.out.println("back - Возрат в основное меню");
        System.out.println("----------------------------------------------");
    }

    public static void available_actions(){
        System.out.println("----------------------------------------------");
        System.out.println("1 - Сменить статус задачи\n"
                            +"2 - Задачи с нужным статусом\n"
                            +"3 - Все задачи пользователя\n"
                            +"back - Возрат к выбору пользовталея\n"
                            +"----------------------------------------------");
    }

    public static void available_status(){

        System.out.println("Напишите нужный статус. Доступны:");
        for (Status value : Status.values()) {
            System.out.println(value);
        }
        System.out.println("back - Возрат назад\n"
                +"----------------------------------------------");
    }

    public static void available_task(int user_id){

        System.out.println("Выбери номер задачи. Доступны:");
        all_user_and_task(user_id);
        System.out.println("back - Возрат к выбранному пользователю\n"
                +"----------------------------------------------");
    }
}
