package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.RoleDTO;
import com.sanjit.peopletracking.enumconstant.Status;
import com.sanjit.peopletracking.mapper.RoleMapper;
import com.sanjit.peopletracking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleDTO> findAllRoles(Status status) {
        return roleMapper.toDto(roleRepository.findAllByStatus(status));
    }

    @Override
    public RoleDTO getRoleByName(String role) {
        return roleMapper.toDto(roleRepository.findByName(role));
    }
}
