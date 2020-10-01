package com.tech.nhstest.service;

import com.tech.nhstest.model.Skills;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Responsible for processing Skills data.
 */
@Service
public class SkillsService {

    private static List<Skills> skillsList = new ArrayList<>();

    public List<Skills> getAllSkills() {
        return skillsList;
    }

    public Skills getSkillsByLevel(String level) {
        Skills skills = null;
        Optional<Skills> optionalSkills = skillsList.stream().filter(s -> s.getLevel().equalsIgnoreCase(level)).findFirst();
        if (optionalSkills.isPresent()) {
            skills = optionalSkills.get();
        }
        return skills;
    }

    public void addSkills(Skills inputSkills) {
        Optional<Skills> optionalSkills = skillsList.stream().filter(s -> s.getLevel().equalsIgnoreCase(inputSkills.getLevel())).findFirst();
        if (optionalSkills.isPresent()) {
            Skills existingSkills = optionalSkills.get();
            List<String> newSkills = Stream.concat(inputSkills.getSkillsList().stream(), existingSkills.getSkillsList().stream()).collect(Collectors.toList());
            existingSkills.setSkillsList(newSkills);
        } else {
            skillsList.add(inputSkills);
        }
    }

    public void updateSkills(Skills newSkills, String level) {
        Optional<Skills> optionalSkills = skillsList.stream().filter(s -> s.getLevel().equalsIgnoreCase(level)).findFirst();
        if (optionalSkills.isPresent()) {
            Skills existingSkills = optionalSkills.get();
            existingSkills.setSkillsList(newSkills.getSkillsList());
        }
    }

    public void deleteSkills(String level) {
        Optional<Skills> optionalSkills = skillsList.stream().filter(s -> s.getLevel().equalsIgnoreCase(level)).findFirst();
        if (optionalSkills.isPresent()) {
            Skills skillsToDelete = optionalSkills.get();
            skillsList.remove(skillsToDelete);
        }
    }
}


