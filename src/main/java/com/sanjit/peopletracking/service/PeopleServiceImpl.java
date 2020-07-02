package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.PeopleDTO;
import com.sanjit.peopletracking.entity.People;
import com.sanjit.peopletracking.mapper.PeopleMapper;
import com.sanjit.peopletracking.repository.PeopleRepository;
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
public class PeopleServiceImpl implements PeopleService {

    private final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public PeopleDTO savePeople(PeopleDTO peopleDTO) {
        log.debug("Request to save/update people : {}", peopleDTO);

        People people = peopleMapper.toEntity(peopleDTO);
        return peopleMapper.toDto(peopleRepository.save(people));
    }

    @Override
    public PeopleDTO findByPeopleId(Long id) {
        log.debug("Request to find people by id : {}", id);

        People people = peopleRepository.findPeopleById(id);
        return peopleMapper.toDto(people);
    }

    @Override
    public List<PeopleDTO> findAllPeople() {
        log.debug("Request to find all people");

        return peopleMapper.toDto(peopleRepository.findAll());
    }

    @Override
    public Map findPeople(Integer pageNo, Integer pageSize, String sortBy) {
        log.debug("Request to get people for Pagination");

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<People> peopleList = peopleRepository.getAllPeople(pageable);
        Page<PeopleDTO> peopleDTOList = peopleList.map(list -> conveterPeopleToDto(list));

        Map map = new HashMap();

        map.put("totalElement", peopleDTOList.getContent());
        map.put("currentPage", peopleDTOList.getNumber());
        map.put("totalPage", peopleDTOList.getTotalElements());
        if (peopleDTOList.hasContent()) {
            return map;
        } else {
            return new HashMap();
        }
    }

    private PeopleDTO conveterPeopleToDto(People people) {
        PeopleDTO peopleDTO = new PeopleDTO();

        peopleDTO.setId(people.getId());
        peopleDTO.setPeopleName(people.getPeopleName());
        peopleDTO.setPeopleLocation(people.getPeopleLocation());
        peopleDTO.setPhoneNumber(people.getPhoneNumber());
        peopleDTO.setEmail(people.getEmail());
        peopleDTO.setVersion(people.getVersion());

        if ( people.getStatus() != null ) {
            peopleDTO.setStatus( people.getStatus().name() );
        }
        return peopleDTO;

    }
}
