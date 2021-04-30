package dao;

import utils.Conexao;
import model.EntidadeDominio;
import model.GrupoPrecificacao;
import utils.ResultadosBusca;
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


import utils.ResultadosBusca;
import utils.Campo;
import utils.Conexao;

public class GrupoPrecificacaoDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public GrupoPrecificacao selectSingleVal;
	
	public ArrayList select (Campo[] campos) {
		PreparedStatement pst = null;
		try {
			CriaFiltragem filtro = new CriaFiltragem();
			String where = filtro.processa(campos);
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from grupos_precificacao " + where + " order by grupos_precificacao.id desc;");

			System.out.println("Select * from grupos_precificacao " + where + " order by grupos_precificacao.id desc;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);	
			ResultSet rs = pst.executeQuery();

			ArrayList<GrupoPrecificacao> lista = new ArrayList();
			while (rs.next()) {
				lista.add(new GrupoPrecificacao(
					rs.getLong("grupos_precificacao.id"),
					rs.getDate("grupos_precificacao.dataCadastro"),
					rs.getString("grupos_precificacao.nome"),
					rs.getDouble("grupos_precificacao.porcentagem"),
					rs.getInt("grupos_precificacao.status")
				));

				System.out.println("FUNCIONE...");
			}

			this.selectVals = lista;

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
	
	public GrupoPrecificacao selectSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("Select * from grupos_precificacao where grupos_precificacao.id = ?");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				this.selectSingleVal = new GrupoPrecificacao(
					rs.getLong("grupos_precificacao.id"),
					rs.getDate("grupos_precificacao.dataCadastro"),
					rs.getString("grupos_precificacao.nome"),
					rs.getDouble("grupos_precificacao.porcentagem"),
					rs.getInt("grupos_precificacao.status")
				);
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
		GrupoPrecificacao grupoprecificacao = (GrupoPrecificacao) entidade;
		
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO grupos_precificacao (nome, porcentagem, status, dataCadastro) VALUES (?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date();  

			pst.setString(1, grupoprecificacao.getNome());
			pst.setDouble(2, grupoprecificacao.getPorcentagem());
			pst.setInt(3, grupoprecificacao.getStatus());
			pst.setDate(4, new java.sql.Date(agora.getTime()));
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int idGrupoPrecificacao = 0;
			if (rs.next()) idGrupoPrecificacao = rs.getInt(1);
			grupoprecificacao.setId(idGrupoPrecificacao);

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

	public void update(EntidadeDominio entidade) {
		GrupoPrecificacao grupoprecificacao = (GrupoPrecificacao) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE grupos_precificacao SET nome = ?, porcentagem = ?, status = ? WHERE grupos_precificacao.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, grupoprecificacao.getNome());
			pst.setDouble(2, grupoprecificacao.getPorcentagem());
			pst.setInt(3, grupoprecificacao.getStatus());
			pst.setLong(4, grupoprecificacao.getId());
			
			
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

	public void updateStatus(EntidadeDominio entidade) {
		GrupoPrecificacao grupoprecificacao = (GrupoPrecificacao) entidade;

		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE grupos_precificacao SET status = ? WHERE id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, grupoprecificacao.getStatus());
			pst.setLong(2, grupoprecificacao.getId());
			
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
			sql.append("DELETE FROM grupos_precificacao WHERE id = ?");
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


	
}

	

