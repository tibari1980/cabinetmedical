package com.arcesi.cliniquemedical.security.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.security.entities.TokenBean;


@Repository
public interface TokenBeanRepository extends JpaRepository<TokenBean, Long> {
	
	  @Query(value = """
		      select t from TokenBean t inner join UserBean u\s
		      on t.userBean.id = u.id\s
		      where u.id = :id and (t.expired = false or t.revoked = false)\s
		      """)
		  List<TokenBean> findAllValidTokenByUser(final Long id);

		  Optional<TokenBean> findByToken(final String token);

}
