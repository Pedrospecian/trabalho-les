package model;

import java.util.Date;

public class SolicitacaoTroca extends EntidadeDominio {

	private ItemCarrinho itemCarrinho;
	private int quantidade;
	private int status;

	public void setItemCarrinho(ItemCarrinho itemCarrinho) {
		this.itemCarrinho = itemCarrinho;
	}
	
	public ItemCarrinho getItemCarrinho() {
		return this.itemCarrinho;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public SolicitacaoTroca(long id, Date dataCadastro, ItemCarrinho itemCarrinho, int quantidade, int status) {
		super(id, dataCadastro);
		this.itemCarrinho = itemCarrinho;
		this.quantidade = quantidade;
		this.status = status;
	}

}
