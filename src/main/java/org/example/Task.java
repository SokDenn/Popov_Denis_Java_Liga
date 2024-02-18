package org.example;

import java.time.LocalDate;

public class Task {
    private int id;
    private String heading;
    private String description;
    private LocalDate date_of_completion;
    private Status status;

    public Task(int id, String heading, String description, LocalDate data) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.date_of_completion = data;
        this.status = Status.NEW;
    }

    public int getId() {
        return this.id;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Задача номер - " + id + "\n" +
                heading + ": " + description +
                "\nВыполнить до: " + date_of_completion.toString() + ", Статус: " + status + "\n";
    }
}
