package br.com.locadora.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.locadora.abstractmodel.Usuario;
import br.com.locadora.enumer.RoleStatus;

@Entity
public class Cliente extends Usuario{
	
	private String rg;
	private String cnh;
	private String endereco;
	private Long numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private Date dataNascimento;
	
	{this.permissao = RoleStatus.CLIENTE.getValue();}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="veiculo")
	private List<Locacao> locacoes;
	
	public Cliente(String nome, String cpf, String login, String senha, String rg, String cnh,
			String endereco, Long numero, String bairro, String cidade, String estado, String cep,
			Date dataNascimento, String email) {
		super(nome, cpf, login, senha, email);
		this.rg = rg;
		this.cnh = cnh;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.dataNascimento = dataNascimento;
	}
	
	public Cliente(String nome, String cpf, String login, String senha, String rg, String cnh,
			String endereco, Long numero, String bairro, String cidade, String estado, String cep,
			Date dataNascimento, String email, Byte permissao) {
		this(nome, cpf, login, senha, rg, cnh, endereco, numero, bairro,  
				cidade, estado, cep, dataNascimento, email);
		this.permissao = permissao;
	}
	
	public Cliente(String nome, String cpf, String login, String senha, String endereco, String email) {
		super(nome, cpf, login, senha, email);
		this.endereco = endereco;
	}
	
	public Cliente() {}
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", login=" + login + ", senha=" + senha
				+ ", email=" + email + ", permissao=" + permissao + ", removed=" + removed + ", rg=" + rg + ", cnh="
				+ cnh + ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + ", cep=" + cep + ", dataNascimento=" + dataNascimento + "]";
	}
	
	
}
