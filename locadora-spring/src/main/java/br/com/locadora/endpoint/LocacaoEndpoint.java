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
import br.com.locadora.model.Locacao;
import br.com.locadora.repository.LocacaoRepository;

@RestController
@RequestMapping("v1")
public class LocacaoEndpoint {
	private final LocacaoRepository locacaoDAO;
	
	@Autowired
	public LocacaoEndpoint(LocacaoRepository locacaoDAO) {
		this.locacaoDAO = locacaoDAO;
	}

	@GetMapping(path="private/locacoes/paging")
	public ResponseEntity<?> listAll(Pageable pageable){
		return new ResponseEntity<>(locacaoDAO.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(path="private/locacoes")
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(locacaoDAO.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path="shared/locacoes/{id}") 	
	public ResponseEntity<?> buscarLocacaoPorId(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails){
		System.out.println(userDetails);
		verifyIfLocacaoExists(id);
		return new ResponseEntity<>(locacaoDAO.findOne(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "shared/locacoes/findByClienteCpf/{cpf}")
	public ResponseEntity<?> buscarLocacaosPorClienteCpf(@PathVariable("cpf") String cpf){
		return new ResponseEntity<>(locacaoDAO.findByClienteCpf(cpf), HttpStatus.OK);
	}
	
	@GetMapping(path = "protected-share/locacoes/findByClienteNome/{nome}")
	public ResponseEntity<?> buscarLocacaosPorClienteNome(@PathVariable("nome") String nome){
		return new ResponseEntity<>(locacaoDAO.findByClienteNomeIgnoreCaseContaining(nome), HttpStatus.OK);
	}
	
	@GetMapping(path = "shared/locacoes/findByClienteId/{id}")
	public ResponseEntity<?> buscarLocacaosPorClienteId(@PathVariable("id") Long id){
		return new ResponseEntity<>(locacaoDAO.findByClienteId(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "shared/locacoes/findByVeiculoPlaca/{placa}")
	public ResponseEntity<?> buscarLocacaosPorVeiculoPlaca(@PathVariable("placa") String placa){
		return new ResponseEntity<>(locacaoDAO.findByVeiculoPlacaIgnoreCase(placa), HttpStatus.OK);
	}
	
	@GetMapping(path = "shared/locacoes/findByVeiculoId/{id}")
	public ResponseEntity<?> buscarLocacaosPorVeiculoId(@PathVariable("id") Long id){
		return new ResponseEntity<>(locacaoDAO.findByVeiculoId(id), HttpStatus.OK);
	}
	
	@PostMapping(path="protected-share/locacoes")
	@Transactional(rollbackFor = Exception.class) //<- serve para definir uma transacão 
	public ResponseEntity<?> save(@Valid @RequestBody Locacao locacao){
		return new ResponseEntity<>(locacaoDAO.save(locacao),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="protected-share/locacoes/remove/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id){
		verifyIfLocacaoExists(id);
		locacaoDAO.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="protected-share/locacoes/restore/{id}")
	public ResponseEntity<?> restaurar(@PathVariable("id") Long id){
		verifyIfLocacaoExists(id);
		locacaoDAO.restore(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path="admin/locacoes/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		verifyIfLocacaoExists(id);
		locacaoDAO.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="protected-share/locacoes")
	public ResponseEntity<?> update(@RequestBody Locacao locacao){
		verifyIfLocacaoExists(locacao.getId());
		locacaoDAO.save(locacao);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//método verifica se ha registro no banco por id
	private void verifyIfLocacaoExists(Long id) {		
		if(locacaoDAO.findOne(id) == null)  
			throw new ResourceNotFoundException("Locacao não encontrado ID: "+id);
	}
}
