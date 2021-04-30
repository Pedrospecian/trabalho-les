package model;

import java.util.Date;

public class ItemCarrinho extends EntidadeDominio {

	private Livro livro;
	private int quantidade;
	private Cliente cliente;
	private int quantidadeItensTrocados;

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Livro getLivro() {
		return this.livro;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getQuantidadeItensTrocados() {
		return this.quantidadeItensTrocados;
	}

	public void setQuantidadeItensTrocados(int quantidadeItensTrocados) {
		this.quantidadeItensTrocados = quantidadeItensTrocados;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public ItemCarrinho(long id, Date dataCadastro, Livro livro, int quantidade, Cliente cliente) {
		super(id, dataCadastro);
		this.livro = livro;
		this.quantidade = quantidade;
		this.cliente = cliente;
	}

}
