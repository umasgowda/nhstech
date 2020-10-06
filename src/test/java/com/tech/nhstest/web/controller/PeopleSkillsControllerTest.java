package com.tech.nhstest.web.controller;


import com.tech.nhstest.web.model.People;
import com.tech.nhstest.web.model.Skills;
import com.tech.nhstest.web.service.PeopleSkillsWebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleSkillsControllerTest {

    List<People> peopleList;
    @InjectMocks
    private PeopleSkillsController peopleSkillsController;
    @Mock
    private PeopleSkillsWebService peopleSkillsWebService;

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
    public void addPeopleApiAddsThePeopleRecords() {
        peopleSkillsController.addPeople(peopleList);

        verify(peopleSkillsWebService).addPeople(peopleList);
    }

    @Test
    public void getAllPeopleApiReturnsListOfPeopleRecords() {
        when(peopleSkillsWebService.getAllPeople()).thenReturn(peopleList);

        List<People> result = peopleSkillsController.getAllPeople();
        assertThat(result.size(), is(2));
    }

    @Test
    public void testGetPeopleByIdSuccessfulWhenRecordExistForGivenId() {
        Skills skills = Skills.builder().level("expertise").skillsList(Arrays.asList("A", "B", "c")).build();
        People people = People.builder().id("1").name("foo").skills(Arrays.asList(skills)).build();
        when(peopleSkillsWebService.getPeopleById("1")).thenReturn(people);

        ResponseEntity<People> result = peopleSkillsController.getPeopleById("1");

        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(people));

    }

    @Test
    public void testGetPeopleByIdReturns404ErrorWhenRecordDoesNotExistForGivenId() {
        when(peopleSkillsWebService.getPeopleById("1")).thenReturn(null);

        ResponseEntity<People> result = peopleSkillsController.getPeopleById("1");

        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void testUpdatePeople() {
        Skills skills = Skills.builder().level("expertise").skillsList(Arrays.asList("A", "B", "c")).build();
        People people = People.builder().id("1").name("foo").skills(Arrays.asList(skills)).build();

        peopleSkillsController.updatePeople(people, "1");

        verify(peopleSkillsWebService).updatePeople(ArgumentMatchers.any(People.class), anyString());
    }


    @Test
    public void testDeletePeople() {
        // Run the test
        peopleSkillsController.deletePeople("id");

        // Verify the results
        verify(peopleSkillsWebService).deletePeople("id");
    }
}