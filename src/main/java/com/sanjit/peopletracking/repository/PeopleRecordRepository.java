package com.sanjit.peopletracking.repository;

import com.sanjit.peopletracking.entity.PeopleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRecordRepository extends JpaRepository<PeopleRecord, Long> {

    PeopleRecord findPeopleRecordById(Long id);

    @Query("select p from PeopleRecord p where p.id is not null order by p.id desc")
    Page<PeopleRecord> getAllPeopleRecord(Pageable pageable);

}
