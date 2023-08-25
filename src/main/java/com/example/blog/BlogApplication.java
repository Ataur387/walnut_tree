package com.example.blog;

import com.example.blog.DTO.RoleDTO;
import com.example.blog.Entity.*;
import com.example.blog.Repositories.BillingAddressRepository;
import com.example.blog.Repositories.RoleRepository;
import com.example.blog.Repositories.UserRepository;
import com.example.blog.Services.RoleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogApplication {


	@PersistenceContext
	private EntityManager entityManager;

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final RoleService roleService;
	private final BillingAddressRepository billingAddressRepository;
	public BlogApplication(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService, BillingAddressRepository billingAddressRepository){
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.roleService = roleService;
		this.billingAddressRepository = billingAddressRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner commandLineRunner() {
		return args -> {
			//Creating a dummy Billing Address
			if(billingAddressRepository.count() == 0){
				BillingAddressEntity billingAddress = new BillingAddressEntity();
				billingAddress.setAddress("Mohakhali DOHS");
				billingAddress.setCity("Dhaka");
				billingAddress.setPostCode("1234");
				billingAddressRepository.save(billingAddress);
			}

			//Creating Roles
			if(roleRepository.count() == 0){
				RoleEntity adminRole = new RoleEntity();
				adminRole.setRoleType(RoleType.ADMIN);
				roleRepository.save(adminRole);
				RoleEntity commmenterRole = new RoleEntity();
				commmenterRole.setRoleType(RoleType.COMMENTER);
				roleRepository.save(commmenterRole);
				RoleEntity bloggerRole = new RoleEntity();
				bloggerRole.setRoleType(RoleType.BLOGGER);
				roleRepository.save(bloggerRole);
			}
			//Create a Normal User
			if(userRepository.count() == 0){
				UserEntity normalUser = new UserEntity();
				normalUser.setId(100L);
				normalUser.setUserName("Nazmul123");
				normalUser.setFirstName("Ahsan");
				normalUser.setLastName("nazmul");
				normalUser.setPassword("aaaa");
				normalUser.setUserStatus(UserStatus.ACTIVE);
				normalUser = userRepository.save(normalUser);
				RoleDTO commenterRole = new RoleDTO();
				commenterRole.setRoleType(RoleType.COMMENTER);
				List<RoleEntity> roleEntities = roleService.findRoles(commenterRole).getContent();
				List<RoleEntity> savedRoles = new ArrayList<>();
				for (RoleEntity roleEntity : roleEntities) {
					roleEntity.setUser(normalUser);
					savedRoles.add(roleRepository.save(roleEntity));
				}
				normalUser.setRolesList(savedRoles);
				userRepository.save(normalUser);
			}
//			Create Admin User
			if (userRepository.count() == 0) {
				ProUserEntity adminUser = new ProUserEntity();
				adminUser.setUserName("admin");
				adminUser.setFirstName("Super");
				adminUser.setLastName("Admin");
				adminUser.setUserStatus(UserStatus.ACTIVE);
				adminUser = userRepository.save(adminUser);
				//Create RoleList
				RoleDTO adminRole = new RoleDTO();
				adminRole.setRoleType(RoleType.ADMIN);
				List<RoleEntity> roleEntities = roleService.findRoles(adminRole).getContent();
				List<RoleEntity> savedRoles = new ArrayList<>();
				for (RoleEntity roleEntity : roleEntities) {
					roleEntity.setUser(adminUser);
					savedRoles.add(roleRepository.save(roleEntity));
				}
				adminUser.setRolesList(savedRoles);
				userRepository.save(adminUser);
			}
		};
	}
}
