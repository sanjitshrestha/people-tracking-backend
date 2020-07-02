package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.PeopleRecordDTO;
import com.sanjit.peopletracking.entity.PeopleRecord;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public interface PeopleRecordService {

    PeopleRecordDTO savePeopleRecord(PeopleRecordDTO peopleRecordDTO);

    PeopleRecordDTO findPeopleRecordId(Long id);

    List<PeopleRecordDTO> findAllPeopleRecord();

    Map findPeopleRecord(Integer pageNo, Integer pageSize, String sortBy);
}
