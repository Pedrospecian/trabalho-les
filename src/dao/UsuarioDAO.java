package dao;

import utils.Conexao;
import strategies.CriaFiltragemUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Usuario;
import model.TipoUsuario;
import model.EntidadeDominio;

import utils.Campo;

public class UsuarioDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public Usuario selectSingleVal;

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragemUsuario filtro = new CriaFiltragemUsuario();
			String where = filtro.processa(campos);
			
			pst = connection.prepareStatement("select * from usuarios inner join tipos_usuarios on tipos_usuarios.id = usuarios.idTipoUsuario " + where + " order by usuarios.id desc;");
	
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
					rs.getString("usuarios.senha"),
					new TipoUsuario(
							rs.getLong("tipos_usuarios.id"),
							rs.getDate("tipos_usuarios.dataCadastro"),
							rs.getString("tipos_usuarios.nome"),
							rs.getString("tipos_usuarios.descricao")
					));

				list.add(usuario);
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

	public Usuario selectSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from usuarios inner join tipos_usuarios on tipos_usuarios.id = usuarios.idTipoUsuario where usuarios.id = ?;");
			
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
					rs.getString("usuarios.senha"),
					new TipoUsuario(
							rs.getLong("tipos_usuarios.id"),
							rs.getDate("tipos_usuarios.dataCadastro"),
							rs.getString("tipos_usuarios.nome"),
							rs.getString("tipos_usuarios.descricao")
					));
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
		}
	}

	public void insert(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO usuarios(nome, email, senha, status, dataCadastro, idTipoUsuario) VALUES (?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setInt(4, usuario.getStatus());
			pst.setDate(5, new java.sql.Date(agora.getTime()));
			pst.setLong(6, usuario.getTipoUsuario().getId());
			
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
		}
	}

	public void update(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios SET nome = ?, email = ?, status = ?, idTipoUsuario = ? WHERE usuarios.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setInt(3, usuario.getStatus());
			pst.setLong(4, usuario.getTipoUsuario().getId());
			pst.setLong(5, usuario.getId());
			
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
		Usuario usuario = (Usuario) entidade;
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
		}
	}
	
	public void updateSenha(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios SET senha = ? WHERE usuarios.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, usuario.getSenha());
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
		}
	}

	public boolean validarSenhaExistente(String senha, long idUsuario) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select id from usuarios where usuarios.id = ? and usuarios.senha = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idUsuario);
			pst.setString(2, senha);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}

			return false;
		} catch (Exception e) {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();	
				e.printStackTrace();
				return false;
			} catch (Exception e2) {
				e2.printStackTrace();
				return false;
			}
		}
	}

	public void delete(long id) {
		PreparedStatement pst = null;
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
		}
	}

	public Usuario recuperaUsuarioLogin(String email, String senha) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, email, nome, idTipoUsuario from usuarios where email = ? and senha = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, email);
			pst.setString(2, senha);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				TipoUsuario tipoUsuario = new TipoUsuario(rs.getLong("usuarios.idTipoUsuario"), null, "funcionario", "");

				if (tipoUsuario.getId() == 2) {
					tipoUsuario.setNome("admin");
				}

				if (tipoUsuario.getId() == 3) {
					tipoUsuario.setNome("gerentevendas");
				}
				this.selectSingleVal = new Usuario(
						rs.getLong("usuarios.id"),
						null,
						rs.getString("usuarios.nome"),
						rs.getString("usuarios.email"),
						0, 
						"",
						tipoUsuario
				);
				return this.selectSingleVal;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

	public boolean validarEmailExistente(String email) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select id from usuarios where usuarios.email = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, email);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}

			return false;
		} catch (Exception e) {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();	
				e.printStackTrace();
				return false;
			} catch (Exception e2) {
				e2.printStackTrace();
				return false;
			}
		}
	}
}
