package com.tech.nhstest.data;

import com.tech.nhstest.data.model.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleSkillsEntityRepository extends JpaRepository<PeopleEntity, String> {
}
