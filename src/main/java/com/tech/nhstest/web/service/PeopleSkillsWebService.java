package com.tech.nhstest.web.service;

import com.tech.nhstest.data.PeopleSkillsDataService;
import com.tech.nhstest.web.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service responsible for passing data from api to data layer.
 */
@Service
public class PeopleSkillsWebService {

    @Autowired
    private PeopleSkillsDataService psDataService;

    public List<People> getAllPeople() {
        return psDataService.list();
    }

    public People getPeopleById(String inputId) {
        return psDataService.getById(inputId);
    }

    public void addPeople(List<People> inputPeopleList) {
        for (People people : inputPeopleList) {
            psDataService.save(people);
        }
    }

    public void updatePeople(People newPeople, String id) {
        psDataService.update(newPeople, id);
    }

    public void deletePeople(String id) {
        psDataService.delete(id);
    }
}
