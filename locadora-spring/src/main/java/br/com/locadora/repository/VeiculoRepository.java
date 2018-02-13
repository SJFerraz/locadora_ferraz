package br.com.locadora.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.locadora.model.Veiculo;

public interface VeiculoRepository extends EntidadeBaseRepository<Veiculo, Long>{
	List<Veiculo> findByPlaca(String placa);
}
