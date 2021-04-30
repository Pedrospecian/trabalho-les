package model;

import java.util.Date;

public class Endereco extends EntidadeDominio {

	private String nome;
	private String logradouro;
	private String numero;
	private String cep;
	private String complemento;	
	private Bairro bairro;
	private TipoEndereco tipoEndereco;

	private TipoResidencia tipoResidencia;
	private FuncaoEndereco funcaoEndereco;
	private TipoLogradouro tipoLogradouro;
	private String observacoes;

	private boolean novo;

	public void setNovo(boolean novo) {
		this.novo = novo;
	}
	
	public boolean getNovo() {
		return this.novo;
	}

	public void setTipoResidencia(TipoResidencia tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}
	
	public TipoResidencia getTipoResidencia() {
		return this.tipoResidencia;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	
	public TipoLogradouro getTipoLogradouro() {
		return this.tipoLogradouro;
	}

	public void setFuncaoEndereco(FuncaoEndereco funcaoEndereco) {
		this.funcaoEndereco = funcaoEndereco;
	}
	
	public FuncaoEndereco getFuncaoEndereco() {
		return this.funcaoEndereco;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getLogradouro() {
		return this.logradouro;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getObservacoes() {
		return this.observacoes;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return this.numero;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCep() {
		return this.cep;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getComplemento() {
		return this.complemento;
	}
	
	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	
	public TipoEndereco getTipoEndereco() {
		return this.tipoEndereco;
	}
	
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	public Bairro getBairro() {
		return this.bairro;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public Endereco(long id, Date dataCadastro) {
		super(id, dataCadastro);
	}
	
	public Endereco(long id, Date dataCadastro, String logradouro, String numero, String cep, String complemento, Bairro bairro, TipoEndereco tipoEndereco, String nome, TipoResidencia tipoResidencia, FuncaoEndereco funcaoEndereco, TipoLogradouro tipoLogradouro, String observacoes) {
		super(id, dataCadastro);
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.bairro = bairro;
		this.tipoEndereco = tipoEndereco;
		this.nome = nome;
		this.tipoResidencia = tipoResidencia;
		this.funcaoEndereco = funcaoEndereco;
		this.tipoLogradouro = tipoLogradouro;
		this.observacoes = observacoes;
		this.novo = false;
	}
}
