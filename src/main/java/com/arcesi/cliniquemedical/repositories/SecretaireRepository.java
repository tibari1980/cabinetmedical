package com.arcesi.cliniquemedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.entities.SecretaireBean;

@Repository
public interface SecretaireRepository extends JpaRepository<SecretaireBean, Long>{

}
