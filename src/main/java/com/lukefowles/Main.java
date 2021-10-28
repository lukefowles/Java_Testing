package com.lukefowles;

import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main (String[] args) {

        PeopleService peopleService = new PeopleService();

        List<Person> people = List.of(new Person(Gender.MALE, "Oliver"),
                new Person(Gender.FEMALE, "Halle"),
                new Person(Gender.FEMALE, "Greta"),
                new Person(Gender.MALE, "Luke"));

        peopleService.isFemale(people).stream().forEach(person -> System.out.println(person.toString()));

    }
}
