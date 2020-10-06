package com.tech.nhstest.web.service;

import com.tech.nhstest.data.PeopleSkillsDataService;
import com.tech.nhstest.web.model.People;
import com.tech.nhstest.web.model.Skills;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleSkillsWebServiceTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LEVEL = "level";
    private static final String SKILL_NAME = "java8";

    @InjectMocks
    private PeopleSkillsWebService peopleSkillsWebService;

    @Mock
    private PeopleSkillsDataService psDataService;


    @Test
    public void getAllPeopleReturnsListOfPeople() {
        List<People> expectedResult = Arrays.asList(new People(ID, NAME, Arrays.asList(new Skills(LEVEL, Arrays.asList(SKILL_NAME)))));
        when(psDataService.list()).thenReturn(expectedResult);

        List<People> result = peopleSkillsWebService.getAllPeople();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getPeopleByIdReturnsSingleItemForGivenId() {
        People expectedResult = new People(ID, NAME, Arrays.asList(new Skills(LEVEL, Arrays.asList(SKILL_NAME))));

        People people = new People(ID, NAME, Arrays.asList(new Skills(LEVEL, Arrays.asList(SKILL_NAME))));
        when(psDataService.getById(ID)).thenReturn(people);


        People result = peopleSkillsWebService.getPeopleById(ID);
        assertEquals(expectedResult, result);
    }

    @Test
    public void addPeopleAddsTheRecords() {

        List<People> inputPeopleList = Arrays.asList(new People(ID, NAME, Arrays.asList(new Skills(LEVEL, Arrays.asList(SKILL_NAME)))));

        peopleSkillsWebService.addPeople(inputPeopleList);

        verify(psDataService).save(new People(ID, NAME, Arrays.asList(new Skills(LEVEL, Arrays.asList(SKILL_NAME)))));
    }

    @Test
    public void testUpdatePeople() {
        People newPeople = new People(ID, NAME, Arrays.asList(new Skills(LEVEL, Arrays.asList(SKILL_NAME))));

        peopleSkillsWebService.updatePeople(newPeople, ID);

        verify(psDataService).update(newPeople, ID);
    }

    @Test
    public void testDeletePeople() {

        peopleSkillsWebService.deletePeople(ID);

        verify(psDataService).delete(ID);
    }

}