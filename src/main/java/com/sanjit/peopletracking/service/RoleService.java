package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.RoleDTO;
import com.sanjit.peopletracking.enumconstant.Status;

import java.util.List;

public interface RoleService {

    List<RoleDTO> findAllRoles(Status status);

    RoleDTO getRoleByName(String role);
}
