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

	public void delete(long id) {
		
	}
}
