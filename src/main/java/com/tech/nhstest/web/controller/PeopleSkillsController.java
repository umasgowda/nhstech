package com.tech.nhstest.web.controller;

import com.tech.nhstest.web.model.People;
import com.tech.nhstest.web.service.PeopleSkillsWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *
 * Reponsible for managing people API CRUD resources
 */
@RestController
public class PeopleSkillsController {

    @Autowired
    private PeopleSkillsWebService peopleSkillsWebService;

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<People> getAllPeople() {
        return peopleSkillsWebService.getAllPeople();
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<People> getPeopleById(@PathVariable String id) {
        People people = peopleSkillsWebService.getPeopleById(id);
        if (people != null) {
            return new ResponseEntity<People>(people, HttpStatus.OK);
        }
        return new ResponseEntity<People>(people, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public void addPeople(@RequestBody List<People> peopleList) {
        peopleSkillsWebService.addPeople(peopleList);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public void updatePeople(@RequestBody People people, @PathVariable String id) {
        peopleSkillsWebService.updatePeople(people, id);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public void deletePeople(@PathVariable String id) {
        peopleSkillsWebService.deletePeople(id);
    }

}
