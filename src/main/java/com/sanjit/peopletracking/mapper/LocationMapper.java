package com.sanjit.peopletracking.mapper;

import com.sanjit.peopletracking.dto.LocationDTO;
import com.sanjit.peopletracking.entity.Location;
import com.sanjit.peopletracking.utility.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring", uses = {CameraMapper.class})
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {

    @Mapping(source = "cameraList", target = "cameraDTOList")
    LocationDTO toDto(Location location);

    Location toEntity(LocationDTO locationDTO);
}
