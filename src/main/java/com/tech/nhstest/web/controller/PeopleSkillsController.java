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
 * People skills management API.
 * Responsible for managing people skills CRUD operations.
 */
@RestController
public class PeopleSkillsController {

    @Autowired
    private PeopleSkillsWebService peopleSkillsWebService;

    /**
     * GET /people endpoint returns list of people.
     *
     * @return returns list of people skills.
     */
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<People> getAllPeople() {
        return peopleSkillsWebService.getAllPeople();
    }

    /**
     * GET /people/{id} endpoint accepts people id in the url path and returns
     * single people json response for the given id.
     *
     * @param id the path variable peopleId
     * @return returns single people skills data
     */

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<People> getPeopleById(@PathVariable String id) {
        People people = peopleSkillsWebService.getPeopleById(id);
        if (people != null) {
            return new ResponseEntity<People>(people, HttpStatus.OK);
        }
        return new ResponseEntity<People>(people, HttpStatus.NOT_FOUND);
    }

    /**
     * POST /people endpoint accepts collection of people skills json data in request body.
     *
     * @param peopleList input collection of people
     */
    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public void addPeople(@RequestBody List<People> peopleList) {
        peopleSkillsWebService.addPeople(peopleList);
    }

    /**
     * PUT /people/{id} endpoint accepts people skills json data in the request body and
     * updates the people skills data for the given id.
     *
     * @param people the people data to be updated
     * @param id id of the people to be updated
     */

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public void updatePeople(@RequestBody People people, @PathVariable String id) {
        peopleSkillsWebService.updatePeople(people, id);
    }

    /**
     * DELETE people/{id} deletes the people skills data for given id.
     *
     * @param id the path variable id.
     */
    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public void deletePeople(@PathVariable String id) {
        peopleSkillsWebService.deletePeople(id);
    }

}
