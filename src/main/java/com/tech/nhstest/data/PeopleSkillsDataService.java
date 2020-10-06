package com.tech.nhstest.data;

import com.tech.nhstest.data.model.PeopleEntity;
import com.tech.nhstest.data.model.SkillsEntity;
import com.tech.nhstest.web.model.People;
import com.tech.nhstest.web.model.Skills;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A service responsible for transforming and processing the data from api to data layer.
 */
@Service
@Slf4j
public class PeopleSkillsDataService {

    @Autowired
    private PeopleSkillsEntityRepository peopleSkillsEntityRepository;


    public List<People> list() {
        List<PeopleEntity> peopleEntityList = peopleSkillsEntityRepository.findAll();
        return peopleEntityList.stream().map(this::mapPeopleEntityToPeople).collect(Collectors.toList());
    }

    public People getById(String id) {
        PeopleEntity peopleEntity = getPeopleEntityById(id);
        return peopleEntity != null ? mapPeopleEntityToPeople(peopleEntity) : null;
    }

    public void update(People people, String peopleId) {
        log.info("about to update people data {} for id {}", people.toString(), peopleId);
        people.setId(peopleId);
        save(people);
        log.info("Successfully updated People data");
    }

    public void save(People people) {
        log.info("about to save people data {}", people.toString());
        PeopleEntity peopleEntity = getPeopleEntityById(people.getId());
        if (peopleEntity == null) {
            peopleEntity = new PeopleEntity();
        }
        mapPeopleToPeopleEntity(peopleEntity, people);
        peopleSkillsEntityRepository.save(peopleEntity);
        log.info("saved PeopleEntity into database {}", peopleEntity.toString());
    }

    public void delete(String id) {
        peopleSkillsEntityRepository.deleteById(id);
        log.debug("People {} deleted successfully", id);
    }

    private PeopleEntity getPeopleEntityById(String id) {
        Optional<PeopleEntity> peopleEntityOptional = peopleSkillsEntityRepository.findById(id);
        return peopleEntityOptional.orElse(null);
    }

    private void mapPeopleToPeopleEntity(PeopleEntity peopleEntity, People people) {
        peopleEntity.setId(people.getId());
        peopleEntity.setName(people.getName());
        List<SkillsEntity> skillsEntityList = people.getSkills().stream().map(this::mapSkillsToSkillsEntity).collect(Collectors.toList());
        peopleEntity.setSkills(skillsEntityList);
    }

    private SkillsEntity mapSkillsToSkillsEntity(Skills skills) {
        return SkillsEntity.builder()
                .level(skills.getLevel())
                .skillNames(StringUtils.collectionToCommaDelimitedString(skills.getSkillsList()))
                .build();
    }

    private People mapPeopleEntityToPeople(PeopleEntity peopleEntity) {
        List<Skills> webSkills = peopleEntity.getSkills().stream().map(this::mapSkillsEntityToSkills).collect(Collectors.toList());
        return People.builder()
                .id(peopleEntity.getId())
                .name(peopleEntity.getName())
                .skills(webSkills).build();
    }

    private Skills mapSkillsEntityToSkills(SkillsEntity skillsEntity) {
        return Skills.builder()
                .level(skillsEntity.getLevel())
                .skillsList(Arrays.asList(StringUtils.delimitedListToStringArray(skillsEntity.getSkillNames(), ",")))
                .build();
    }
}
