package com.arcesi.cliniquemedical.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.security.entities.PrivilegeBean;


@Repository
public interface PrivilegeBeanRepository  extends JpaRepository<PrivilegeBean, Long>{

	PrivilegeBean findByName(String permission);

}
