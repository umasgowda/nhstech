package com.tech.nhstest.controller;


import com.tech.nhstest.model.People;
import com.tech.nhstest.model.Skills;
import com.tech.nhstest.service.PeopleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PeopleControllerTest {

    List<People> peopleList;
    @InjectMocks
    private PeopleController peopleController;
    @Mock
    private PeopleService peopleService;

    @Before
    public void setUp() {
        peopleList = new ArrayList<>();
        Skills skills = Skills.builder().level("expertise").skillsList(Arrays.asList("A", "B", "c")).build();
        Skills skills2 = Skills.builder().level("Working").skillsList(Arrays.asList("Java8", "Docker", "SpringBoot")).build();
        People people1 = People.builder().id("1").name("foo").skills(Arrays.asList(skills)).build();
        People people2 = People.builder().id("2").name("dummy").skills(Arrays.asList(skills2)).build();
        peopleList.addAll(Arrays.asList(people1, people2));
    }

    @Test
    public void addPeopleAddsThePeopleRecords() {
        peopleController.addPeople(peopleList);

        verify(peopleService).addPeople(peopleList);
    }

    @Test
    public void testGetAllPeoples() {
        when(peopleService.getAllPeople()).thenReturn(peopleList);

        List<People> result = peopleController.getAllPeople();
        assertThat(result.size(), is(2));
    }

    @Test
    public void testGetPeopleByIdSuccessfulWhenRecordExistForGivenId() {
        Skills skills = Skills.builder().level("expertise").skillsList(Arrays.asList("A", "B", "c")).build();
        People people = People.builder().id("1").name("foo").skills(Arrays.asList(skills)).build();
        when(peopleService.getPeopleById("1")).thenReturn(people);

        ResponseEntity<People> result = peopleController.getPeopleById("1");

        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(people));

    }

    @Test
    public void testGetPeopleByIdReturns404ErrorWhenRecordDoesNotExistForGivenId() {
        when(peopleService.getPeopleById("1")).thenReturn(null);

        ResponseEntity<People> result = peopleController.getPeopleById("1");

        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void testUpdatePeople() {
        Skills skills = Skills.builder().level("expertise").skillsList(Arrays.asList("A", "B", "c")).build();
        People people = People.builder().id("1").name("foo").skills(Arrays.asList(skills)).build();

        peopleController.updatePeople(people, "1");

        verify(peopleService).updatePeople(ArgumentMatchers.any(People.class), anyString());
    }


    @Test
    public void testDeletePeople() {
        // Run the test
        peopleController.deletePeople("id");

        // Verify the results
        verify(peopleService).deletePeople("id");
    }
}