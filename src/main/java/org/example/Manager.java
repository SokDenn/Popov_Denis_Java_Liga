package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Manager {
    private static ArrayList<User> user_list = new ArrayList<>();
    private static ArrayList<Task> task_list = new ArrayList<>();

    public static void createUser(int id, String name) {
        if (findUserById(id)) {
            throw new IllegalArgumentException("ID пользоватлей должны быть уникальными");
        } else {
            User user = new User(id, name);
            user_list.add(user);
        }
    }

    public static void add_task(int id, String heading, String description, Integer id_user, LocalDate date_of_completion) {
        if (findUserById(id_user)) {
            if(!findTaskById(id)){
                Task task = new Task(id, heading, description, date_of_completion);
                task_list.add(task);
                user_list.stream()
                        .filter(user -> user.getId() == id_user)
                        .findFirst().orElse(null)
                        .addTask(task);

            } else{
                throw new IllegalArgumentException("Задача с таким ID уже есть");
            }
        } else {
            throw new IllegalArgumentException("Задача назначается на несуществующий ID пользователя");
        }

    }

    public static ArrayList<User> return_user_list(){
        return user_list;
    }

    public static Boolean findUserById(int id) {
        for (User user : user_list) {
            if (user.getId() == id) {
                return true;
            }
        } return false;
    }

    public static Boolean findTaskById(int id) {
        for (Task task : task_list) {
            if (task.getId() == id) {
                return true;
            }
        } return false;
    }

}
