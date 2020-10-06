package com.tech.nhstest.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "PEOPLE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleEntity {

    @Id
    @Column(name = "PEOPLE_ID", unique = true, nullable = false)
    private String id;

    @Column(name = "PEOPLE_NAME", nullable = false)
    private String name;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinTable
    private List<SkillsEntity> skills = new ArrayList<>();
}
