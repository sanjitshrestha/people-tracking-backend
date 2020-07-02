package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.UserDTO;
import com.sanjit.peopletracking.dto.UserRoleDTO;
import com.sanjit.peopletracking.mapper.RoleMapper;
import com.sanjit.peopletracking.mapper.UserRoleMapper;
import com.sanjit.peopletracking.repository.UserRepository;
import com.sanjit.peopletracking.repository.UserRoleRepository;
import com.sanjit.peopletracking.security.User;
import com.sanjit.peopletracking.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserRoleDTO> findUserRoleByUser(UserDTO userDTO) {
        User user = userRepository.findUserByUsername(userDTO.getUsername());
        List<UserRole> userRoleList = userRoleRepository.findByUser(user);
        List<UserRoleDTO> userRoleDto = new ArrayList<>();
        UserRoleDTO userRoleDto1 = new UserRoleDTO();
        for (UserRole userRole : userRoleList) {
            userRoleDto1.setRoleDTO(roleMapper.toDto(userRole.getRole()));
        }
        userRoleDto.add(userRoleDto1);
        return userRoleDto;
    }

    @Override
    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
        UserRole userRole = userRoleMapper.toEntity(userRoleDTO);
        return userRoleMapper.toDto(userRoleRepository.save(userRole));
    }
}
