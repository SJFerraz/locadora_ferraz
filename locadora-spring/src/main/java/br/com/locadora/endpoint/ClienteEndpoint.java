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
import br.com.locadora.model.Cliente;
import br.com.locadora.repository.ClienteRepository;

@RestController
@RequestMapping("v1")
public class ClienteEndpoint {
	private final ClienteRepository clienteDAO;
	
	@Autowired
	public ClienteEndpoint(ClienteRepository clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	

	@GetMapping(path="protected-shared/clientes/paging")
	public ResponseEntity<?> listAll(Pageable pageable){
		return new ResponseEntity<>(clienteDAO.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="protected-shared/clientes")
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(clienteDAO.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path="shared/clientes/{id}") 	
	public ResponseEntity<?> buscarClientePorId(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails){
		System.out.println(userDetails);
		verifyIfClienteExists(id);
		return new ResponseEntity<>(clienteDAO.findOne(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "protected-shared/clientes/findByNome/{nome}")
	public ResponseEntity<?> buscarClientesPorNome(@PathVariable("nome") String name){
		return new ResponseEntity<>(clienteDAO.findByNomeIgnoreCaseContaining(name), HttpStatus.OK);
	}
	
	@PostMapping(path="protected-share/clientes")
	@Transactional(rollbackFor = Exception.class) //<- serve para definir uma transacão 
	public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente){
		return new ResponseEntity<>(clienteDAO.save(cliente),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="private/clientes/remove/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id){
		verifyIfClienteExists(id);
		clienteDAO.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="private/clientes/restore/{id}")
	public ResponseEntity<?> restaurar(@PathVariable("id") Long id){
		verifyIfClienteExists(id);
		clienteDAO.restore(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path="admin/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		verifyIfClienteExists(id);
		clienteDAO.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="protected-share/clientes")
	public ResponseEntity<?> update(@RequestBody Cliente cliente){
		verifyIfClienteExists(cliente.getId());
		clienteDAO.save(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//método verifica se ha registro no banco por id
	private void verifyIfClienteExists(Long id) {		
		if(clienteDAO.findOne(id) == null)  
			throw new ResourceNotFoundException("Cliente não encontrado ID: "+id);
	}
}
