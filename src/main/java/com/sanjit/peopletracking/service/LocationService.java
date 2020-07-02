package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.LocationDTO;

import java.util.List;
import java.util.Map;

public interface LocationService {

    LocationDTO saveLocation(LocationDTO locationDTO);

    LocationDTO findByLocationId(Long id);

    List<LocationDTO> findAllLocation();

    /**
     * Get all the Location.
     *
     * server-side pagination i.e. pageNo define number of current page
     * pageSize define size of data in to be listed per page
     * @return the list of entities
     */
    Map findLocation(Integer pageNo, Integer pageSize, String sortBy);
}
