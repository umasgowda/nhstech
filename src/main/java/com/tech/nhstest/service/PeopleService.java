package com.tech.nhstest.service;

import com.tech.nhstest.model.People;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Responsible for processing the data.
 */
@Service
public class PeopleService {

    //This is temporary code as the data is small. This could be replaced by persistance layer if the data needs to be stored permanently.
    private static List<People> peopleList = new ArrayList<>();

    public List<People> getAllPeople() {
        return peopleList;
    }

    public People getPeopleById(String inputId) {
        People people = null;
        Optional<People> peopleOptional = peopleList.stream().filter(p -> p.getId().equalsIgnoreCase(inputId)).findFirst();
        if (peopleOptional.isPresent()) {
            people = peopleOptional.get();
        }
        return people;
    }

    public void addPeople(List<People> inputPeopleList) {
        peopleList.addAll(inputPeopleList);
    }

    public void updatePeople(People newPeople, String id) {
        for (int i = 0; i < peopleList.size(); i++) {
            People existingPeople = peopleList.get(i);
            if (isRecordExisting(id, existingPeople)) {
                peopleList.set(i, newPeople);
            }
        }
    }

    private boolean isRecordExisting(String id, People existingPeople) {
        return existingPeople.getId().equalsIgnoreCase(id);
    }

    public void deletePeople(String id) {
        peopleList.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }
}
