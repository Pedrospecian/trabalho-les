package dao;

import utils.Conexao;
import strategies.CriaFiltragemUsuario;
import strategies.CriaPaginacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Configuracao;
import model.EntidadeDominio;

import utils.Campo;

public class ConfiguracoesDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public Configuracao selectSingleVal;

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from configuracoes;");
	
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Configuracao> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Configuracao configuracao = new Configuracao(
					rs.getLong("configuracoes.id"),
					rs.getString("configuracoes.descricao"),
					rs.getString("configuracoes.valor"),
					rs.getDate("configuracoes.dataAlteracao"));

				list.add(configuracao);
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

	public void insert(EntidadeDominio entidade) {
		
	}

	public void update(EntidadeDominio entidade) {

	}

	public void updateConfiguracoes(Configuracao[] configuracoes) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			java.sql.Date agora = new java.sql.Date(new Date().getTime());

			for (int i = 0; i < configuracoes.length; i++) {			
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE configuracoes SET valor = ?, dataAlteracao = ? WHERE id = ?;");
				
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setString(1, configuracoes[i].getValor());
				pst.setDate(2, agora );
				pst.setLong(3, (long) i + 1);
				
				pst.executeUpdate();
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

	public void resetarConfiguracoes() {
		PreparedStatement pst = null;
		String[] configuracoesPadrao = {"10", "10", "7", "10", "08780220"};
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			java.sql.Date agora = new java.sql.Date(new Date().getTime());

			for (int i = 0; i < configuracoesPadrao.length; i++) {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE configuracoes SET valor = ?, dataAlteracao = ? WHERE id = ?;");
				
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setString(1, configuracoesPadrao[i]);
				pst.setDate(2, agora );
				pst.setLong(3, (long) i + 1);
				
				pst.executeUpdate();
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

	public void implantarDominio() {

	}

	public void delete(long id) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			java.sql.Date agora = new java.sql.Date(new Date().getTime());
			
			//autores
			StringBuilder sql1 = new StringBuilder();
			sql1.append("INSERT IGNORE INTO autores (id, dataCadastro, nome, resumo) VALUES " +
			"(1, '2021-04-12', 'Autor teste', 'teste'), " +
			"(2, '2021-04-12', 'Agatha Christie', 'autora de livros de suspense');");
			
			pst = connection.prepareStatement(sql1.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//bandeiras
			StringBuilder sql2 = new StringBuilder();
			sql2.append("INSERT IGNORE INTO bandeiras (id, dataCadastro, nome) VALUES " +
			"(1, '2021-04-12', 'Visa'), " +
			"(2, '2021-04-12', 'MasterCard');");
			
			pst = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//cartoes_aprovados
			//categorias
			StringBuilder sql4 = new StringBuilder();
			sql4.append("INSERT IGNORE INTO categorias (id, dataCadastro, nome) VALUES " +
			"(1, '2021-04-12', 'cat1'), " +
			"(2, '2021-04-12', 'cat 2'), " +
			"(3, '2021-04-12', 'categoria #3');");
			
			pst = connection.prepareStatement(sql4.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//categorias_ativacao
			StringBuilder sql5 = new StringBuilder();
			sql5.append("INSERT IGNORE INTO categorias_ativacao (id, dataCadastro, nome) VALUES " +
			"(1, '2021-04-12', 'Aumento de demanda'), " +
			"(2, '2021-04-12', 'Lançamento de adaptação'), " +
			"(3, '2021-04-12', 'Outro');");
			
			pst = connection.prepareStatement(sql5.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//categorias_inativacao
			StringBuilder sql6 = new StringBuilder();
			sql6.append("INSERT IGNORE INTO categorias_inativacao (id, dataCadastro, nome) VALUES " +
			"(1, '2021-04-12', 'Falta de demanda'), " +
			"(2, '2021-04-12', 'Conteúdo ofensivo'), " +
			"(3, '2021-04-12', 'Outro');");
			
			pst = connection.prepareStatement(sql6.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//configuracoes
			//editoras
			StringBuilder sql8 = new StringBuilder();
			sql8.append("INSERT IGNORE INTO editoras (id, dataCadastro, nome, descricao) VALUES " +
			"(1, '2021-04-12', 'Editora teste', 'teste'), " +
			"(2, '2021-04-12', 'Editora Ininap', 'Editora de HQs');");
			
			pst = connection.prepareStatement(sql8.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//generos
			StringBuilder sql9 = new StringBuilder();
			sql9.append("INSERT IGNORE INTO generos (id, dataCadastro, nome) VALUES " +
			"(1, '2021-04-12', 'Masculino'), " +
			"(2, '2021-04-12', 'Feminino');");
			
			pst = connection.prepareStatement(sql9.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//tipos_clientes
			//tipos_documentos
			//tipos_enderecos
			//tipos_logradouros
			//tipos_residencias
			//tipos_telefones
			//tipos_usuarios
			
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
}
