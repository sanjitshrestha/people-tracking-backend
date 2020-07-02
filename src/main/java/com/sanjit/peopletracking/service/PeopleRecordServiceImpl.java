package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.PeopleRecordDTO;
import com.sanjit.peopletracking.dto.PeopleRecordDTO;
import com.sanjit.peopletracking.entity.PeopleRecord;
import com.sanjit.peopletracking.entity.PeopleRecord;
import com.sanjit.peopletracking.mapper.PeopleRecordMapper;
import com.sanjit.peopletracking.repository.PeopleRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeopleRecordServiceImpl implements PeopleRecordService {

    private final Logger log = LoggerFactory.getLogger(PeopleRecordServiceImpl.class);

    @Autowired
    private PeopleRecordRepository peopleRecordRepository;

    @Autowired
    private PeopleRecordMapper peopleRecordMapper;

    @Override
    public PeopleRecordDTO savePeopleRecord(PeopleRecordDTO peopleRecordDTO) {
        log.debug("Request to save/update people record : {}", peopleRecordDTO);

        PeopleRecord peopleRecord = peopleRecordMapper.toEntity(peopleRecordDTO);
        return peopleRecordMapper.toDto(peopleRecordRepository.save(peopleRecord));
    }

    @Override
    public PeopleRecordDTO findPeopleRecordId(Long id) {
        log.debug("Request to get PeopleRecord by id : {}", id);

        PeopleRecord peopleRecord = peopleRecordRepository.findPeopleRecordById(id);
        return peopleRecordMapper.toDto(peopleRecord);
    }

    @Override
    public List<PeopleRecordDTO> findAllPeopleRecord() {
        log.debug("Request to get All People Record");

        return peopleRecordMapper.toDto(peopleRecordRepository.findAll());
    }

    @Override
    public Map findPeopleRecord(Integer pageNo, Integer pageSize, String sortBy) {
        log.debug("Request to get PeopleRecord for Pagination");

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<PeopleRecord> peopleRecordList = peopleRecordRepository.getAllPeopleRecord(pageable);
        Page<PeopleRecordDTO> peopleRecordDTOList = peopleRecordList.map(list -> converterPeopleRecordToDto(list));

        Map map = new HashMap();

        map.put("totalElement", peopleRecordDTOList.getContent());
        map.put("currentPage", peopleRecordDTOList.getNumber());
        map.put("totalPage", peopleRecordDTOList.getTotalElements());
        if (peopleRecordDTOList.hasContent()) {
            return map;
        } else {
            return new HashMap();
        }
    }

    private PeopleRecordDTO converterPeopleRecordToDto(PeopleRecord peopleRecord) {
        PeopleRecordDTO peopleRecordDTO = new PeopleRecordDTO();

        peopleRecordDTO.setId(peopleRecord.getId());
        peopleRecordDTO.setDescription(peopleRecord.getDescription());
        peopleRecordDTO.setCameraId(peopleRecord.getCamera().getId());
        peopleRecordDTO.setCameraName(peopleRecord.getCamera().getCameraName());
        peopleRecordDTO.setPeopleId(peopleRecord.getPeople().getId());
        peopleRecordDTO.setPeopleName(peopleRecord.getPeople().getPeopleName());
        peopleRecord.setVersion(peopleRecord.getVersion());
        if ( peopleRecord.getStatus() != null ) {
            peopleRecordDTO.setStatus( peopleRecord.getStatus().name() );
        }
        return peopleRecordDTO;
    }
}
