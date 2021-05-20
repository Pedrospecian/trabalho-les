package utils;
import java.util.Date;

public class ItemGrafico {
	private int valor;
	private Date data;
	private int tipo; //1 para livro individual, 2 para categoria
	private String label;


	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}	

	public ItemGrafico(int valor, Date data, int tipo, String label) {
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
		this.label = label;
	}
}