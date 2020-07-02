package com.sanjit.peopletracking.controller;

import com.sanjit.peopletracking.dto.LocationDTO;
import com.sanjit.peopletracking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/location-save")
    public ResponseEntity<?> saveLocation(@RequestBody LocationDTO locationDTO){
        return new ResponseEntity<>(locationService.saveLocation(locationDTO), HttpStatus.OK);
    }

    @GetMapping("/location/all")
    public ResponseEntity<?> findAllLocation(){
        return new ResponseEntity<>(locationService.findAllLocation(), HttpStatus.OK);
    }

    @GetMapping("/location/pagination/all")
    public ResponseEntity<?> getAllLocation(@RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy){
        Map locationList = locationService.findLocation(pageNo-1, pageSize, sortBy);

        return new ResponseEntity<>(locationList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/location/id/{id}")
    public ResponseEntity<?> locationById(@PathVariable("id") Long id){
        return new ResponseEntity<>(locationService.findByLocationId(id), HttpStatus.OK);
    }
}
