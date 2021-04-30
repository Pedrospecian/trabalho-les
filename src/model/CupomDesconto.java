package model;

import java.util.Date;

public class CupomDesconto extends EntidadeDominio {

	private String nome;
	private double valor;
	private int status;
	private Date dataInicio;
	private Date dataFim;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public double getValor() {
		return this.valor;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public Date getDataFim() {
		return this.dataFim;
	}
	
	public CupomDesconto(long id, Date dataCadastro, String nome, double valor, Date dataInicio, Date dataFim, int status) {
		super(id, dataCadastro);
		this.nome = nome;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.status = status;
	}

}
