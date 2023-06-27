package com.arcesi.cliniquemedical.jdd;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.cliniquemedical.security.entities.PrivilegeBean;
import com.arcesi.cliniquemedical.security.entities.RoleBean;
import com.arcesi.cliniquemedical.security.entities.UserBean;
import com.arcesi.cliniquemedical.security.enums.AppUserRole;
import com.arcesi.cliniquemedical.security.enums.Permission;
import com.arcesi.cliniquemedical.security.repositories.PrivilegeBeanRepository;
import com.arcesi.cliniquemedical.security.repositories.RoleBeanRepository;
import com.arcesi.cliniquemedical.security.repositories.UserBeanRepository;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Transactional
public class JddSecurityTest implements CommandLineRunner {

	@Autowired
	private UserBeanRepository userRepository;
	@Autowired
	private RoleBeanRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private PrivilegeBeanRepository privilegeBeanRepository;

	@SuppressWarnings("unused")
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAllInBatch();
		roleRepository.deleteAllInBatch();
		privilegeBeanRepository.deleteAllInBatch();

		addPrivilege();
		addRoles();
		AddMedecin();
		AddPatient();
		AddSecretaire();
		AddAMS();
		AddAdmin();
	     
		UserBean user=userRepository.findUserBeanByEmail("tibarinewdzign12@gmail.com").get();
		RoleBean roleAdmin=roleRepository.findRoleBeanByRoleName(AppUserRole.ADMINISTRATEUR.getId()).get();
		
		user.getRoleBeans().add(roleAdmin);
		UserBean lastUser=userRepository.save(user);
	}

	private void AddAdmin() {
		/***** add ADMIN *****/
		UserBean userAdmin = UserBean.builder().createdAt(Instant.now()).updatedAt(null)
				.email("zeroualsouad@gmail.com").uidUser(UUID.randomUUID().toString().replace("-", ""))
				.firstName("zeroual").lastName("souad")
				.tokenActivationEmail(UUID.randomUUID().toString())
				.expiresAt(LocalDateTime.now().plusDays(7))
				.roleBeans(new HashSet<>()).password(bCryptPasswordEncoder.encode("Boudarga1980@")).build();
		UserBean userAdminBDD = userRepository.save(userAdmin);
		@SuppressWarnings("unused")
		Optional<UserBean> userAdminSaved = userRepository.findUserBeanByEmail("zeroualsouad@gmail.com");
		Optional<RoleBean> roleADMIN=roleRepository.findRoleBeanByRoleName(AppUserRole.ADMINISTRATEUR.getId());
	    userAdminBDD.getRoleBeans().add(roleADMIN.get());
	        
	     Set<PrivilegeBean> privilegeAdmin=privilegeBeanRepository.findAll().stream().filter(p->p.getName().startsWith("admin")).collect(Collectors.toSet());
		 roleADMIN.get().setPrivilegeBeans(privilegeAdmin);
	}

	@SuppressWarnings("unused")
	private void AddAMS() {
		/***** add AMS *****/
		UserBean userAMS = UserBean.builder().createdAt(Instant.now()).updatedAt(null)
				.email("zeroual.t@gmail.com").uidUser(UUID.randomUUID().toString().replace("-", ""))
				.firstName("zeroual").lastName("tarik")
				.tokenActivationEmail(UUID.randomUUID().toString())
				.expiresAt(LocalDateTime.now().plusDays(7))
				.roleBeans(new HashSet<>()).password(bCryptPasswordEncoder.encode("Boudarga1980@")).build();
		UserBean userAMSBDD = userRepository.save(userAMS);
		Optional<UserBean> userAMSSaved = userRepository.findUserBeanByEmail("zeroual.t@gmail.com");

		Optional<RoleBean> roleAss=roleRepository.findRoleBeanByRoleName(AppUserRole.ASSISTANTE_MEDICO_SOCIALE.getId());
	    userAMSBDD.getRoleBeans().add(roleAss.get());
	        
	     Set<PrivilegeBean> privilegeAMS=privilegeBeanRepository.findAll().stream().filter(p->p.getName().startsWith("assitant-medico-social")).collect(Collectors.toSet());
		 roleAss.get().setPrivilegeBeans(privilegeAMS);
	}

	@SuppressWarnings("unused")
	private void AddSecretaire() {
		/***** add Secritaire *****/
		UserBean userSecritaire = UserBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.email("zeroualhamza@gmail.com")
				.uidUser(UUID.randomUUID().toString().replace("-", ""))
				.firstName("zeroual")
				.lastName("hamza")
				.tokenActivationEmail(UUID.randomUUID().toString())
				.expiresAt(LocalDateTime.now().plusDays(7))
				.roleBeans(new HashSet<>())
				.password(bCryptPasswordEncoder.encode("Boudarga1980@")).build();
		UserBean ubSecritaire = userRepository.save(userSecritaire);
		Optional<UserBean> userSecriSaved = userRepository.findUserBeanByEmail("zeroualhamza@gmail.com");

		 Optional<RoleBean> roleSecritaire=roleRepository.findRoleBeanByRoleName(AppUserRole.SECRETAIRE.getId());
	     ubSecritaire.getRoleBeans().add(roleSecritaire.get());
	        
	     Set<PrivilegeBean> privilegeSecriataire=privilegeBeanRepository.findAll().stream().filter(p->p.getName().startsWith("secretaire")).collect(Collectors.toSet());
		 roleSecritaire.get().setPrivilegeBeans(privilegeSecriataire);
	}

	private void AddPatient() {
		/*** add Patient ************/
		UserBean userPatient = UserBean.builder().createdAt(Instant.now()).updatedAt(null).email("kazzarlyna120@gmail.com")
				.uidUser(UUID.randomUUID().toString().replace("-", ""))
				.firstName("kazzar").lastName("lyna")
				.tokenActivationEmail(UUID.randomUUID().toString())
				.expiresAt(LocalDateTime.now().plusDays(7))
				.roleBeans(new HashSet<>())
				.password(bCryptPasswordEncoder.encode("Boudarga1980@")).build();
		UserBean uPatient = userRepository.save(userPatient);
		Optional<RoleBean> rolePatient=roleRepository.findRoleBeanByRoleName(AppUserRole.PATIENT.getId());
        uPatient.getRoleBeans().add(rolePatient.get());
        
        Set<PrivilegeBean> privilegePatient=privilegeBeanRepository.findAll().stream().filter(p->p.getName().startsWith("patient")).collect(Collectors.toSet());
		rolePatient.get().setPrivilegeBeans(privilegePatient);
	}

	private void AddMedecin() {
		/********************* MEDECIN **/
		UserBean userMedecin = UserBean.builder().createdAt(Instant.now()).updatedAt(null).email("tibarinewdzign12@gmail.com")
				.uidUser(UUID.randomUUID().toString().replace("-", "")).firstName("zeroual").lastName("tibari")
				.tokenActivationEmail(UUID.randomUUID().toString())
				.expiresAt(LocalDateTime.now().plusDays(7))
				.roleBeans(new HashSet<>())
				.password(bCryptPasswordEncoder.encode("Boudarga1980@")).build();
		UserBean userMedec = userRepository.save(userMedecin);
		//Optional<UserBean> uMedecinSaved = userRepository.findUserBeanByEmail("tibarinewdzign@gmail.com");

		Optional<RoleBean> roleMedecin=roleRepository.findRoleBeanByRoleName(AppUserRole.MEDECIN.getId());
		userMedec.getRoleBeans().add(roleMedecin.get());
		
		Set<PrivilegeBean> privilegeMedecins=privilegeBeanRepository.findAll().stream().filter(p->p.getName().startsWith("medecin")).collect(Collectors.toSet());
		roleMedecin.get().setPrivilegeBeans(privilegeMedecins);
	}

	private void addRoles() {
		RoleBean rolePatient = RoleBean.builder().createdAt(Instant.now()).updatedAt(null).codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.PATIENT.getId())
				.privilegeBeans(new HashSet<>())
				.build();
		RoleBean roleMedecin = RoleBean.builder().createdAt(Instant.now()).updatedAt(null).codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", "")).roleName(AppUserRole.MEDECIN.getId())
				.build();
		RoleBean roleAdmin = RoleBean.builder().createdAt(Instant.now()).updatedAt(null).codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.privilegeBeans(new HashSet<>())
				.roleName(AppUserRole.ADMINISTRATEUR.getId()).build();

		RoleBean roleSecritaire = RoleBean.builder().createdAt(Instant.now()).updatedAt(null).codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.SECRETAIRE.getId())
				.privilegeBeans(new HashSet<>())
				.build();

		RoleBean roleAMS = RoleBean.builder().createdAt(Instant.now()).updatedAt(null).codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.ASSISTANTE_MEDICO_SOCIALE.getId())
				.privilegeBeans(new HashSet<>())
				.build();

		roleRepository.saveAll(Arrays.asList(roleAdmin, roleAMS, rolePatient, roleSecritaire, roleMedecin));
	}

	private void addPrivilege() {
		for (Permission permission : Permission.values()) {
			PrivilegeBean privilegeBean = PrivilegeBean.builder().name(permission.getPermission()).build();
			privilegeBeanRepository.save(privilegeBean);
		}
	}
}
