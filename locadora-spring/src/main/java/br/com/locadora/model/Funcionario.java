package br.com.locadora.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.locadora.abstractmodel.Usuario;
import br.com.locadora.enumer.RoleStatus;

@Entity
public class Funcionario extends Usuario{
	
	private String cargo;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="veiculo")
	private List<Locacao> locacoes;
	
	{this.permissao = RoleStatus.FUNCIONARIO.getValue();}

	
	public Funcionario(String nome, String cpf, String login, String senha, String cargo, String email) {
		super(nome, cpf, login, senha, email);
		this.cargo = cargo;
	}
	
	public Funcionario(String nome, String cpf, String login, String senha, String cargo, String email, Byte permissao) {
		this(nome, cpf, login, senha, cargo, email);
		this.permissao = permissao;
	}
	
	public Funcionario() {}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", login=" + login + ", senha=" + senha
				+ ", email=" + email + ", permissao=" + permissao + ", removed=" + removed + ", cargo=" + cargo
				+ ", locacoes=" + locacoes + "]";
	}

	
}
