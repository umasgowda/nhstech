package com.tech.nhstest.controller;

import com.tech.nhstest.model.Skills;
import com.tech.nhstest.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Uma Shivalingaiah
 *
 * Responsible for managing skills api CRUD resources
 */
@RestController
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    public List<Skills> getAllSkills() {
        return skillsService.getAllSkills();
    }

    @RequestMapping(value = "/skills/{level}", method = RequestMethod.GET)
    public Skills getSkillsByLevelId(@PathVariable String level) {
        return skillsService.getSkillsByLevel(level);
    }

    @RequestMapping(value = "/skills", method = RequestMethod.POST)
    public void addSkills(@RequestBody Skills skills) {
        skillsService.addSkills(skills);
    }

    @RequestMapping(value = "/skills/{level}", method = RequestMethod.PUT)
    public void updateSkills(@RequestBody Skills newSkills, @PathVariable String level) {
        skillsService.updateSkills(newSkills, level);
    }

    @RequestMapping(value = "/skills/{level}", method = RequestMethod.DELETE)
    public void deleteSkills(@PathVariable String level) {
        skillsService.deleteSkills(level);
    }
}
