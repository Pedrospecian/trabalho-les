package model;

import java.util.Date;
import java.util.ArrayList;

public class Carrinho extends EntidadeDominio {

	private ArrayList<ItemCarrinho> itensCarrinho;
	private int status;
	private Cliente cliente;
	private Date dataAlteracao;

	public void setItensCarrinho(ArrayList<ItemCarrinho> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}
	
	public ArrayList<ItemCarrinho> getItensCarrinho() {
		return this.itensCarrinho;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}
	
	public Carrinho(long id, Date dataCadastro, ArrayList<ItemCarrinho> itensCarrinho, int status, Cliente cliente) {
		super(id, dataCadastro);
		this.itensCarrinho = itensCarrinho;
		this.status = status;
		this.cliente = cliente;
	}

}
