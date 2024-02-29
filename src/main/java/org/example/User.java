package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Task> task;

    protected User(int id, String name) {
        this.id = id;
        this.name = name;
        this.task = new ArrayList();
    }


    public int getId(){
        return this.id;
    }

    public void addTask(Task task) {
        this.task.add(task);
    }

    public List<Task> getTaskList() {
        return this.task;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name;
    }
}
