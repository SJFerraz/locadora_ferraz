package br.com.locadora.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.validator.ValidateWith;
import org.springframework.format.annotation.NumberFormat;

import br.com.locadora.abstractmodel.EntidadeBase;

@Entity
public class Veiculo extends EntidadeBase {
	@NotEmpty
	private String marca;
	
	@NotEmpty
	private String modelo;
	
	@NotEmpty
	@NaturalId
	@Pattern(regexp= "[A-Z]{3}\\d{4}", message = "Formato de placa errado.")
	private String placa;
	
	private String chassi;
	
	@Pattern(regexp= "\\d{4}", message = "Campo ano inválido")
	private String anoFabricacao;
	
	private String cor;
	
	private String observacoes;
	
	private Boolean disponibilidade;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="veiculo")
	private List<Locacao> locacoes;
	
	public Veiculo(String marca, String modelo, String placa, String chassi, String anoFabricacao,
			String cor, String observacoes, Boolean disponibilidade) {
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.chassi = chassi;
		this.anoFabricacao = anoFabricacao;
		this.cor = cor;
		this.observacoes = observacoes;
		this.disponibilidade = disponibilidade;
	}
	
	public Veiculo(String marca, String modelo, String placa, String anoFabricacao,
			String cor) {
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.anoFabricacao = anoFabricacao;
		this.cor = cor;
	}
	
	public Veiculo() {}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Boolean getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", placa=" + placa + ", chassi="
				+ chassi + ", anoFabricacao=" + anoFabricacao + ", cor=" + cor + ", observacoes=" + observacoes
				+ ", disponibilidade=" + disponibilidade + "]";
	}

	
	
}
