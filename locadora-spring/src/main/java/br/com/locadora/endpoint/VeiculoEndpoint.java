package br.com.locadora.endpoint;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.error.ResourceNotFoundException;
import br.com.locadora.model.Veiculo;
import br.com.locadora.repository.VeiculoRepository;

@RestController
@RequestMapping("v1")
public class VeiculoEndpoint {
	private final VeiculoRepository veiculoDAO;
	
	@Autowired
	public VeiculoEndpoint(VeiculoRepository veiculoDAO) {
		this.veiculoDAO = veiculoDAO;
	}
	

	@GetMapping(path="public/veiculos/paging")
	public ResponseEntity<?> listAll(Pageable pageable){
		return new ResponseEntity<>(veiculoDAO.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="public/veiculos")
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(veiculoDAO.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path="public/veiculos/{id}") 	
	public ResponseEntity<?> buscarVeiculoPorId(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails){
		System.out.println(userDetails);
		verifyIfVeiculoExists(id);
		return new ResponseEntity<>(veiculoDAO.findOne(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "public/veiculos/findByPlaca/{placa}")
	public ResponseEntity<?> buscarVeiculosPorPlaca(@PathVariable("placa") String placa){
		return new ResponseEntity<>(veiculoDAO.findByPlaca(placa), HttpStatus.OK);
	}
	
	@PostMapping(path="private/veiculos")
	@Transactional(rollbackFor = Exception.class) //<- serve para definir uma transacão 
	public ResponseEntity<?> save(@Valid @RequestBody Veiculo veiculo){
		return new ResponseEntity<>(veiculoDAO.save(veiculo),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="private/veiculos/remove/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id){
		verifyIfVeiculoExists(id);
		veiculoDAO.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="private/veiculos/restore/{id}")
	public ResponseEntity<?> restaurar(@PathVariable("id") Long id){
		verifyIfVeiculoExists(id);
		veiculoDAO.restore(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path="admin/veiculos/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		verifyIfVeiculoExists(id);
		veiculoDAO.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="private/veiculos")
	public ResponseEntity<?> update(@RequestBody Veiculo veiculo){
		verifyIfVeiculoExists(veiculo.getId());
		veiculoDAO.save(veiculo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//método verifica se ha registro no banco por id
	private void verifyIfVeiculoExists(Long id) {		
		if(veiculoDAO.findOne(id) == null)  
			throw new ResourceNotFoundException("Veiculo não encontrado ID: "+id);
	}
}
