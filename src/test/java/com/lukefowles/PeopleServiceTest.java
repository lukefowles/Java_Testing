package com.lukefowles;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class PeopleServiceTest {
    private PeopleService peopleService;

    @BeforeEach
    void setUp() {
        PeopleService peopleService= new PeopleService();
    }

    @Test
    void shouldReturnOnlyTheFemales() {

        //Given
        List<Person> people = List.of(new Person(Gender.MALE, "Oliver"),
                new Person(Gender.FEMALE, "Halle"),
                new Person(Gender.FEMALE, "Greta"),
                new Person(Gender.MALE, "Luke"));

        List<Person> expected = List.of(new Person(Gender.FEMALE, "Halle"),
                new Person(Gender.FEMALE, "Greta"));

        //When
        List<Person> actual = peopleService.isFemale(people);

        //Then
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void emptyListShouldThrowError() {
        //Given
          List<Person> people = List.of();

//        //When

//        //Then
         Assertions.assertThatThrownBy(() -> peopleService.isFemale(people))
                 .isInstanceOf(IllegalArgumentException.class)
                 .hasMessage("Input cannot be empty or null");
    }
    @Test
    void nullArgumentShouldThrowError() {
        //Given
        List<Person> people = null;

//      //When

//      //Then
        Assertions.assertThatThrownBy(() -> peopleService.isFemale(people))
                .isInstanceOf(NullPointerException.class);
    }


}