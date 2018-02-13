package br.com.locadora.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.locadora.abstractmodel.EntidadeBase;

@NoRepositoryBean
public interface EntidadeBaseRepository<T extends EntidadeBase, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	@Transactional
	@Modifying
	@Query("UPDATE #{#entityName} e SET e.removed = true WHERE e.id = :id ")
	int remove(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE #{#entityName} e SET e.removed = false WHERE e.id = :id ")
	int restore(@Param("id") Long id);

	
}
