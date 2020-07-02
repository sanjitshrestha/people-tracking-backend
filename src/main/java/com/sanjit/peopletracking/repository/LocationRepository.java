package com.sanjit.peopletracking.repository;

import com.sanjit.peopletracking.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findLocationById(Long id);

    @Query("select l from Location  l where l.id is not null order by l.id desc")
    Page<Location> getAllLocation(Pageable pageable);
}
