package model;

import java.util.Date;

public class Fornecedor extends EntidadeDominio {

	private String nome;
	private String email;
	private int status;
	private Documento documento;
	private Endereco endereco;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public Documento getDocumento() {
		return this.documento;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public Fornecedor(long id, Date dataCadastro, String nome, String email, int status, Documento documento, Endereco endereco) {
		super(id, dataCadastro);
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.status = status;
	}

}
