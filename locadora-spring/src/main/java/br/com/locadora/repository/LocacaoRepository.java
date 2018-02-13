package br.com.locadora.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.locadora.model.Locacao;

public interface LocacaoRepository extends EntidadeBaseRepository<Locacao, Long>{
	
	List<Locacao> findByClienteCpf(String cpf);
	
	List<Locacao> findByClienteNomeIgnoreCaseContaining(String nome);
	
	List<Locacao> findByClienteId(Long id);
	
	List<Locacao> findByVeiculoPlacaIgnoreCase(String placa);
			
	List<Locacao> findByVeiculoId(Long id);
	
}