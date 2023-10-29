package ru.example.simplelibrary.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 90, message = "ФИО должно быть от 2 до 90 символов длиной")
    private String fio;

    @Min(value = 1800, message = "Год рождения должен быть больше, чем 1800")
    private int birthYear;

    // Конструктор по умолчанию нужен для Spring
    public Person() {
    }

    public Person(String fio, int birthYear) {
        this.fio = fio;
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

}
