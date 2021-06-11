package model;

import java.util.Date;

public class Pedido extends EntidadeDominio {

	private Cliente cliente;
	private Endereco endereco;
	private double valorFrete;
	private CupomDesconto cupomDesconto;
	private CartaoCredito[] cartoesCredito;
	private CupomTroca[] cuponsTroca;	
	private int status;
	private Carrinho carrinho;
	private double valorTotal;
	private int prazo;
	private String tipoServico;
	private Date dataAlteracao;
	
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
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	public double getValorFrete() {
		return this.valorFrete;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public double getValorTotal() {
		return this.valorTotal;
	}

	public void setCupomDesconto(CupomDesconto cupomDesconto) {
		this.cupomDesconto = cupomDesconto;
	}
	
	public CupomDesconto getCupomDesconto() {
		return this.cupomDesconto;
	}

	public void setCartoesCredito(CartaoCredito[] cartoesCredito) {
		this.cartoesCredito = cartoesCredito;
	}
	
	public CartaoCredito[] getCartoesCredito() {
		return this.cartoesCredito;
	}

	public void setCuponsTroca(CupomTroca[] cuponsTroca) {
		this.cuponsTroca = cuponsTroca;
	}
	
	public CupomTroca[] getCuponsTroca() {
		return this.cuponsTroca;
	}
	
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	public Carrinho getCarrinho() {
		return this.carrinho;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}
	
	public int getPrazo() {
		return this.prazo;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	public String getTipoServico() {
		return this.tipoServico;
	}


	public Pedido(long id, Date dataCadastro) {
		super(id, dataCadastro);
	}
	
	public Pedido(long id, Date dataCadastro, Cliente cliente, int status, Endereco endereco, double valorFrete, CupomDesconto cupomDesconto, CartaoCredito[] cartoesCredito, CupomTroca[] cuponsTroca, Carrinho carrinho, double valorTotal, int prazo, String tipoServico) {
		super(id, dataCadastro);
		this.cliente = cliente;
		this.status = status;
		this.endereco = endereco;
		this.valorFrete = valorFrete;
		this.cupomDesconto = cupomDesconto;
		this.cartoesCredito = cartoesCredito;
		this.cuponsTroca = cuponsTroca;
		this.carrinho = carrinho;
		this.valorTotal = valorTotal;
		this.prazo = prazo;
		this.tipoServico = tipoServico;
	}
}
