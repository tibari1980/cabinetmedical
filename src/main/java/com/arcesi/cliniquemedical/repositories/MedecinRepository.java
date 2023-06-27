package com.arcesi.cliniquemedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.entities.MedecinBean;

@Repository
public interface MedecinRepository extends JpaRepository<MedecinBean, Long> {

}
