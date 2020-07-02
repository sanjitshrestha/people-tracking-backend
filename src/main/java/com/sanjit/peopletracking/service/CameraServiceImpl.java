package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.CameraDTO;
import com.sanjit.peopletracking.dto.LocationDTO;
import com.sanjit.peopletracking.entity.Camera;
import com.sanjit.peopletracking.entity.Location;
import com.sanjit.peopletracking.mapper.CameraMapper;
import com.sanjit.peopletracking.repository.CameraRepository;
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
public class CameraServiceImpl implements CameraService {

    private final Logger log = LoggerFactory.getLogger(CameraServiceImpl.class);

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraMapper cameraMapper;

    @Override
    public CameraDTO saveCamera(CameraDTO cameraDTO) {
        log.debug("Request to save/update Camera : {}", cameraDTO);

        Camera camera = cameraMapper.toEntity(cameraDTO);
        return cameraMapper.toDto(cameraRepository.save(camera));
    }

    @Override
    public CameraDTO findCameraId(Long id) {
        log.debug("Request to get Camera by id : {}", id);

        Camera camera = cameraRepository.findCameraById(id);
        return cameraMapper.toDto(camera);
    }

    @Override
    public List<CameraDTO> findAllCamera() {
        log.debug("Request to get All Camera");

        return cameraMapper.toDto(cameraRepository.findAll());
    }

    @Override
    public List<CameraDTO> getCameraByLocationId(Long id) {
        log.debug("Request all camera by location");

        List<Camera> cameraList = cameraRepository.getCameraByLocationId(id);
        return cameraMapper.toDto(cameraList);
    }

    @Override
    public Map findCamera(Integer pageNo, Integer pageSize, String sortBy) {
        log.debug("Request to get Camera for Pagination");

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Camera> cameraList = cameraRepository.getAllCamera(pageable);
        Page<CameraDTO> cameraDTOList = cameraList.map(list -> converterCameraToDto(list));

        Map map = new HashMap();

        map.put("totalElement", cameraDTOList.getContent());
        map.put("currentPage", cameraDTOList.getNumber());
        map.put("totalPage", cameraDTOList.getTotalElements());
        if (cameraDTOList.hasContent()) {
            return map;
        } else {
            return new HashMap();
        }
    }

    private CameraDTO converterCameraToDto(Camera camera) {
        CameraDTO cameraDTO = new CameraDTO();

        cameraDTO.setId(camera.getId());
        cameraDTO.setCameraName(camera.getCameraName());
        cameraDTO.setCameraCode(camera.getCameraCode());
        cameraDTO.setDirection(camera.getDirection());
        cameraDTO.setLocationId(camera.getLocation().getId());
        cameraDTO.setLocationName(camera.getLocation().getStreetAddress());
        cameraDTO.setVersion(camera.getVersion());

        if ( camera.getStatus() != null ) {
            cameraDTO.setStatus( camera.getStatus().name() );
        }
        return cameraDTO;

    }
}
