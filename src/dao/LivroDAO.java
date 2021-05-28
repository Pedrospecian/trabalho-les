package dao;

import utils.Conexao;
import strategies.CriaFiltragem;
import strategies.CriaPaginacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import dao.IDAO;


import model.Livro;
import model.LivroEstoque;
import model.SolicitacaoAtivacaoLivro;
import model.SolicitacaoInativacaoLivro;
import model.GrupoPrecificacao;
import model.ItemCarrinho;
import model.Autor;
import model.Editora;
import model.EntidadeDominio;
import model.Fornecedor;
import model.Usuario;
import model.Categoria;
import utils.Campo;

import model.CategoriaInativacao;
import model.CategoriaAtivacao;

public class LivroDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public Livro selectSingleVal;	
	public Categoria[] selectCategoriasVal;

	public ArrayList selectHome () {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from livros where status = 1 OR status = 2");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<Livro> list = new ArrayList();
			
			while (rs.next()) {
				Livro livro = new Livro(
					rs.getLong("livros.id"), 
					rs.getDate("livros.dataCadastro"), 
					rs.getString("livros.titulo"), 
					new Autor(rs.getLong("livros.autorId"), null, "", "" ), 
					new Editora(rs.getLong("livros.idEditora"), null, "", "" ), 
					null, 
					rs.getString("livros.ano"),
					rs.getString("livros.isbn"),
					rs.getInt("livros.ano"),
					rs.getString("livros.sinopse"),
					rs.getDouble("livros.altura"), 
					rs.getDouble("livros.peso"), 
					rs.getDouble("livros.profundidade"), 
					rs.getDouble("livros.preco"), 
					rs.getString("livros.codigoBarras"), 
					rs.getInt("livros.status"),
					rs.getString("livros.capa"),				
					new GrupoPrecificacao(rs.getLong("livros.idGrupoPrecificacao"), null, "", 0, 0), 
					rs.getString("livros.edicao"));

				livro.setLargura(rs.getDouble("livros.largura"));

				list.add(livro);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}			

			return null;
		}
	}
	
	public ArrayList select (Campo[] campos) {
		PreparedStatement pst = null;
		try {
			CriaFiltragem filtro = new CriaFiltragem();
			
			String where = filtro.processa(campos);

			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from livros "+
					   "inner join autores on livros.autorId = autores.id "+
					   "inner join editoras on livros.idEditora = editoras.id "+
					   "inner join grupos_precificacao on livros.idGrupoPrecificacao = grupos_precificacao.id " + where);
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);			

			ResultSet rs = pst.executeQuery();			
			ArrayList<Livro> list = new ArrayList();
			
			while (rs.next()) {
				Livro livro = new Livro(
					rs.getLong("livros.id"), 
					rs.getDate("livros.dataCadastro"), 
					rs.getString("livros.titulo"), 
					new Autor(rs.getLong("livros.autorId"), null, rs.getString("autores.nome"), "" ), 
					new Editora(rs.getLong("livros.idEditora"), null, rs.getString("editoras.nome"), "" ), 
					null, 
					rs.getString("livros.ano"),
					rs.getString("livros.isbn"),
					rs.getInt("livros.ano"),
					rs.getString("livros.sinopse"),
					rs.getDouble("livros.altura"), 
					rs.getDouble("livros.peso"), 
					rs.getDouble("livros.profundidade"), 
					rs.getDouble("livros.preco"), 
					rs.getString("livros.codigoBarras"), 
					rs.getInt("livros.status"),
					rs.getString("livros.capa"),				
					new GrupoPrecificacao(rs.getLong("livros.idGrupoPrecificacao"), null, "", rs.getDouble("grupos_precificacao.porcentagem"), 0), 
					rs.getString("livros.edicao"));

				livro.setLargura(rs.getDouble("livros.largura"));

				livro.setEstoque(contaEstoque(livro, 0));
				livro.setNumeroVendas(contaVendas(livro));

				list.add(livro);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}			

			return null;
		}
	}

	public void getSolicitacoesAtivacao(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from solicitacoes_ativacao_livro " +
				       "inner join livros on livros.id = solicitacoes_ativacao_livro.idLivro " +
				       "inner join categorias_ativacao on categorias_ativacao.id = solicitacoes_ativacao_livro.idCategoria");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<SolicitacaoAtivacaoLivro> list = new ArrayList();
			
			while (rs.next()) {
				Livro livro = new Livro(
					rs.getLong("livros.id"),
					rs.getDate("livros.dataCadastro")
				);
				livro.setCapa(rs.getString("livros.capa"));
				livro.setTitulo(rs.getString("livros.titulo"));
				CategoriaAtivacao categoria = new CategoriaAtivacao(
					rs.getLong("categorias_ativacao.id"),
					rs.getDate("categorias_ativacao.dataCadastro"),
					rs.getString("categorias_ativacao.nome")
				);
				SolicitacaoAtivacaoLivro sol = new SolicitacaoAtivacaoLivro(
					rs.getLong("solicitacoes_ativacao_livro.id"),
					rs.getDate("solicitacoes_ativacao_livro.dataEntrada"),
					categoria,
					rs.getString("solicitacoes_ativacao_livro.justificativa"),
					livro
				);
				list.add(sol);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void getSolicitacoesInativacao(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from solicitacoes_inativacao_livro " +
				       "inner join livros on livros.id = solicitacoes_inativacao_livro.idLivro " +
				       "inner join categorias_inativacao on categorias_inativacao.id = solicitacoes_inativacao_livro.idCategoria");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<SolicitacaoInativacaoLivro> list = new ArrayList();
			
			while (rs.next()) {
				Livro livro = new Livro(
					rs.getLong("livros.id"),
					rs.getDate("livros.dataCadastro")
				);
				livro.setCapa(rs.getString("livros.capa"));
				livro.setTitulo(rs.getString("livros.titulo"));
				CategoriaInativacao categoria = new CategoriaInativacao(
					rs.getLong("categorias_inativacao.id"),
					rs.getDate("categorias_inativacao.dataCadastro"),
					rs.getString("categorias_inativacao.nome")
				);
				SolicitacaoInativacaoLivro sol = new SolicitacaoInativacaoLivro(
					rs.getLong("solicitacoes_inativacao_livro.id"),
					rs.getDate("solicitacoes_inativacao_livro.dataEntrada"),
					categoria,
					rs.getString("solicitacoes_inativacao_livro.justificativa"),
					livro
				);
				list.add(sol);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList selectEstoque (long id, Campo[] campos) {
		PreparedStatement pst = null;
		try {
			CriaFiltragem filtro = new CriaFiltragem();
			String where = filtro.processa(campos);

			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from livros_estoque left join fornecedores on livros_estoque.fornecedorId = fornecedores.id left join usuarios on usuarios.id = livros_estoque.idUsuarioAdmin " + where);
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			//pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<LivroEstoque> list = new ArrayList();
			
			while (rs.next()) {
				LivroEstoque estoque = new LivroEstoque(
					rs.getLong("livros_estoque.id"), 
					rs.getDate("livros_estoque.dataCadastro"), 
					rs.getInt("livros_estoque.quantidade"),
					rs.getDouble("livros_estoque.custo"),
					rs.getDate("livros_estoque.dataEntrada"),
					new Fornecedor(rs.getLong("fornecedores.id"), rs.getDate("fornecedores.dataCadastro"), rs.getString("fornecedores.nome"), rs.getString("fornecedores.email"), rs.getInt("fornecedores.status"), null, null),
					new Livro(rs.getLong("livros_estoque.id"), new Date()),
					rs.getInt("livros_estoque.tipoMovimentacao")
				);

				estoque.setUsuarioResponsavel(new Usuario(
					rs.getLong("usuarios.id"), 
					rs.getDate("usuarios.dataCadastro"), 
					rs.getString("usuarios.nome")
				));

				list.add(estoque);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}			

			return null;
		}
	}
	
	//Select Single lista um livro só mais os detalhes dele
	
	public Livro selectSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from livros "+
					   "inner join autores on livros.autorId = autores.id "+
					   "inner join editoras on livros.idEditora = editoras.id "+
					   "inner join grupos_precificacao on livros.idGrupoPrecificacao = grupos_precificacao.id "+
					   "where livros.id = ?");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				this.getCategoriasDoLivro(rs.getLong("livros.id"));
				Categoria[] categorias = this.selectCategoriasVal;

				System.out.println("as categorias");
				System.out.println(categorias.length);

				Livro livro = new Livro(
					rs.getLong("livros.id"),
					rs.getDate("livros.dataCadastro"),
					rs.getString("livros.titulo"),
					new Autor(rs.getLong("autores.id"), rs.getDate("autores.dataCadastro"), rs.getString("autores.nome"), rs.getString("autores.resumo")),
					new Editora(rs.getLong("editoras.id"), rs.getDate("editoras.dataCadastro"), rs.getString("editoras.nome"), rs.getString("editoras.descricao")),
					categorias,
					rs.getString("livros.ano"),
					rs.getString("livros.isbn"),
					rs.getInt("livros.numeroPaginas"),
					rs.getString("livros.sinopse"),
					rs.getDouble("livros.altura"),
					rs.getDouble("livros.peso"),
					rs.getDouble("livros.profundidade"),
					rs.getDouble("livros.preco"),
					rs.getString("livros.codigoBarras"),
					rs.getInt("livros.status"),
					rs.getString("livros.capa"),
					new GrupoPrecificacao(rs.getLong("grupos_precificacao.id"), null, null, rs.getDouble("grupos_precificacao.porcentagem"), 0),
					rs.getString("livros.edicao")
				);

				livro.setLargura(rs.getDouble("livros.largura"));

				//public Livro(id, Date dataCadastro, String titulo, Autor autor, Editora editora, Categoria[] categorias, String ano, String isbn, int numeroPaginas, String sinopse, double altura, double peso, double profundidade, double preco, String codigoBarras, int status, String capa, GrupoPrecificacao grupoPrecificacao, String edicao) {

				this.selectSingleVal = livro;
				return this.selectSingleVal;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}			

			return null;
		}
	}

	private Categoria[] getCategoriasDoLivro(long idLivro) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();

			sql.append("select * from categorias " +
				"inner join livros_categorias on livros_categorias.idCategoria = categorias.id " +
				"where livros_categorias.idLivro = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idLivro);		
			ResultSet rs = pst.executeQuery();

			ArrayList<Categoria> lista = new ArrayList();
			while (rs.next()) {
				lista.add(new Categoria(
						rs.getLong("categorias.id"),
						rs.getDate("categorias.dataCadastro"),
						rs.getString("categorias.nome")
				));
			}

			Categoria[] categorias = new Categoria[lista.size()];
			lista.toArray(categorias);

			this.selectCategoriasVal = categorias;
			return categorias;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}			

			return null;
		}
	}


	public void insert(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO livros (titulo, autorId, idEditora, ano, isbn, numeroPaginas, sinopse, altura, largura, peso, profundidade, preco, codigoBarras, status, dataCadastro, edicao, idGrupoPrecificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date();  

			pst.setString(1, livro.getTitulo());
			pst.setLong(2, livro.getAutor().getId());
			pst.setLong(3, livro.getEditora().getId());
			pst.setString(4, livro.getAno());
			pst.setString(5, livro.getIsbn());
			pst.setInt(6, livro.getNumeroPaginas());
			pst.setString(7, livro.getSinopse());
			pst.setDouble(8, livro.getAltura());
			pst.setDouble(9, livro.getLargura());
			pst.setDouble(10, livro.getPeso());
			pst.setDouble(11, livro.getProfundidade());
			pst.setDouble(12, livro.getPreco());
			pst.setString(13, livro.getCodigoBarras());
			pst.setInt(14, livro.getStatus());
			pst.setDate(15, new java.sql.Date(livro.getDataCadastro().getTime()));
			pst.setString(16, livro.getEdicao());
			pst.setLong(17, livro.getGrupoPrecificacao().getId());
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int idLivro = 0;
			if (rs.next()) {
				idLivro = rs.getInt(1);
			}
			livro.setId(idLivro);

			salvaCategorias(livro);
			
			connection.commit();
		}catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertEstoque(LivroEstoque livroEstoque) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO livros_estoque (dataCadastro, quantidade, custo, dataEntrada, fornecedorId, livroId, tipoMovimentacao, idUsuarioAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date();  

			pst.setDate(1, new java.sql.Date(livroEstoque.getDataCadastro().getTime()));
			pst.setInt(2, Math.max(1, livroEstoque.getQuantidade()));
			pst.setDouble(3, livroEstoque.getCusto());
			pst.setDate(4, new java.sql.Date(livroEstoque.getDataEntrada().getTime()));
			pst.setLong(5, livroEstoque.getFornecedor().getId());
			pst.setLong(6, livroEstoque.getLivro().getId());
			pst.setInt(7, 1);
			pst.setLong(8, livroEstoque.getUsuarioResponsavel().getId());
			// 1 = Entrada (cadastro no admin)
			// 2 = Saída (venda)
			// 3 = Entrada (troca)
			// 4 = Saída (troca)

			pst.executeUpdate();
			
			/*ResultSet rs = pst.getGeneratedKeys();
			int idLivro = 0;
			if (rs.next()) {
				idLivro = rs.getInt(1);
			}
			livro.setId(idLivro);*/			
			
			connection.commit();

		}catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} /*finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}

	public void baixaEstoque(ItemCarrinho itemCarrinho) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO livros_estoque (dataCadastro, quantidade, custo, dataEntrada, fornecedorId, livroId, tipoMovimentacao) VALUES (?, ?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date();  

			pst.setDate(1, new java.sql.Date(new Date().getTime()));
			pst.setInt(2, itemCarrinho.getQuantidade());			
			pst.setDouble(3, 0);
			pst.setDate(4, new java.sql.Date(new Date().getTime()));
			pst.setLong(5, 0);
			pst.setLong(6, itemCarrinho.getLivro().getId());
			pst.setInt(7, 2);
			//ItemCarrinho(long id, Date dataCadastro, Livro livro, int quantidade, Cliente cliente)
			// 1 = Entrada (cadastro no admin)
			// 2 = Saída (venda)
			// 3 = Entrada (troca)
			// 4 = Saída (troca)

			pst.executeUpdate();
		}catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public int contaEstoque(Livro livro, long idCarrinho) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			String bloq = "- (SELECT IFNULL(sum(bloqueios.quantidade),0) as z FROM bloqueios_produtos as bloqueios where bloqueios.idLivro = ?)";
			
			if (idCarrinho > 0) {
				bloq = "- (SELECT IFNULL(sum(bloqueios.quantidade),0) as z FROM bloqueios_produtos as bloqueios where bloqueios.idLivro = ? and bloqueios.idCarrinho <> ?)";
			}

			sql.append("SELECT (SELECT sum(entradas.quantidade) as x FROM livros_estoque as entradas where entradas.livroId = ? and (entradas.tipoMovimentacao = 1 or entradas.tipoMovimentacao = 3)) - (SELECT IFNULL(sum(baixas.quantidade),0) as y FROM livros_estoque as baixas where baixas.livroId = ? and (baixas.tipoMovimentacao = 2 or baixas.tipoMovimentacao = 4)) " + bloq);
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, livro.getId());
			pst.setLong(2, livro.getId());
			pst.setLong(3, livro.getId());

			if (idCarrinho > 0) {
				pst.setLong(4, idCarrinho);
			}

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int contaVendas(Livro livro) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT sum(quantidade) FROM livros_estoque where livroId = ? and tipoMovimentacao = 2");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, livro.getId());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	public void update(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livros SET " +
					   "titulo = ?, autorId = ?, edicao = ?, " +
					   "capa = ?, idEditora = ?, ano = ?, " +
					   "isbn = ?, numeroPaginas = ?, sinopse = ?, " +
					   "altura = ?, largura = ?, peso = ?, " +
					   "profundidade = ?, codigoBarras = ?, idGrupoPrecificacao = ? " +
					   "WHERE livros.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, livro.getTitulo());
			pst.setLong(2, livro.getAutor().getId());
			pst.setString(3, livro.getEdicao());

			pst.setString(4, livro.getCapa());
			pst.setLong(5, livro.getEditora().getId());
			pst.setString(6, livro.getAno());

			pst.setString(7, livro.getIsbn());
			pst.setInt(8, livro.getNumeroPaginas());
			pst.setString(9, livro.getSinopse());

			pst.setDouble(10, livro.getAltura());
			pst.setDouble(11, livro.getLargura());
			pst.setDouble(12, livro.getPeso());

			System.out.println("a largura 2  ====    ==========");
			System.out.println(livro.getLargura());

			pst.setDouble(13, livro.getProfundidade());
			pst.setString(14, livro.getCodigoBarras());
			pst.setLong(15, livro.getGrupoPrecificacao().getId());

			pst.setLong(16, livro.getId());
			
			pst.executeUpdate();

			//alterar categorias
			if (livro.getCategorias() != null) {
				salvaCategorias(livro);
			}

			connection.commit();
		
		} catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updatePreco(Livro livro, boolean gerenteVendas) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livros SET preco = ? WHERE livros.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDouble(1, livro.getPreco());
			pst.setLong(2, livro.getId());
			
			pst.executeUpdate();

			//alterar categorias
			if (livro.getCategorias() != null) {
				salvaCategorias(livro);
			}

			connection.commit();

			if (!gerenteVendas) {
				alteraPreco(livro.getId());
			}
		
		} catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateStatus(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livros SET status = ? WHERE livro.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, livro.getStatus());
			pst.setLong(2, livro.getId());
			
			pst.executeUpdate();
			connection.commit();			
		} catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM livros WHERE id = ?");
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);
			pst.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao excluir o registro!");
			e.printStackTrace();
		}
	}

	public void alteraPreco(long idLivro) {
		PreparedStatement pst = null;
		try {
			//Connection conn = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("select max(livros_estoque.custo) as maiorCusto, livros.preco as precoAtual, grupos_precificacao.porcentagem from livros_estoque " +
				"inner join livros on livros.id = livros_estoque.livroId " +
				"inner join grupos_precificacao on grupos_precificacao.id = livros.idGrupoPrecificacao " +
				"where livros_estoque.livroId = ? and livros_estoque.tipoMovimentacao = 1");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idLivro);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				StringBuilder sql2 = new StringBuilder();
				sql2.append("update livros set preco = ? where id = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);

				pst.setDouble(1, Math.max(rs.getDouble("precoAtual"), rs.getDouble("maiorCusto") * (1 + (rs.getDouble("grupos_precificacao.porcentagem") / 100 ) ) ));
				pst.setLong(2, idLivro);
				
				pst.executeUpdate();

				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList getCategorias() {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from categorias;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<Categoria> list = new ArrayList();
			
			while (rs.next()) {
				Categoria categoria = new Categoria(
					rs.getLong("categorias.id"), 
					rs.getDate("categorias.dataCadastro"), 
					rs.getString("categorias.nome"));

				list.add(categoria);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList getEditoras() {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from editoras;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<Editora> list = new ArrayList();
			
			while (rs.next()) {
				Editora editora = new Editora(
					rs.getLong("editoras.id"), 
					rs.getDate("editoras.dataCadastro"), 
					rs.getString("editoras.nome"),
					rs.getString("editoras.descricao"));

				list.add(editora);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList getAutores() {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from autores;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<Autor> list = new ArrayList();
			
			while (rs.next()) {
				Autor autor = new Autor(
					rs.getLong("autores.id"), 
					rs.getDate("autores.dataCadastro"), 
					rs.getString("autores.nome"),
					rs.getString("autores.resumo"));

				list.add(autor);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void getCategoriasInativacao() {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from categorias_inativacao;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<CategoriaInativacao> list = new ArrayList();
			
			while (rs.next()) {
				CategoriaInativacao categoria = new CategoriaInativacao(
					rs.getLong("categorias_inativacao.id"), 
					rs.getDate("categorias_inativacao.dataCadastro"), 
					rs.getString("categorias_inativacao.nome"));

				list.add(categoria);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getCategoriasAtivacao() {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from categorias_ativacao;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<CategoriaAtivacao> list = new ArrayList();
			
			while (rs.next()) {
				CategoriaAtivacao categoria = new CategoriaAtivacao(
					rs.getLong("categorias_ativacao.id"), 
					rs.getDate("categorias_ativacao.dataCadastro"), 
					rs.getString("categorias_ativacao.nome"));

				list.add(categoria);
			}
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirSolicitacaoInativacaoLivro(SolicitacaoInativacaoLivro sol) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO solicitacoes_inativacao_livro (dataEntrada, idCategoria, justificativa, idLivro) VALUES (?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setDate(1, new java.sql.Date(agora.getTime())); 
			pst.setLong(2, sol.getCategoria().getId());
			pst.setString(3, sol.getJustificativa());
			pst.setLong(4, sol.getLivro().getId());
			pst.executeUpdate();

			StringBuilder sql2 = new StringBuilder();
			sql2.append("UPDATE livros SET status = 2 WHERE id = ?");

			PreparedStatement pst2 = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst2.setLong(1, sol.getLivro().getId());

			pst2.executeUpdate();
			
			connection.commit();
		}catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void inserirSolicitacaoAtivacaoLivro(SolicitacaoAtivacaoLivro sol) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO solicitacoes_ativacao_livro (dataEntrada, idCategoria, justificativa, idLivro) VALUES (?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setDate(1, new java.sql.Date(agora.getTime())); 
			pst.setLong(2, sol.getCategoria().getId());
			pst.setString(3, sol.getJustificativa());
			pst.setLong(4, sol.getLivro().getId());
			pst.executeUpdate();

			StringBuilder sql2 = new StringBuilder();
			sql2.append("UPDATE livros SET status = 3 WHERE id = ?");

			PreparedStatement pst2 = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst2.setLong(1, sol.getLivro().getId());

			pst2.executeUpdate();
			
			connection.commit();
		}catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void concluirInativacao(long idLivro, int aceite) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livros SET status = ? WHERE id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(2, idLivro);
		
			if (aceite == 1) {
				pst.setInt(1, 0);
			} else {
				pst.setInt(1, 1);
			}
			
			pst.executeUpdate();

			StringBuilder sql2 = new StringBuilder();
			sql2.append("DELETE FROM solicitacoes_inativacao_livro WHERE idLivro = ?;");
			
			pst = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, idLivro);
		
			pst.executeUpdate();

			connection.commit();		
		} catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
		
	}

	public void concluirAtivacao(long idLivro, int aceite) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livros SET status = ? WHERE id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, aceite);
			pst.setLong(2, idLivro);
		
			pst.executeUpdate();

			StringBuilder sql2 = new StringBuilder();
			sql2.append("DELETE FROM solicitacoes_ativacao_livro WHERE idLivro = ?;");
			
			pst = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, idLivro);
		
			pst.executeUpdate();

			connection.commit();		
		} catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}		
	}

	private void salvaCategorias(Livro livro) throws SQLException, ClassNotFoundException {
		PreparedStatement pst2 = null;
		PreparedStatement pst = null;
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append("SELECT id FROM livros_categorias WHERE idCategoria = ? AND idLivro = ?;");

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO livros_categorias(idCategoria, idLivro) VALUES (?, ?);");
		
		for (Categoria categoria : livro.getCategorias()) {

			pst2 = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst2.setLong(1, categoria.getId());
			pst2.setLong(2, livro.getId());			

			ResultSet rs = pst2.executeQuery();

			if (!rs.next()) {
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, categoria.getId());
				pst.setLong(2, livro.getId());

				pst.executeUpdate();
			}
		}
	}

	public void deleteCategorias(Categoria[] categorias, long idLivro) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		connection = Conexao.getConnectionMySQL();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM livros_categorias WHERE idCategoria = ? AND idLivro = ?;");
		
		for (Categoria categoria : categorias) {		

			pst = connection.prepareStatement(
					sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, categoria.getId());
			pst.setLong(2, idLivro);

			pst.executeUpdate();
		}
	}

	
}

	

