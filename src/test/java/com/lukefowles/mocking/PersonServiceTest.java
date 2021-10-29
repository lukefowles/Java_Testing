package com.lukefowles.mocking;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


class PersonServiceTest {

    private PersonDAO personDAO;
    private PersonService underTest;

    @BeforeEach
    void setUp() {

        // TODO: create a mock for personDAO
        personDAO = mock(PersonDAO.class);
        // TODO: create an instance of underTest and pass personDAO into it
        underTest = new PersonService(personDAO);
    }

    /*
       TODO: Test all these methods.
        You might need to create additional methods. Check test coverage
    */

//    Good luck :)

    @Test
    void itCanSavePerson() {
        //Given
        //A new instance of a person
        Person person = new Person(5, "Luke", 24);
        //Person nullPerson = new Person(null, null, null);

        //Give an EXPECTED return value of 1 when the savePerson method is called on the personDAO mock
        when(personDAO.savePerson(person)).thenReturn(1);

        //When the getPeople method is called on the person DAO then return a list of one person with
        //a different ID
        when(personDAO.getPeople()).thenReturn(List.of(new Person(6, "John", 23)));

        //When
        int actualValue = underTest.savePerson(person);

        //Then
        //Return value should be 1 (i.e. the method of savePerson is called on the DAO implementation
        assertThat( actualValue).isEqualTo(1);

        //the argument of the method savePerson should be person
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        //this line verifies that the argument being passed to save person method is an instance of the person class
        verify(personDAO).savePerson(personArgumentCaptor.capture());
        //this line gets the value of the person passed as the argument in the save person method
        Person personSent = personArgumentCaptor.getValue();
        //this line asserts that person passed to the argument is the correct person given
        assertThat(personSent).isEqualTo(person);


    }

    @Test
    void itWillThrowErrorWhenPersonHasSameID () {
        //Given
        Person person = new Person(5, "Luke", 24);
        when(personDAO.savePerson(person)).thenReturn(1);
        //and that the getPeople() method when called on the personDAO returns
        //a list with a person with the sameID
        when(personDAO.getPeople()).thenReturn(List.of(new Person(5, "John", 23)));

        //Then
        assertThatThrownBy(() -> underTest.savePerson(person))
                .hasMessage("person with id " + person.getId() + " already exists")
                .isInstanceOfAny(IllegalStateException.class);

    }
    @Test
    void itWillThrowErrorWhenEmptyPersonSaved () {
        //Given
        Person person = new Person(null, null, null);
        //and that the getPeople() method when called on the personDAO returns
        when(personDAO.getPeople()).thenReturn(List.of(new Person(6, "John", 23)));

        //Then
        assertThatThrownBy(() -> underTest.savePerson(person))
                .hasMessage("Person cannot have empty fields")
                .isInstanceOfAny(IllegalStateException.class);

    }

    @Test
    void itCanDeletePerson() {
        //Given
        Integer id = 5;
        when(personDAO.getPeople()).thenReturn(List.of(new Person(5, "Luke", 24)));

        //When
        when(personDAO.deletePerson(id)).thenReturn(1);
        Integer actualValue = underTest.deletePerson(id);

        //Then
        //Return value should be 1 (i.e. the method of savePerson is called on the DAO implementation
        assertThat(actualValue).isEqualTo(1);

        //the argument of the method savePerson should be person
        ArgumentCaptor <Integer>  IDArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        //this line verifies that the argument being passed to save person method is an instance of the person class
        verify(personDAO).deletePerson(IDArgumentCaptor.capture());
        //this line gets the value of the person passed as the argument in the save person method
        Integer IDSent = IDArgumentCaptor.getValue();
        //this line asserts that person passed to the argument is the correct person given
        assertThat(IDSent).isEqualTo(id);


    }

    @Test
    void canGetPeopleFromDB() {
        //Given
        List<Person> expected =  List.of(new Person(6, "John", 23));
        when(personDAO.getPeople()).thenReturn(List.of(new Person(6, "John", 23)));

        //When
        List actual =  underTest.getPeople();

        //Then
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void canGetPersonById() {
        //Given
        Optional <Person> expected = Optional.of(new Person(6, "John", 23));
        when(personDAO.getPeople()).thenReturn(List.of(new Person(6, "John", 23),
                new Person(7, "Ron", 23),
                new Person(9, "Vohn", 23)));
        Integer id = 6;
        //When
        Optional<Person> actual = underTest.getPersonById(id);

        //Then
        assertThat(actual).isEqualTo(expected);

    }
}