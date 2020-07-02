package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.LocationDTO;
import com.sanjit.peopletracking.entity.Location;
import com.sanjit.peopletracking.mapper.LocationMapper;
import com.sanjit.peopletracking.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public LocationDTO saveLocation(LocationDTO locationDTO) {
        log.debug("Request to save/update Location : {}", locationDTO);

        Location location = locationMapper.toEntity(locationDTO);
        return locationMapper.toDto(locationRepository.save(location));
    }

    @Override
    public LocationDTO findByLocationId(Long id) {
        log.debug("Request to get Location by id : {}", id);

        Location location = locationRepository.findLocationById(id);
        return locationMapper.toDto(location);
    }

    @Override
    public List<LocationDTO> findAllLocation() {
        log.debug("Request to get All Location");

        return locationMapper.toDto(locationRepository.findAll());
    }

    @Override
    public Map findLocation(Integer pageNo, Integer pageSize, String sortBy) {
        log.debug("Request to get Location for Pagination");

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Location> locationList = locationRepository.getAllLocation(pageable);
        Page<LocationDTO> locationDTOList = locationList.map(list -> conveterLocaitonToDto(list));

        Map map = new HashMap();

        map.put("totalElement", locationDTOList.getContent());
        map.put("currentPage", locationDTOList.getNumber());
        map.put("totalPage", locationDTOList.getTotalElements());
        if (locationDTOList.hasContent()) {
            return map;
        } else {
            return new HashMap();
        }
    }

    private LocationDTO conveterLocaitonToDto(Location location) {
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setId(location.getId());
        locationDTO.setCountryName(location.getCountryName());
        locationDTO.setStateName(location.getStateName());
        locationDTO.setCity(location.getCity());
        locationDTO.setZip(location.getZip());
        locationDTO.setStreetAddress(location.getStreetAddress());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setVersion(location.getVersion());

        if ( location.getStatus() != null ) {
            locationDTO.setStatus( location.getStatus().name() );
        }
        return locationDTO;
    }
}
