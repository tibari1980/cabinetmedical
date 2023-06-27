package com.arcesi.cliniquemedical.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.cliniquemedical.security.entities.UserBean;

@Repository
@Transactional(readOnly = true)
public interface UserBeanRepository extends JpaRepository<UserBean, Long> {

	Optional<UserBean> findUserBeanByEmail(String string);

	Optional<UserBean> findUserBeanByUidUser(String uid);
	
	Optional<UserBean> findByTokenActivationEmail(String tokenAction);

	 @Transactional
	    @Modifying
	    @Query("UPDATE UserBean a " +
	            "SET a.enabled = TRUE,a.locked = FALSE WHERE a.email = ?1")
	    int enableAppUser(String email);
	 
//	 @Transactional
//	    @Modifying
//	    @Query("UPDATE ConfirmationToken c " +
//	            "SET c.confirmedAt = ?2 " +
//	            "WHERE c.token = ?1")
//	    int updateConfirmedAt(String token,
//	                          LocalDateTime confirmedAt);

}
