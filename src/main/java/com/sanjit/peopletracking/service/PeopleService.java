package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.PeopleDTO;

import java.util.List;
import java.util.Map;

public interface PeopleService {

    PeopleDTO savePeople(PeopleDTO peopleDTO);

    PeopleDTO findByPeopleId(Long id);

    List<PeopleDTO> findAllPeople();

    Map findPeople(Integer pageNo, Integer pageSize, String sortBy);
}
