package com.sanjit.peopletracking.repository;

import com.sanjit.peopletracking.entity.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    People findPeopleById(Long id);

    @Query("select p from People p where p.id is not null order by p.id desc")
    Page<People> getAllPeople(Pageable pageable);
}
