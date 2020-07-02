package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.UserDTO;
import com.sanjit.peopletracking.security.User;

import java.util.List;

public interface UserService {

    User findUserByUserName(String userName);

    UserDTO getUserByUsername(String username);

    UserDTO findUserByUserId(Long id);

    User findByEmail(String email);

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findUserById(Long id);

    List<String> findAllUsername();
}
