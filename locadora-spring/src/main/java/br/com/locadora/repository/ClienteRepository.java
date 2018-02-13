package br.com.locadora.repository;

import javax.transaction.Transactional;

import br.com.locadora.model.Cliente;

@Transactional
public interface ClienteRepository extends UsuarioRepository<Cliente,Long>{
	
}
