package br.com.locadora.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.locadora.abstractmodel.Usuario;

@NoRepositoryBean
public interface UsuarioRepository<T extends Usuario, ID extends Serializable> extends EntidadeBaseRepository<T, ID>{
	
	List<T> findByNomeIgnoreCaseContaining(String nome);
	
	T findByLogin(String login);
	
	List<T> findByCpf(String cpf);
}
