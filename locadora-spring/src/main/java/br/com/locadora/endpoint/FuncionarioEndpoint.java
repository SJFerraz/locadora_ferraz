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
import br.com.locadora.model.Funcionario;
import br.com.locadora.repository.FuncionarioRepository;

@RestController
@RequestMapping("v1")
public class FuncionarioEndpoint {
	private final FuncionarioRepository funcionarioDAO;
	
	@Autowired
	public FuncionarioEndpoint(FuncionarioRepository funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	@GetMapping(path="private/funcionarios/paging")
	public ResponseEntity<?> listAll(Pageable pageable){
		return new ResponseEntity<>(funcionarioDAO.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="private/funcionarios")
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(funcionarioDAO.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path="shared/funcionarios/{id}") 	
	public ResponseEntity<?> buscarFuncionarioPorId(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails){
		System.out.println(userDetails);
		verifyIfFuncionarioExists(id);
		return new ResponseEntity<>(funcionarioDAO.findOne(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "private/funcionarios/findByNome/{nome}")
	public ResponseEntity<?> buscarFuncionariosPorNome(@PathVariable("nome") String name){
		return new ResponseEntity<>(funcionarioDAO.findByNomeIgnoreCaseContaining(name), HttpStatus.OK);
	}
	
	@PostMapping(path="private/funcionarios")
	@Transactional(rollbackFor = Exception.class) //<- serve para definir uma transacão 
	public ResponseEntity<?> save(@Valid @RequestBody Funcionario funcionario){
		return new ResponseEntity<>(funcionarioDAO.save(funcionario),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="private/funcionarios/remove/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id){
		verifyIfFuncionarioExists(id);
		funcionarioDAO.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="private/funcionarios/restore/{id}")
	public ResponseEntity<?> restaurar(@PathVariable("id") Long id){
		verifyIfFuncionarioExists(id);
		funcionarioDAO.restore(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path="admin/funcionarios/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		verifyIfFuncionarioExists(id);
		funcionarioDAO.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="private/funcionarios")
	public ResponseEntity<?> update(@RequestBody Funcionario funcionario){
		verifyIfFuncionarioExists(funcionario.getId());
		funcionarioDAO.save(funcionario);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//método verifica se ha registro no banco por id
	private void verifyIfFuncionarioExists(Long id) {		
		if(funcionarioDAO.findOne(id) == null)  
			throw new ResourceNotFoundException("Funcionario não encontrado ID: "+id);
	}
}
