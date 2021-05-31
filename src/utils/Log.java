package utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	private Date dataHora;
	private String usuarioResponsavel;
	private String dados;
	private String tipo;

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getUsuarioResponsavel() {
		return this.usuarioResponsavel;
	}

	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public String getDados() {
		return this.dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Log(String usuarioResponsavel, String dados, String tipo) {
		this.usuarioResponsavel = usuarioResponsavel;
		this.dados = dados;
		this.tipo = tipo;
		this.dataHora = new Date();
	}

	public void registrar() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dataFormatada = dateFormat.format(this.dataHora);

		    FileWriter writer = new FileWriter("../../logs/logAtividades.txt");
			writer.write(dataFormatada + 
						  " - Usuário responsável: " + this.usuarioResponsavel +
						  " Operação: " + this.tipo +
						  " Dados: " + this.dados +
						  System.getProperty( "line.separator" ));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
