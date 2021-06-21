package dao;

import utils.Conexao;
import strategies.CriaFiltragem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.EntidadeDominio;
import model.CupomDesconto;

import utils.Campo;

public class CupomDescontoDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public CupomDesconto selectSingleVal;

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragem filtro = new CriaFiltragem();
			String where = filtro.processa(campos);
			
			pst = connection.prepareStatement("select * from cupons_desconto " + where + " order by cupons_desconto.id desc;");
					
			ResultSet rs = pst.executeQuery();
			
			ArrayList<CupomDesconto> list = new ArrayList();

			while(rs.next()) {
		    	CupomDesconto cd = new CupomDesconto(
		    		rs.getLong("cupons_desconto.id"),
		    		rs.getDate("cupons_desconto.dataCadastro"),		    		
		    		rs.getString("cupons_desconto.nome"),
		    		rs.getDouble("cupons_desconto.valor"),		    		
		    		rs.getDate("cupons_desconto.dataInicio"),
		    		rs.getDate("cupons_desconto.dataFim"),
		    		rs.getInt("cupons_desconto.status")
		    	);

		    	list.add(cd);
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
			
	public CupomDesconto selectSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from cupons_desconto where id = ?");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
		    	CupomDesconto cd = new CupomDesconto(
		    		rs.getLong("cupons_desconto.id"),
		    		rs.getDate("cupons_desconto.dataCadastro"),
		    		rs.getString("cupons_desconto.nome"),
		    		rs.getDouble("cupons_desconto.valor"),
		    		rs.getDate("cupons_desconto.dataInicio"),
		    		rs.getDate("cupons_desconto.dataFim"),
		    		rs.getInt("cupons_desconto.status")
		    	);

		    	this.selectSingleVal = cd;
		    	return cd;
		    } else {
		    	return null;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insert(EntidadeDominio entidade) {
		CupomDesconto cupom = (CupomDesconto) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cupons_desconto (nome, valor, status, dataInicio, dataFim, dataCadastro) VALUES (?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setString(1, cupom.getNome());
			pst.setDouble(2, cupom.getValor());
			pst.setInt(3, cupom.getStatus());
			pst.setDate(4, new java.sql.Date(cupom.getDataInicio().getTime()));
			pst.setDate(5, new java.sql.Date(cupom.getDataFim().getTime()));
			pst.setDate(6, new java.sql.Date(agora.getTime()));			
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int idCupom = 0;
			if (rs.next()) idCupom = rs.getInt(1);
			cupom.setId(idCupom);
			
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

	public void update(EntidadeDominio entidade) {
		CupomDesconto cupom = (CupomDesconto) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cupons_desconto SET nome = ?, valor = ?, status = ?, dataInicio = ?, dataFim = ? WHERE cupons_desconto.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, cupom.getNome());
			pst.setDouble(2, cupom.getValor());
			pst.setInt(3, cupom.getStatus());
			pst.setDate(4, new java.sql.Date(cupom.getDataInicio().getTime()));
			pst.setDate(5, new java.sql.Date(cupom.getDataFim().getTime()));
			pst.setLong(6, cupom.getId());
			
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

	public void updateStatus(EntidadeDominio entidade) {
		CupomDesconto cupom = (CupomDesconto) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cupons_desconto SET status = ? WHERE cupons_desconto.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, cupom.getStatus());
			pst.setLong(2, cupom.getId());
			
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
			sql.append("DELETE FROM cupons_desconto WHERE id = ?");
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

	public CupomDesconto encontraCupomDesconto(String nomeCupomDesconto) {	
		PreparedStatement pst = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from cupons_desconto where nome = ? and dataInicio <= ? and (dataFim is null or dataFim >= ?) and status = 1;");
			
			Connection con = Conexao.getConnectionMySQL();
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setString(1, nomeCupomDesconto);
			pst.setDate(2, new java.sql.Date(agora.getTime()));
			pst.setDate(3, new java.sql.Date(agora.getTime()));
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				CupomDesconto cupomDesconto = new CupomDesconto(
					rs.getLong("cupons_desconto.id"),
					rs.getDate("cupons_desconto.dataCadastro"),
					rs.getString("cupons_desconto.nome"),
					rs.getDouble("cupons_desconto.valor"),
					rs.getDate("cupons_desconto.dataInicio"),
					rs.getDate("cupons_desconto.dataFim"),
					rs.getInt("cupons_desconto.status")
				);

				con.commit();
				
				return cupomDesconto;
			} else {
				return null;
			}
		}  catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar o cupom!");
			e.printStackTrace();
			return null;
		}
	}
}
