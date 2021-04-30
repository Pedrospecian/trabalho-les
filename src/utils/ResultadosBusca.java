package utils;

import java.util.ArrayList;

public class ResultadosBusca {
	private int contagemTotal;
	private ArrayList resultados;

	public int getContagemTotal() {
		return this.contagemTotal;
	}

	public void setContagemTotal(int contagemTotal) {
		this.contagemTotal = contagemTotal;
	}

	public ArrayList getResultados() {
		return this.resultados;
	}

	public void setResultados(ArrayList resultados) {
		this.resultados = resultados;
	}

	public ResultadosBusca(ArrayList resultados) {
		this.resultados = resultados;
	}
}
