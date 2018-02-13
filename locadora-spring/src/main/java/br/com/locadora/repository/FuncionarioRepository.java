package br.com.locadora.repository;

import javax.transaction.Transactional;

import br.com.locadora.model.Funcionario;

@Transactional
public interface FuncionarioRepository extends UsuarioRepository<Funcionario, Long>{

}
