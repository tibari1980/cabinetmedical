package com.arcesi.cliniquemedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.entities.PatientBean;

@Repository
public interface PatientRepository extends JpaRepository<PatientBean, Long> {

}
