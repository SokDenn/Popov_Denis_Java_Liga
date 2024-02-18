package org.example;

public enum Status {
    NEW("Новое"),
    IN_WORK("В работе"),
    DONE("Выполнено");

    private String title;

    private Status(String title) {
        this.title = title;
    }
    public static Status title_of_status(String title) {
        for (Status values: Status.values()){
            if(values.title.equalsIgnoreCase(title)) return values;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
