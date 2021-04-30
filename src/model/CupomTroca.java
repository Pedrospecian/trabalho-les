package model;

import java.util.Date;

public class CupomTroca extends EntidadeDominio {

	private String nome;
	private double valor;
	private Pedido pedido;
	
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

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Pedido getPedido() {
		return this.pedido;
	}
	
	public CupomTroca(long id, Date dataCadastro, String nome, double valor, Pedido pedido) {
		super(id, dataCadastro);
		this.nome = nome;
		this.valor = valor;
		this.pedido = pedido;
	}

}
