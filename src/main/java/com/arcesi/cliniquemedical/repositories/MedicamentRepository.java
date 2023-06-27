package com.arcesi.cliniquemedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.entities.MedicamentBean;

@Repository
public interface MedicamentRepository extends JpaRepository<MedicamentBean, Long> {

}
