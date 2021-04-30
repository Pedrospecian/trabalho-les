package model;

import java.util.Date;

public class Livro extends EntidadeDominio {

	private String titulo;
	private Autor autor;
	private Editora editora;
	private Categoria[] categorias;
	private String ano;
	private String isbn;
	private int numeroPaginas;
	private String sinopse; 
	private double altura;
	private double largura;
	private double peso;
	private double profundidade;
	private double preco;
	private String codigoBarras;
	private int status;
	private String capa;
	private GrupoPrecificacao grupoPrecificacao;
	private String edicao;
	private int estoque;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public Autor getAutor() {
		return this.autor;
	}
	
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public Editora getEditora() {
		return this.editora;
	}
	
	public void setCategorias(Categoria[] categorias) {
		this.categorias = categorias;
	}
	
	public Categoria[] getCategorias() {
		return this.categorias;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public String getAno() {
		return this.ano;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
	public int getNumeroPaginas() {
		return this.numeroPaginas;
	}
	
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	public String getSinopse() {
		return this.sinopse;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	public double getAltura() {
		return this.altura;
	}
	
	public void setLargura(double largura) {
		this.largura = largura;
	}
	
	public double getLargura() {
		return this.largura;
	}
	public void setPeso (double peso) {
		this.peso = peso;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public void setProfundidade (double profundidade) {
		this.profundidade = profundidade;
	}
	
	public double getProfundidade() {
		return this.profundidade;
	}
	
	public void setPreco (double preco) {
		this.preco = preco;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public void setCodigoBarras (String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	public String getCodigoBarras() {
		return this.codigoBarras;
	}
	
	public void setStatus (int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}
	
	public String getCapa() {
		return this.capa;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	
	public String getEdicao() {
		return this.edicao;
	}

	public void setGrupoPrecificacao(GrupoPrecificacao grupoPrecificacao) {
		this.grupoPrecificacao = grupoPrecificacao;
	}

	public GrupoPrecificacao getGrupoPrecificacao() {
		return this.grupoPrecificacao;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	
	public int getEstoque() {
		return this.estoque;
	}

	public Livro(long id, Date dataCadastro) {
		super(id, dataCadastro);
	}

	public Livro(long id, Date dataCadastro, String titulo, String capa, double preco) {
		super(id, dataCadastro);
		this.titulo = titulo;
		this.capa = capa;
		this.preco = preco;
	}
	
	public Livro(long id, Date dataCadastro, String titulo, Autor autor, Editora editora, Categoria[] categorias, String ano, String isbn, int numeroPaginas, String sinopse, double altura, double peso, double profundidade, double preco, String codigoBarras, int status, String capa, GrupoPrecificacao grupoPrecificacao, String edicao) {
		super(id, dataCadastro);
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.categorias = categorias;
		this.isbn = isbn;
		this.numeroPaginas = numeroPaginas;
		this.sinopse = sinopse;
		this.ano = ano;
		this.altura = altura;
		this.peso = peso;
		this.profundidade = profundidade;
		this.preco = preco;
		this.codigoBarras = codigoBarras;
		this.status = status;
		this.capa = capa;
		this.grupoPrecificacao = grupoPrecificacao;
		this.edicao = edicao;
	}

}
