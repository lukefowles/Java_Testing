package com.lukefowles;

import java.util.List;
import java.util.stream.Collectors;

public class PeopleService{

    public static List<Person> isFemale (List<Person> people) {

        if (people.isEmpty())
        {
            throw new IllegalArgumentException("Input cannot be empty or null");
        }

        if (people == null) {
            throw new NullPointerException();
        }

        return people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
    }
}
