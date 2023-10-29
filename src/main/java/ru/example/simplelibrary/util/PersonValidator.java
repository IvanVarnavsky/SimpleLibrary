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
        if (personDAO.getPersonByFIO(person.getFio()).isPresent() && personDAO.show(person.getId()) == null) {
            errors.rejectValue("fio", "", "Человек с таким ФИО уже существует");
        }

        // Проверяем, что у человека ФИО начинается с заглавной буквы
        if (!Character.isUpperCase(person.getFio().codePointAt(0)))
            errors.rejectValue("fio", "", "ФИО должно начинаться с большой буквы");

    }
}
