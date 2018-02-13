package br.com.locadora.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.locadora.abstractmodel.Usuario;
import br.com.locadora.enumer.RoleStatus;
import br.com.locadora.model.Cliente;
import br.com.locadora.model.Funcionario;
import br.com.locadora.repository.ClienteRepository;
import br.com.locadora.repository.FuncionarioRepository;
import br.com.locadora.repository.UsuarioRepository;
import javassist.bytecode.DuplicateMemberException;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	
	//private UserRepository<T,ID> userRepository;
	
	private ClienteRepository clienteRepository;
	
	private FuncionarioRepository funcionarioRepository;
		
	@Autowired
	public CustomUserDetailService(ClienteRepository clienteRepository, FuncionarioRepository funcionarioRepository) {
		super();
		this.clienteRepository = clienteRepository;
		this.funcionarioRepository = funcionarioRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = null;		
		Cliente clienteLogin = clienteRepository.findByLogin(username);
		Funcionario funcionarioLogin = funcionarioRepository.findByLogin(username);
		
		if(!isDuplicateLogin(funcionarioLogin, clienteLogin)) {
			usuario = setUserPriorityToLogin(funcionarioLogin, clienteLogin);
		}else {
			try {
				throw new DuplicateMemberException("Identificados usuarios duplicados");
			}catch(DuplicateMemberException e) {
				e.printStackTrace();
			}
		}
		
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		
		
		List<GrantedAuthority> userAuthority = new ArrayList<>();
		
		if(RoleStatus.ADMIN.getValue().equals(usuario.getPermissao())) {
			userAuthority = AuthorityUtils.createAuthorityList("ROLE_FUNCIONARIO","ROLE_ADMIN");
		}else if(RoleStatus.FUNCIONARIO.getValue().equals(usuario.getPermissao())) {
			userAuthority = AuthorityUtils.createAuthorityList("ROLE_FUNCIONARIO");
		}else if(RoleStatus.CLIENTE.getValue().equals(usuario.getPermissao())) {
			userAuthority = AuthorityUtils.createAuthorityList("ROLE_CLIENTE");
		}else if(RoleStatus.NENHUMA.getValue().equals(usuario.getPermissao())) {
			userAuthority = AuthorityUtils.createAuthorityList("ROLE_NENHUMA");
		}
		
		return new org.springframework.security.core.userdetails.User(
				usuario.getLogin(),usuario.getSenha(),userAuthority
		);
	}
	
	private static Usuario setUserPriorityToLogin(Usuario user1, Usuario user2) {
		if(user1 != null) {
			return user1;
		}else{
			if(user2 != null) {
				return user2;
			}
		}
		
		return null;
	}
	
	private static boolean isDuplicateLogin(Usuario user1, Usuario user2) {
		if(user1 != null && user2 != null) {
			if(user1.getLogin().equals(user2.getLogin())) {
				return true;
			}
		}
		return false;
	}

	

}
