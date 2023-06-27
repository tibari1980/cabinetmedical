package com.arcesi.cliniquemedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.entities.PathologieBean;

@Repository
public interface PathologieRepository extends JpaRepository<PathologieBean, Long> {

}
