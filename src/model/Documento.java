package model;

import java.util.Date;

public class Documento extends EntidadeDominio {

	private String codigo;
	private Date validade;
	private TipoDocumento tipoDocumento;
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public Date getValidade() {
		return this.validade;
	}
	
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public Documento(long id, Date dataCadastro) {
		super(id, dataCadastro);
	}	
	
	public Documento(long id, Date dataCadastro, String codigo, Date validade, TipoDocumento tipoDocumento) {
		super(id, dataCadastro);
		this.codigo = codigo;
		this.validade = validade;
		this.tipoDocumento = tipoDocumento;
	}
}
