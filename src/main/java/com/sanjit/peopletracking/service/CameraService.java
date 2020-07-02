package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.CameraDTO;

import java.util.List;
import java.util.Map;

public interface CameraService {

    CameraDTO saveCamera(CameraDTO cameraDTO);

    CameraDTO findCameraId(Long id);

    List<CameraDTO> findAllCamera();

    List<CameraDTO> getCameraByLocationId(Long id);

    Map findCamera(Integer pageNo, Integer pageSize, String sortBy);
}
