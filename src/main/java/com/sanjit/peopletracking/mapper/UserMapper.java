package com.sanjit.peopletracking.mapper;

import com.sanjit.peopletracking.dto.UserDTO;
import com.sanjit.peopletracking.security.User;
import com.sanjit.peopletracking.utility.EntityMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
