package com.tech.nhstest.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "SKILLS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_ID", unique = true, nullable = false)
    private long id;

    @Column(name = "SKILL_LEVEL", nullable = false)
    private String level;

    @Column(name = "SKILL_NAMES", nullable = false)
    private String skillNames;
}
