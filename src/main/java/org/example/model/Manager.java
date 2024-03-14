package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Manager {
    private static ArrayList<User> user_list = new ArrayList<>();

    public static Boolean createUser(int id, String name) {
        if (findUserById(id)) {
            System.out.println("ID пользоватлей должны быть уникальными");
            return false;
        } else {
            User user = new User(id, name);
            user_list.add(user);
            return true;
        }
    }

    public static Boolean add_task(int id, String heading, String description, Integer id_user, LocalDate date_of_completion, Status status) {
        if (findUserById(id_user)) {
            if(!findTaskById(id)){
                Task task = new Task(id, heading, description, date_of_completion, id_user, status);
                user_list.stream()
                        .filter(user -> user.getId() == id_user)
                        .findFirst().orElse(null)
                        .addTask(task);
                return true;

            } else{
                System.out.println("----------------------------------------------");
                System.out.println("Задача с таким ID уже есть");
                return false;
            }
        } else {
            System.out.println("Задача назначается на несуществующий ID пользователя");
            return false;
        }
    }

    public static void edit_task(int task_id_new, Integer task_id_original, String heading, String description, Integer user_id_new,
                                 Integer user_id_original, LocalDate date_of_completion, Status status) {

        Task task_result = new Task(task_id_new, heading, description, date_of_completion, user_id_new, status);

        if (user_id_original != user_id_new) {
            Manager.delete_task(user_id_original, task_id_original);
            Manager.add_task(task_id_new, heading, description, user_id_new, date_of_completion, status);
        } else {

            user_list.stream()
                    .filter(user -> user.getId() == user_id_original)
                    .findFirst().orElse(null)
                    .getTaskList().set(Manager.findTaskById(task_id_original, user_id_original), task_result);

        }
    }

    public static void delete_task(Integer user_id, Integer task_id) {
        user_list.stream()
                .filter(user -> user.getId() == user_id)
                .findFirst()
                .ifPresent(user -> {
                    user.getTaskList().removeIf(task -> task.getId() == task_id);
                });
    }

    public static void delete_all_user_and_task() {
        for (User user : user_list) {
            user.clearTasks();
        }
        user_list.clear();
    }

    public static void set_status_task(Integer id_user, Integer id_task, Status status){
        user_list.stream()
                .filter(user -> user.getId() == id_user)
                .findFirst().orElse(null)
                .getTaskList().stream()
                .filter(task -> task.getId() == id_task)
                .findFirst().orElse(null).setStatus(status);

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

        Optional<Task> foundTask = user_list.stream()
                .flatMap(user -> user.getTaskList().stream())
                .filter(task -> task.getId() == id)
                .findFirst();

        if (foundTask.isPresent()) {
                return true;
            } else return false;
    }

    public static Integer findTaskById(Integer id, Integer user_id) {

        List<Task> task_list_user = user_list.stream()
                .filter(user -> user.getId() == user_id)
                .findFirst().orElse(null)
                .getTaskList();
        if(task_list_user.size() == 0) return null;

        for (int index = 0; index < task_list_user.size(); index++) {
            if (task_list_user.get(index).getId() == id) {
                return index;
            }
        }
        return null;
    }

    public static Boolean findTaskByUserId(Integer user_id) {

        List<Task> task_list_user = user_list.stream()
                .filter(user -> user.getId() == user_id)
                .findFirst().orElse(null)
                .getTaskList();
        if(task_list_user.size() == 0) return false;
        return true;
    }
}
