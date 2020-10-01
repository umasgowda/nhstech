package com.tech.nhstest.controller;

import com.tech.nhstest.model.People;
import com.tech.nhstest.service.PeopleService;
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
 * @author Uma Shivalingaiah
 *
 * Reponsible for managing people API CRUD resources
 */
@RestController
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<People> getAllPeople() {
        return peopleService.getAllPeople();
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<People> getPeopleById(@PathVariable String id) {
        People people = peopleService.getPeopleById(id);
        if (people != null) {
            return new ResponseEntity<People>(people, HttpStatus.OK);
        }
        return new ResponseEntity<People>(people, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public void addPeople(@RequestBody List<People> peopleList) {
        peopleService.addPeople(peopleList);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public void updatePeople(@RequestBody People people, @PathVariable String id) {
        peopleService.updatePeople(people, id);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public void deletePeople(@PathVariable String id) {
        peopleService.deletePeople(id);
    }

}
