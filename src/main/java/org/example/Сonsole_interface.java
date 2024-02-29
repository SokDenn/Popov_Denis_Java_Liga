package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Сonsole_interface {
    public static void start(){
        String action = "";

        try (BufferedReader buf = new BufferedReader(new InputStreamReader(System.in))){
            while (!action.equalsIgnoreCase("exit")) {

                Console_out.start_interface();
                action = buf.readLine();
                switch (action.toLowerCase()) {
                    case "1" -> Console_out.all_user_and_task();
                    case "2" -> selection_user(buf);
                    case "exit" -> System.out.println("Пока!");
                    default -> System.out.println("Команда не распознана. Повтори ввод!");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка консоли");
        }
    }

    private static void selection_user(BufferedReader buf) {
        User user;

        while (true) {
            Console_out.available_user();

            try {
                String action = buf.readLine();
                if (action.equalsIgnoreCase("back")) return;

                int user_id = Integer.parseInt(action);
                user = Manager.return_user_list().get(user_id - 1);

                if (user.getTaskList().size() == 0) {
                    System.out.println("Задач у пользоватлея нет");
                    System.out.println("----------------------------------------------");
                } else user_task_editor(user, buf);

            } catch (Exception e) {
                System.out.println("Ошибка поиска пользоваеля с данным id. Повторите ввод:");
            }
        }
    }

    private static void user_task_editor(User user, BufferedReader buf) {
        Console_out.all_user_and_task(user.getId());

        String action = "";
        try {

            while (!action.equalsIgnoreCase("back")) {
                Console_out.available_actions();
                action = buf.readLine();
                switch (action.toLowerCase()) {
                    case "1" -> {
                        Task task = selection_task(user, buf);
                        if(task != null) {
                            Status status = selection_status(buf);
                            if (status != null) {
                                task.setStatus(status);
                                System.out.println("Статус задачи успешно изменен!");
                                Console_out.all_user_and_task(user.getId(), task.getId());
                            }
                        }
                    }

                    case "2" -> {
                        Status status = selection_status(buf);
                        if (status != null) Console_out.all_user_and_task(user.getId(), status);
                    }

                    case "3" -> {
                        Console_out.all_user_and_task(user.getId());
                    }

                    case "back" -> System.out.println("Возрат к выбору пользотвалея");
                    default -> System.out.println("Команда не распознана. Повтори ввод!");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка консоли");
        }
    }

    private static Status selection_status(BufferedReader buf) {
        Console_out.available_status();

        while (true) {
            try {
                String action = buf.readLine();
                Status status;

                if (action.equalsIgnoreCase("back")) return null;
                if ((status = Status.title_of_status(action)) == null) throw new Exception();
                else return status;

            } catch (Exception e) {
                System.out.println("Ошибка выбора статуса. Повторите ввод:");
            }
        }
    }

    private static Task selection_task(User user, BufferedReader buf) {
        Console_out.available_task(user.getId());

        while (true) {
            try {
                String action = buf.readLine();

                if (action.equalsIgnoreCase("back")) return null;
                for (Task task_for_user : user.getTaskList()) {
                    if (task_for_user.getId() == Integer.parseInt(action)) return task_for_user;
                }

                throw new Exception();
            } catch (Exception e) {
                System.out.println("Ошибка выбора задачи. Повторите ввод номера:");
            }
        }
    }
}
