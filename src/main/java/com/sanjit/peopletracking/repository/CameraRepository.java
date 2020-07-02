package com.sanjit.peopletracking.repository;

import com.sanjit.peopletracking.dto.CameraDTO;
import com.sanjit.peopletracking.entity.Camera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

    Camera findCameraById(Long id);

    @Query("select c from Camera c where c.id is not null order by c.id desc")
    Page<Camera> getAllCamera(Pageable pageable);

    List<Camera> getCameraByLocationId(Long id);
}
