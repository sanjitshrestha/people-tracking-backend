package com.sanjit.peopletracking.repository;

import com.sanjit.peopletracking.enumconstant.Status;
import com.sanjit.peopletracking.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Query("select r from Role r where r.status=?1")
    List<Role> findAllByStatus(Status status);
}
