package com.sanjit.peopletracking.repository;

import com.sanjit.peopletracking.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByMobile(String mobile);

    User findUserById(Long id);

    User findUserByUsername(String username);

    @Query("select u.username from  User u ")
    List<String> getUserNameList();
}
