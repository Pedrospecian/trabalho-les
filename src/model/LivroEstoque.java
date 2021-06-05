package model;

import java.util.Date;

public class LivroEstoque extends EntidadeDominio {

	private int quantidade;
	private double custo;
	private Date dataEntrada;
	private Fornecedor fornecedor;
	private Livro livro;
	private int tipoMovimentacao;
	private Usuario usuarioResponsavel;
	private Cliente cliente;

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
	
	public Usuario getUsuarioResponsavel() {
		return this.usuarioResponsavel;
	}

	public void setTipoMovimentacao(int tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
	public int getTipoMovimentacao() {
		return this.tipoMovimentacao;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	public double getCusto() {
		return this.custo;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	public Date getDataEntrada() {
		return this.dataEntrada;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Livro getLivro() {
		return this.livro;
	}

	public LivroEstoque(long id, Date dataCadastro, int quantidade, double custo, Date dataEntrada, Fornecedor fornecedor, Livro livro, int tipoMovimentacao) {
		super(id, dataCadastro);
		this.quantidade = quantidade;
		this.custo = custo;
		this.dataEntrada = dataEntrada;
		this.fornecedor = fornecedor;
		this.livro = livro;
		this.tipoMovimentacao = tipoMovimentacao;
	}

}
