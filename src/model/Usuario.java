package model;

import java.util.Date;

public class Usuario extends EntidadeDominio {

	private String nome;
	private String email;
	private int status;
	private String senha;
	private TipoUsuario tipoUsuario;
	
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
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return this.senha;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public Usuario(long id, Date dataCadastro) {
		super(id, dataCadastro);
	}

	public Usuario(long id, Date dataCadastro, String nome) {
		super(id, dataCadastro);
		this.nome = nome;
	}
	
	public Usuario(long id, Date dataCadastro, String nome, String email, int status, String senha, TipoUsuario tipoUsuario) {
		super(id, dataCadastro);
		this.nome = nome;
		this.email = email;
		this.status = status;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}

}
