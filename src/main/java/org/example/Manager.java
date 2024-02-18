package org.example;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Manager {
    private static Manager instance;
    private Set<Integer> userId = new HashSet();
    private Set<Integer> taskId = new HashSet();
    private Manager() {}
    public static Manager getInstance() {

        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public User createUser(int id, String name) {
        if (userId.contains(id)) {
            throw new IllegalArgumentException("ID пользоватлей должны быть уникальными");
        } else {
            User user = new User(id, name);
            userId.add(id);
            return user;
        }
    }
    public  Task add_task(int id, String heading, String description, Integer id_user, LocalDate date_of_completion) {
        if (userId.contains(id_user)) {
            if(!taskId.contains(id)){
                Task task = new Task(id, heading, description, date_of_completion);
                taskId.add(id);
                return task;
            } else{
                throw new IllegalArgumentException("Задача с таким ID уже есть");
            }
        } else {
            throw new IllegalArgumentException("Задача назначается на несуществующий ID пользователя");
        }

    }

}
