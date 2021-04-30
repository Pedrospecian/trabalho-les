package model;

import java.util.Date;

public class CartaoCredito extends EntidadeDominio {

	private String nome;
	private String numero;
	private Date dataExpiracao;
	private String cvv;
	private Bandeira bandeira;
	private double valorPago;
	private boolean registrarCartao;
	private boolean jaExistente;
	private boolean preferencial;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return this.numero;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getCvv() {
		return this.cvv;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	
	public double getValorPago() {
		return this.valorPago;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
	public Date getDataExpiracao() {
		return this.dataExpiracao;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	
	public Bandeira getBandeira() {
		return this.bandeira;
	}

	public void setRegistrarCartao(boolean registrarCartao) {
		this.registrarCartao = registrarCartao;
	}
	
	public boolean getRegistrarCartao() {
		return this.registrarCartao;
	}
	
	public void setJaExistente(boolean jaExistente) {
		this.jaExistente = jaExistente;
	}
	
	public boolean getJaExistente() {
		return this.jaExistente;
	}

	public void setPreferencial(boolean preferencial) {
		this.preferencial = preferencial;
	}
	
	public boolean getPreferencial() {
		return this.preferencial;
	}

	public CartaoCredito(long id, Date dataCadastro, String nome, String numero, String cvv, Date dataExpiracao, Bandeira bandeira) {
		super(id, dataCadastro);
		this.nome = nome;
		this.numero = numero;
		this.dataExpiracao = dataExpiracao;
		this.cvv = cvv;
		this.bandeira = bandeira;
	}

}
