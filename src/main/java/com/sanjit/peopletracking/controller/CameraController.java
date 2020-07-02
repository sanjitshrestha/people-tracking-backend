package com.sanjit.peopletracking.controller;

import com.sanjit.peopletracking.dto.CameraDTO;
import com.sanjit.peopletracking.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @PostMapping("/camera-save")
    public ResponseEntity<?> saveCamera(@RequestBody CameraDTO cameraDTO) {
        return new ResponseEntity<>(cameraService.saveCamera(cameraDTO), HttpStatus.OK);
    }

    @GetMapping("/camera/all")
    public ResponseEntity<?> findAllCamera() {
        return new ResponseEntity<>(cameraService.findAllCamera(), HttpStatus.OK);
    }

    @GetMapping("/camera/pagination/all")
    public ResponseEntity<?> getAllCamera(@RequestParam(defaultValue = "1") Integer pageNo,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(defaultValue = "id") String sortBy) {
        Map cameraList = cameraService.findCamera(pageNo - 1, pageSize, sortBy);

        return new ResponseEntity<>(cameraList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/camera/id/{id}")
    public ResponseEntity<?> cameraById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cameraService.findCameraId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/location/filter/{id}")
    public ResponseEntity<?> findCameraByLocation(@PathVariable Long id) {
        return new ResponseEntity<>(cameraService.getCameraByLocationId(id), HttpStatus.OK);
    }
}
