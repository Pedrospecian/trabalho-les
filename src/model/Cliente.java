package model;

import java.util.Date;

public class Cliente extends Pessoa {

	private String nome;
	private Genero genero;
	private Date dataNascimento;
	private int status;
	private TipoCliente tipoCliente;
	private Endereco[] enderecos;
	private CartaoCredito[] cartoesCredito;
	private Telefone[] telefones;
	private String email;
	private String senha;
	private int totalPedidos;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public Genero getGenero() {
		return this.genero;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Date getDataNascimento() {
		return this.dataNascimento;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void setTotalPedidos(int totalPedidos) {
		this.totalPedidos = totalPedidos;
	}
	
	public int getTotalPedidos() {
		return this.totalPedidos;
	}
	
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	public TipoCliente getTipoCliente() {
		return this.tipoCliente;
	}

	public void setEnderecos(Endereco[] enderecos) {
		this.enderecos = enderecos;
	}
	
	public Endereco[] getEnderecos() {
		return this.enderecos;
	}

	public void setCartoesCredito(CartaoCredito[] cartoesCredito) {
		this.cartoesCredito = cartoesCredito;
	}
	
	public CartaoCredito[] getCartoesCredito() {
		return this.cartoesCredito;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return this.senha;
	}

	public void setTelefones(Telefone[] telefones) {
		this.telefones = telefones;
	}
	
	public Telefone[] getTelefones() {
		return this.telefones;
	}

	public Cliente(long id, Date dataCadastro, Documento[] documentos) {
		super(id, dataCadastro, documentos);
	}

	public Cliente(long id, Date dataCadastro, Documento[] documentos, String nome) {
		super(id, dataCadastro, documentos);
		this.nome = nome;
	}

	public Cliente(long id, Date dataCadastro, Documento[] documentos, String nome, String senha) {
		super(id, dataCadastro, documentos);
		this.nome = nome;
		this.senha = senha;
	}
	
	public Cliente(long id, Date dataCadastro, Documento[] documentos, String nome, Genero genero, Date dataNascimento, TipoCliente tipoCliente, Endereco[] enderecos, int status, CartaoCredito[] cartoesCredito, String email, String senha, Telefone[] telefones) {
		super(id, dataCadastro, documentos);
		this.nome = nome;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.tipoCliente = tipoCliente;
		this.enderecos = enderecos;
		this.status = status;
		this.cartoesCredito = cartoesCredito;
		this.email = email;
		this.senha = senha;
		this.telefones = telefones;
	}
}
