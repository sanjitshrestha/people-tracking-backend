package com.sanjit.peopletracking.mapper;

import com.sanjit.peopletracking.dto.UserRoleDTO;
import com.sanjit.peopletracking.security.UserRole;
import com.sanjit.peopletracking.utility.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface UserRoleMapper extends EntityMapper<UserRoleDTO, UserRole> {

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "role", target = "roleDTO")
    UserRoleDTO toDto(UserRole userRole);

    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "roleDTO", target = "role")
    UserRole toEntity(UserRoleDTO userRoleDTO);
}
