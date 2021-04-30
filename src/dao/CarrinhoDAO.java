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

import model.Usuario;
import model.EntidadeDominio;

import utils.Campo;

public class CarrinhoDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public Usuario selectSingleVal;

	public ArrayList select(Campo[] campos) {
		return null;
		/*PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragemUsuario filtro = new CriaFiltragemUsuario();
			CriaPaginacao paginacao = new CriaPaginacao();
			String where = filtro.processa(campos);
			String paginacaoStr = paginacao.processa(campos);
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from usuarios " + where + paginacaoStr + ";");
	
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Usuario> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Usuario usuario = new Usuario(
					rs.getLong("usuarios.id"),
					rs.getDate("usuarios.dataCadastro"),
					rs.getString("usuarios.nome"),
					rs.getString("usuarios.email"),
					rs.getInt("usuarios.status"),
					rs.getString("usuarios.senha"));

				list.add(usuario);
			}

			pst = connection.prepareStatement("select count(usuarios.id) as resultadosTotal from usuarios " + where + ";");
		    ResultSet rsc = pst.executeQuery();
		    this.countVals = 0;
		    while (rsc.next()) {
		    	this.countVals = rsc.getInt("resultadosTotal");
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
		}*/
	}

	public Usuario selectSingle(long id) {
		return null;
		/*PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from usuarios where usuarios.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				this.selectSingleVal = new Usuario(
					rs.getLong("usuarios.id"),
					rs.getDate("usuarios.dataCadastro"),
					rs.getString("usuarios.nome"),
					rs.getString("usuarios.email"),
					rs.getInt("usuarios.status"),
					rs.getString("usuarios.senha"));
				return this.selectSingleVal;
			}
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
		}*/
	}

	public void insert(EntidadeDominio entidade) {
		/*Usuario usuario = (Usuario) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO usuarios(nome, email, senha, status, dataCadastro) VALUES (?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setInt(4, usuario.getStatus());
			pst.setDate(5, new java.sql.Date(agora.getTime()));
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int idUsuario = 0;
			if (rs.next()) idUsuario = rs.getInt(1);
			usuario.setId(idUsuario);
			
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
		}*/
	}

	public void update(EntidadeDominio entidade) {
		/*Usuario usuario = (Usuario) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios SET nome = ?, email = ?, status = ? WHERE usuarios.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setInt(3, usuario.getStatus());
			pst.setLong(4, usuario.getId());
			
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
		}*/
	}

	public void updateStatus(EntidadeDominio entidade) {
		/*Usuario usuario = (Usuario) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios SET status = ? WHERE usuarios.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, usuario.getStatus());
			pst.setLong(2, usuario.getId());
			
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
		}*/
	}

	public void adicionarAoCarrinho() {
		
	}

	public void delete(long id) {
		/*PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM usuarios WHERE id = ?");
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);
			pst.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao excluir o registro!");
			e.printStackTrace();
		}*/
	}
}
