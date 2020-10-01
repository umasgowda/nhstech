package com.tech.nhstest.service;

import com.tech.nhstest.model.People;
import com.tech.nhstest.model.Skills;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


@RunWith(MockitoJUnitRunner.class)
public class PeopleServiceTest {

    private static final String EXPERTISE = "expertise";
    private static final String ID = "1";
    private static final String FOO = "foo";
    private static final String WORKING = "working";

    @InjectMocks
    private PeopleService peopleService;


    @Test
    public void testCRUDOperations() {
        Skills skills = new Skills(EXPERTISE, Arrays.asList("java", "dotnet", "php"));
        People expectedResult = new People(ID, FOO, Arrays.asList(skills));
        List<People> inputPeopleList = Arrays.asList(expectedResult);

        //adds people
        peopleService.addPeople(inputPeopleList);

        //Get list of people
        List<People> result = peopleService.getAllPeople();
        assertThat(result.size(), is(1));

        //Get People by Id
        People actualResultById = peopleService.getPeopleById(ID);
        assertThat(actualResultById, is(expectedResult));

        //Update People And Skills
        Skills newSkills1 = new Skills(EXPERTISE, Arrays.asList("A", "B"));
        Skills newSkills2 = new Skills(WORKING, Arrays.asList("D", "E", "F"));
        People newPeople = new People(ID, FOO, Arrays.asList(newSkills1, newSkills2));
        peopleService.updatePeople(newPeople, ID);
        People resultAfterUpdate = peopleService.getPeopleById(ID);
        assertUpdatedPeopleAndSkills(resultAfterUpdate);

        //delete
        peopleService.deletePeople(ID);
        People resultAfterDelete = peopleService.getPeopleById(ID);
        assertThat(resultAfterDelete, nullValue());
    }

    private void assertUpdatedPeopleAndSkills(People resultAfterUpdate) {
        assertThat(resultAfterUpdate.getId(), is(ID));
        assertThat(resultAfterUpdate.getName(), is(FOO));
        assertThat(resultAfterUpdate.getSkills().size(), is(2));
        Skills skills1 = resultAfterUpdate.getSkills().get(0);
        assertThat(skills1.getLevel(), is(EXPERTISE));
        assertThat(skills1.getSkillsList(), contains("A", "B"));
        Skills skills2 = resultAfterUpdate.getSkills().get(1);
        assertThat(skills2.getLevel(), is(WORKING));
        assertThat(skills2.getSkillsList(), contains("D", "E", "F"));
    }

}