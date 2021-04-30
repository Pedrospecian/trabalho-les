package utils;

public class Campo {
	private int tipo;
	private String valor;
	private boolean valido;
	private String mensagemErro;
	private boolean obrigatorio;
	private String nome;

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getMensagemErro() {
		return this.mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public boolean getValido() {
		return this.valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public boolean getObrigatorio() {
		return this.obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Campo(int tipo, String valor, boolean valido, String mensagemErro, boolean obrigatorio, String nome) {
		this.tipo = tipo;
		this.valor = valor;
		this.valido = valido;
		this.mensagemErro = mensagemErro;
		this.obrigatorio = obrigatorio;
		this.nome = nome;
	}
}
