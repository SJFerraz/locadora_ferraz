package br.com.locadora.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.locadora.abstractmodel.EntidadeBase;

@Entity
public class Locacao extends EntidadeBase {
	
	private Date dataLocacao;
	private Date dataVencimento;
	private Date dataDevolucao;
	private Double valor;
	private String observacoes;
	private Byte status;
	private Long idCliente;
	private Long idVeiculo;
	private Long idFuncionario;
	
	
	@ManyToOne
	@JoinColumn(name="idCliente",insertable=false, updatable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idVeiculo", insertable=false, updatable=false)
	private Veiculo veiculo;
	
	
	@ManyToOne
	@JoinColumn(name="idFuncionario",insertable=false, updatable=false)
	private Funcionario funcionario;

	
	
	public Locacao(Date dataLocacao, Date dataVencimento, Date dataDevolucao, Double valor, String observacoes,
			Byte status, Long idCliente, Long idVeiculo) {
		this(dataLocacao,dataVencimento, dataDevolucao, valor, observacoes, status);
		this.idCliente = idCliente;
		this.idVeiculo = idVeiculo;
	}
	
	public Locacao(Date dataLocacao, Date dataVencimento, Date dataDevolucao, Double valor, String observacoes,
			Byte status, Cliente cliente, Veiculo veiculo) {
		this(dataLocacao,dataVencimento, dataDevolucao, valor, observacoes, status);
		this.cliente = cliente;
		this.veiculo = veiculo;
	}
	
	public Locacao(Date dataLocacao, Date dataVencimento, Date dataDevolucao, Double valor, String observacoes,
			Byte status, Long idCliente, Long idVeiculo, Long idFuncionario) {
		this(dataLocacao,dataVencimento, dataDevolucao, valor, observacoes, status, idCliente, idVeiculo);
		this.idFuncionario = idFuncionario;
	}
	
	public Locacao(Date dataLocacao, Date dataVencimento, Date dataDevolucao, Double valor, String observacoes,
			Byte status, Cliente cliente, Veiculo veiculo, Funcionario funcionario) {
		this(dataLocacao,dataVencimento, dataDevolucao, valor, observacoes, status, cliente, veiculo);
		this.funcionario = funcionario;
	}
	
	//Construtor base (privado) para extender as opções por id ou objetos
	private Locacao(Date dataLocacao, Date dataVencimento, Date dataDevolucao, Double valor, String observacoes,
			Byte status) {
		this.dataLocacao = dataLocacao;
		this.dataVencimento = dataVencimento;
		this.dataDevolucao = dataDevolucao;
		this.valor = valor;
		this.observacoes = observacoes;
		this.status = status;
	}
	
	public Locacao() {}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", removed=" + removed + ", dataLocacao=" + dataLocacao + ", dataVencimento="
				+ dataVencimento + ", dataDevolucao=" + dataDevolucao + ", valor=" + valor + ", observacoes="
				+ observacoes + ", status=" + status + ", idCliente=" + idCliente + ", idVeiculo=" + idVeiculo
				+ ", idFuncionario=" + idFuncionario + ", cliente=" + cliente + ", veiculo=" + veiculo
				+ ", funcionario=" + funcionario + "]";
	}


	
}
