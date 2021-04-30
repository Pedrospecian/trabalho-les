package model;
import java.util.Date;

public class EntidadeDominio {

	private long id;
	private Date dataCadastro;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Date getDataCadastro() {
		return this.dataCadastro;
	}
	
	public EntidadeDominio(long id, Date dataCadastro) {
		this.id = id;
		this.dataCadastro = dataCadastro;
	}

}
