package com.sanjit.peopletracking.service;

import com.sanjit.peopletracking.repository.RoleRepository;
import com.sanjit.peopletracking.repository.UserRepository;
import com.sanjit.peopletracking.security.Authority;
import com.sanjit.peopletracking.security.Role;
import com.sanjit.peopletracking.security.User;
import com.sanjit.peopletracking.security.UserRole;
import com.sanjit.peopletracking.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    public User findUserByEmail(String email) {
        return userService.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        Role role = roleRepository.findByName("ADMIN");

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        user.setUserRoles(userRoles);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUserName(username);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getUserRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<UserRole> userRoles) {

        List<GrantedAuthority> authorites = new ArrayList<>();
        userRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));

        return authorites;

    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getMobile(), user.getPassword(), authorities);
    }
}
