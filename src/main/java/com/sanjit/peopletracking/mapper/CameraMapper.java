package com.sanjit.peopletracking.mapper;

import com.sanjit.peopletracking.dto.CameraDTO;
import com.sanjit.peopletracking.entity.Camera;
import com.sanjit.peopletracking.utility.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface CameraMapper extends EntityMapper<CameraDTO, Camera> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.streetAddress", target = "locationName")
    CameraDTO toDto(Camera camera);

    @Mapping(source = "locationId", target = "location.id")
    Camera toEntity(CameraDTO cameraDTO);
}
