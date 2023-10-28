package ru.example.simplelibrary.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.simplelibrary.dao.PersonDAO;
import ru.example.simplelibrary.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        // Проверка уникальности ФИО
        if (personDAO.show(person.getFio()).isPresent() && personDAO.show(person.getId()) == null) {
            errors.rejectValue("fio", "", "This FIO is already in use");
        }

        // Проверяем, что у человека ФИО начинается с заглавной буквы
        if (!Character.isUpperCase(person.getFio().codePointAt(0)))
            errors.rejectValue("fio", "", "Name should start with a capital letter");

    }
}
