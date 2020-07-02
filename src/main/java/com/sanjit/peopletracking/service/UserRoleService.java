package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.UserDTO;
import com.sanjit.peopletracking.dto.UserRoleDTO;

import java.util.List;

public interface UserRoleService {

    List<UserRoleDTO> findUserRoleByUser(UserDTO userDTO);

    UserRoleDTO save(UserRoleDTO userRoleDTO);
}
