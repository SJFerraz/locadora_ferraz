package br.com.locadora.abstractmodel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.locadora.enumer.RoleStatus;

@MappedSuperclass
public class Usuario extends EntidadeBase{
	
	@NotEmpty(message= "O campo nome é obrigatorio")
	protected String nome;
	
	@NotEmpty(message= "O campo cpf é obrigatorio")
	@NaturalId
	@CPF
	protected String cpf;
	
	
	@NaturalId
	protected String login;
	
	//@JsonIgnore
	protected String senha;
	
	@NotEmpty(message= "O campo email é obrigatório")
	@Email(message="O campo email não é valido")
	protected String email;
	
	protected Byte permissao;
	
	{this.permissao = RoleStatus.NENHUMA.getValue();}
	
	public Usuario(String nome, String cpf, String login, String senha, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
		this.email = email;
		
	}
	
	public Usuario(String nome, String cpf, String login, String senha, String email, Byte permissao) {
		this(nome,cpf,login,senha,email);
		this.permissao = permissao;
	}

	public Usuario() {
	};
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Byte getPermissao() {
		return permissao;
	}

	public void setPermissao(Byte permission) {
		this.permissao = permission;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
