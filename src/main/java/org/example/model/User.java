package org.example.model;

import org.example.model.Task;

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

    public void clearTasks() {
        task.clear();
    }
    public String get_full_info_str() {
        return this.id + "," + this.name + "\n";
    }
    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name;
    }
}
