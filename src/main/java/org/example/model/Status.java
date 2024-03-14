package org.example.model;

public enum Status {
    NEW(1, "Новое"),
    IN_WORK(2, "В работе"),
    DONE(3, "Выполнено");

    private final int number;
    private final String title;

    private Status(int number, String title) {
        this.number = number;
        this.title = title;
    }
    public static Status title_of_status(String title) {
        for (Status values: Status.values()){
            if(values.title.equalsIgnoreCase(title)) return values;
        }
        return null;
    }

    public static Status number_of_status(int number_status) {
        for (Status values: Status.values()){
            if(values.number == number_status) return values;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
