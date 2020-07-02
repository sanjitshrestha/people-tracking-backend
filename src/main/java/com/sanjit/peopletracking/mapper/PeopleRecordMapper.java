package com.sanjit.peopletracking.mapper;

import com.sanjit.peopletracking.dto.PeopleRecordDTO;
import com.sanjit.peopletracking.entity.PeopleRecord;
import com.sanjit.peopletracking.utility.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface PeopleRecordMapper extends EntityMapper<PeopleRecordDTO, PeopleRecord> {

    @Mapping(source = "camera.id", target = "cameraId")
    @Mapping(source = "camera.cameraName", target = "cameraName")
    @Mapping(source = "people.id", target = "peopleId")
    @Mapping(source = "people.peopleName", target = "peopleName")
    PeopleRecordDTO toDto(PeopleRecord peopleRecord);

    @Mapping(source = "cameraId", target = "camera.id")
    @Mapping(source = "peopleId", target = "people.id")
    PeopleRecord toEntity(PeopleRecordDTO peopleRecordDTO);
}
