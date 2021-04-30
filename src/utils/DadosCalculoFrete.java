package utils;

public class DadosCalculoFrete {
	private double totalAlturas;
	private double totalPesos;	
	private double maiorLargura;
	private double maiorProfundidade;
	private String cepOrigem;
	private String cepDestino;
	private String tipoServico;

	public double getTotalAlturas() {
		return this.totalAlturas;
	}

	public void setTotalAlturas(double totalAlturas) {
		this.totalAlturas = totalAlturas;
	}

	public double getTotalPesos() {
		return this.totalPesos;
	}

	public void setTotalPesos(double totalPesos) {
		this.totalPesos = totalPesos;
	}

	public double getMaiorLargura() {
		return this.maiorLargura;
	}

	public void setMaiorLargura(double maiorLargura) {
		this.maiorLargura = maiorLargura;
	}

	public double getMaiorProfundidade() {
		return this.maiorProfundidade;
	}

	public void setMaiorProfundidade(double maiorProfundidade) {
		this.maiorProfundidade = maiorProfundidade;
	}

	public String getCepOrigem() {
		return this.cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return this.cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public String getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public DadosCalculoFrete(double totalAlturas, double totalPesos, double maiorLargura, double maiorProfundidade, String cepOrigem, String tipoServico) {
		this.totalAlturas = totalAlturas;
		this.totalPesos = totalPesos;
		this.maiorLargura = maiorLargura;
		this.maiorProfundidade = maiorProfundidade;
		this.cepOrigem = cepOrigem;
		this.tipoServico = tipoServico;
	}
}
