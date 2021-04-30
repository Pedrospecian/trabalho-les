package model;

import java.util.Date;

public class Pessoa extends EntidadeDominio {
	
	private Documento[] documentos;
	
	public void setDocumentos(Documento[] documentos) {
		this.documentos = documentos;
	}
	
	public Documento[] getDocumentos() {
		return this.documentos;
	}
	
	public Pessoa(long id, Date dataCadastro, Documento[] documentos) {
		super(id, dataCadastro);
		this.documentos = documentos;
	}
}
