package com.sanjit.peopletracking.controller;

import com.sanjit.peopletracking.dto.PeopleDTO;
import com.sanjit.peopletracking.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/people-save")
    public ResponseEntity<?> savePeople(@RequestBody PeopleDTO peopleDTO) {
        return new ResponseEntity<>(peopleService.savePeople(peopleDTO), HttpStatus.OK);
    }

    @GetMapping("/people/all")
    public ResponseEntity<?> findAllPeople() {
        return new ResponseEntity<>(peopleService.findAllPeople(), HttpStatus.OK);
    }

    @GetMapping("/people/pagination/all")
    public ResponseEntity<?> getAllPeople(@RequestParam(defaultValue = "1") Integer pageNo,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(defaultValue = "id") String sortBy) {
        Map peopleList = peopleService.findPeople(pageNo - 1, pageSize, sortBy);

        return new ResponseEntity<>(peopleList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/people/id/{id}")
    public ResponseEntity<?> findPeopleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(peopleService.findByPeopleId(id), HttpStatus.OK);
    }
}
