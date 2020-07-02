package com.sanjit.peopletracking.controller;

import com.sanjit.peopletracking.dto.PeopleRecordDTO;
import com.sanjit.peopletracking.service.PeopleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PeopleRecordController {

    @Autowired
    private PeopleRecordService peopleRecordService;

    @PostMapping("/people-record/save")
    public ResponseEntity<?> savePeopleRecord(@RequestBody PeopleRecordDTO peopleRecordDTO) {
        return new ResponseEntity<>(peopleRecordService.savePeopleRecord(peopleRecordDTO), HttpStatus.OK);
    }

    @GetMapping("/people-record/all")
    public ResponseEntity<?> findAllPeoplrRecord() {
        return new ResponseEntity<>(peopleRecordService.findAllPeopleRecord(), HttpStatus.OK);
    }

    @GetMapping("/people-record/pagination/all")
    public ResponseEntity<?> getAllPeopleRecord(@RequestParam(defaultValue = "1") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        Map peopleRecordList = peopleRecordService.findPeopleRecord(pageNo - 1, pageSize, sortBy);
        return new ResponseEntity<>(peopleRecordList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/people-record/id/{id}")
    public ResponseEntity<?> peopleRecordById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(peopleRecordService.findPeopleRecordId(id), HttpStatus.OK);
    }
}
