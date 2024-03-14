package org.example.model;

import org.example.model.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String heading;
    private String description;
    private int user_id;
    private LocalDate date_of_completion;
    private Status status;

    public Task(int id, String heading, String description, LocalDate data, int user_id, Status status) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.user_id = user_id;
        this.date_of_completion = data;
        this.status = status;
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

    public String get_full_info_str() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return this.id + "," + this.heading + "," + this.description + "," + this.user_id + ","
                + this.date_of_completion.format(formatter) + "," + status + "\n";
    }

    @Override
    public String toString() {
        return "Задача номер - " + id + "\n" +
                heading + ": " + description +
                "\nВыполнить до: " + date_of_completion.toString() + ", Статус: " + status + "\n";
    }
}
