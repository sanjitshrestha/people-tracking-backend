package com.sanjit.peopletracking.mapper;

import com.sanjit.peopletracking.dto.RoleDTO;
import com.sanjit.peopletracking.security.Role;
import com.sanjit.peopletracking.utility.EntityMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    RoleDTO toDto(Role role);

    Role toEntity(RoleDTO roleDTO);
}
