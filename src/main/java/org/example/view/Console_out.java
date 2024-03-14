package org.example.view;

import org.example.model.Manager;
import org.example.model.Status;
import org.example.model.User;

public class Console_out {

    public static void all_user(){
        System.out.println("Весь список пользователей:");
        Manager.return_user_list().stream().forEach(System.out::println);
        System.out.println("----------------------------------------------");
    }

    public static void all_task(){
        System.out.println("Весь список задач:");
        Manager.return_user_list().stream()
                .flatMap(user -> user.getTaskList().stream())
                .forEach(System.out::println);
    }

    public static void all_user_and_task(){
            for (User user : Manager.return_user_list()) {
                System.out.println(user);
                user.getTaskList().stream().forEach(System.out::println);
            }
    }
    public static void user_all_task(int user_id){
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

    public static void start_interface(){

        System.out.println("----------------------------------------------"
                            + "\nВведи цифру нужного действия\n"
                            + "1 - Отобразить всех пользователей и их задачи" + "\n"
                            + "2 - Все задачи выбранного пользователя" + "\n"
                            + "3 - Добавить задачу" + "\n"
                            + "4 - Добавить пользователя" + "\n"
                            + "/////////////////////////////////////////////" + "\n"
                            + "delete - Удалить все задачи и пользователей" + "\n"
                            + "save - Сохранить в исходные файлы" + "\n"
                            + "exit - Выход" + "\n"
                            + "----------------------------------------------");
    }


    public static void available_actions(){
        System.out.println("----------------------------------------------");
        System.out.println( "1 - Сменить статус задачи\n"
                          + "2 - Задачи с нужным статусом\n"
                          + "3 - Все задачи пользователя\n"
                          + "4 - Редактировать задачу" + "\n"
                          + "5 - Удалить задачу" + "\n"
                          +"back - Возврат к выбору пользователя\n"
                          +"----------------------------------------------");
    }

    public static void  сonfirmation_of_deletion(){
        System.out.println("!----------------------------------------------!\n"
                + "Вы действительно хотите удалить всех пользователей\n"
                + "и их задачи из программы и файла?\n"
                + "!----------------------------------------------!\n"
                + "back - Возврат в основное меню\n"
                + "delete - Подтвердить удаление");

    }

    public static void available_status(){

        System.out.println("Напишите нужный статус. Доступны:");
        int index = 1;
        for (Status value : Status.values()) {
            System.out.println(index + " - " + value);
            index++;
        }
    }

    public static void available_task(int user_id){
        user_all_task(user_id);
        System.out.println("back - Возврат к выбранному пользователю\n"
                +"----------------------------------------------");
        System.out.println("Выбери номер задачи:");
    }

    public static void no_task() throws InterruptedException {
        System.out.println("Задач у пользователя больше нет)");
        System.out.println("#_#");
        System.out.println("Выполняется возврат в основное меню");
        Thread.sleep(1000);
    }
}
