package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.dto.UserDTO;
import com.sanjit.peopletracking.mapper.UserMapper;
import com.sanjit.peopletracking.repository.UserRepository;
import com.sanjit.peopletracking.security.User;
import com.sanjit.peopletracking.utility.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserDTO userDTO = userMapper.toDto(userRepository.findUserByUsername(username));
        return userDTO;
    }

    @Override
    public UserDTO findUserByUserId(Long id) {
        User user = userRepository.findUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.findUserById(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserType(Enum.valueOf(AuthorityType.class, userDTO.getUserType()));
        user.setEnabled(Boolean.parseBoolean(userDTO.getEnabled()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public List<String> findAllUsername() {
        return userRepository.getUserNameList();
    }
}
