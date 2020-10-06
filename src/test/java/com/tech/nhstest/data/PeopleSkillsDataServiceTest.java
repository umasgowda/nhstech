package com.tech.nhstest.data;

import com.tech.nhstest.web.model.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleSkillsDataServiceTest {

    @Autowired
    private PeopleSkillsDataService peopleSkillsDataService;

    @Test
    public void testListOfPeople() {
        List<People> peopleList = peopleSkillsDataService.list();
        assertEquals(peopleList.size(), 1);
    }

    @Test
    public void testGetPeopleById() {
       People result = peopleSkillsDataService.getById("p1");
       assertEquals(result.getId(), "p1");
       assertEquals(result.getName(), "Uma Shivalingaiah");
       assertEquals(result.getSkills().size(), 4 );
    }

}