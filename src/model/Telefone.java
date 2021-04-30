package model;

import java.util.Date;

public class Telefone extends EntidadeDominio {

	private TipoTelefone tipoTelefone;
	private String ddd;
	private String numero;

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	
	public TipoTelefone getTipoTelefone() {
		return this.tipoTelefone;
	}
	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public String getDdd() {
		return this.ddd;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return this.numero;
	}
	
	public Telefone(long id, Date dataCadastro, TipoTelefone tipoTelefone, String ddd, String numero) {
		super(id, dataCadastro);
		this.tipoTelefone = tipoTelefone;
		this.ddd = ddd;
		this.numero = numero;
	}

}
