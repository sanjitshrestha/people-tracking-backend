package com.sanjit.peopletracking;

import com.sanjit.peopletracking.repository.RoleRepository;
import com.sanjit.peopletracking.repository.UserRepository;
import com.sanjit.peopletracking.security.User;
import com.sanjit.peopletracking.enumconstant.Privilege;
import com.sanjit.peopletracking.enumconstant.Status;
import com.sanjit.peopletracking.security.Role;
import com.sanjit.peopletracking.security.UserRole;
import com.sanjit.peopletracking.utility.AuthorityType;
import com.sanjit.peopletracking.utility.SecurityUtility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class PeopleTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleTrackingApplication.class, args);
	}


	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {

		return args -> {

			Role adminRole = createRole(roleRepository, "ADMIN");
			Role userRole = createRole(roleRepository, "USER");


			User user = userRepository.findByEmail("tracking@person.com");
			if (user == null) {
				User adminUser = new User();
				adminUser.setEmail("tracking@person.com");
				adminUser.setMobile("admin");
				adminUser.setUsername(adminUser.getMobile());
				adminUser.setPassword(passwordEncoder().encode("admin"));
				adminUser.setUserType(AuthorityType.ADMIN);
				Set<UserRole> userRoles = new HashSet<>();
				userRoles.add(new UserRole(adminUser, adminRole));
				userRoles.add(new UserRole(adminUser, userRole));
				adminUser.setUserRoles(userRoles);
				adminUser = userRepository.save(adminUser);
			}
		};

	}


	private Role createRole(RoleRepository roleRepository, String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {

			Set<Privilege> privilegeSet = new HashSet<>();

			privilegeSet.addAll(Arrays.asList(Privilege.values()));

			role = new Role();

			role.setStatus(Status.ACTIVE);
			role.setName(name);
			role.setPermissionSet(privilegeSet);

			return roleRepository.save(role);

		} else if (role.getPermissionSet().size() != Arrays.asList(Privilege.values()).size()) {
			Set<Privilege> privilegeSet = new HashSet<>();

			privilegeSet.addAll(Arrays.asList(Privilege.values()));

			role.setPermissionSet(privilegeSet);

			return roleRepository.save(role);
		}
		return role;
	}

}
