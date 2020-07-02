package com.sanjit.peopletracking.mapper;

import com.sanjit.peopletracking.dto.PeopleDTO;
import com.sanjit.peopletracking.entity.People;
import com.sanjit.peopletracking.utility.EntityMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface PeopleMapper extends EntityMapper<PeopleDTO, People> {

    PeopleDTO toDto(People people);

    People toEntity(PeopleDTO peopleDTO);
}
