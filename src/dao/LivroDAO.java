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
import model.GrupoPrecificacao;
import model.ItemCarrinho;
import model.Autor;
import model.Editora;
import model.EntidadeDominio;
import model.Fornecedor;
import model.Usuario;
import model.Categoria;
import utils.Campo;

public class LivroDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public Livro selectSingleVal;

	public ArrayList selectHome () {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from livros where status = 1");
			
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
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from livros inner join autores on livros.autorId = autores.id inner join editoras on livros.idEditora = editoras.id inner join grupos_precificacao on livros.idGrupoPrecificacao = grupos_precificacao.id");
			
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
					new GrupoPrecificacao(rs.getLong("livros.idGrupoPrecificacao"), null, "", 0, 0), 
					rs.getString("livros.edicao"));

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
	
	//Select Single lista um livro s� mais os detalhes dele
	
	public Livro selectSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from livros inner join autores on livros.autorId = autores.id inner join editoras on livros.idEditora = editoras.id where livros.id = ?");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Livro livro = new Livro(
					rs.getLong("livros.id"),
					rs.getDate("livros.dataCadastro"),
					rs.getString("livros.titulo"),
					new Autor(rs.getLong("autores.id"), rs.getDate("autores.dataCadastro"), rs.getString("autores.nome"), rs.getString("autores.resumo")),
					new Editora(rs.getLong("editoras.id"), rs.getDate("editoras.dataCadastro"), rs.getString("editoras.nome"), rs.getString("editoras.descricao")),
					null,
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
					null,
					rs.getString("livros.edicao")
				);

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
			// 2 = Sa�da (venda)
			// 3 = Entrada (troca)
			// 4 = Sa�da (troca)

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
			// 2 = Sa�da (venda)
			// 3 = Entrada (troca)
			// 4 = Sa�da (troca)

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
			sql.append("SELECT sum(quantidade) FROM livros_estoque where entradas.livroId = ? and entradas.tipoMovimentacao = 2");
			
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
		/*Livro livro = (Livro) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livro SET titulo = ?, autor = ?, editora = ?, categoria = ?, ano, isbn = ?, numerodepaginas = ?,  sinopse = ?,  altura = ?, largura = ?,peso = ?,profundidade = ?,preco = ?,codigodebarras = ?, status = ?,  WHERE livro.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, livro.getTitulo());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getEditora());
			pst.setString(4, livro.getCategoria());
			pst.setInt(5, livro.getAno());
			pst.setInt(6, livro.getIsbn());
			pst.setInt(7, livro.getNumerodepaginas());
			pst.setString(8, livro.getSinopse());
			pst.setFloat(9, livro.getAltura());
			pst.setFloat(10, livro.getLargura());
			pst.setFloat(11, livro.getPeso());
			pst.setFloat(12, livro.getProfundidade());
			pst.setFloat(13, livro.getPreco());
			pst.setInt(14, livro.getCodigodebarras());
			pst.setInt(15, livro.getStatus());
			
			pst.executeUpdate();
		
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
		}*/
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
			sql.append("select max(livros_estoque.custo) as maiorCusto, grupos_precificacao.porcentagem from livros_estoque " +
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

				pst.setDouble(1, rs.getDouble("maiorCusto") * (1 + (rs.getDouble("grupos_precificacao.porcentagem") / 100 ) ) );
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

	
}

	

