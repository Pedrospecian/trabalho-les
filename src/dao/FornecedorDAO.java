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

import model.Pais;
import model.Estado;
import model.Cidade;
import model.Bairro;
import model.Endereco;
import model.Fornecedor;
import model.FuncaoEndereco;
import model.Documento;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoLogradouro;
import model.TipoResidencia;
import model.EntidadeDominio;

import utils.Campo;

public class FornecedorDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public Fornecedor selectSingleVal;
	public Documento selectDocumentoVal;
	public Endereco selectEnderecoVal;

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragem filtro = new CriaFiltragem();
			CriaPaginacao paginacao = new CriaPaginacao();
			String where = filtro.processa(campos);
			String paginacaoStr = paginacao.processa(campos);
			connection = Conexao.getConnectionMySQL();
			
			pst = connection.prepareStatement("select * from fornecedores " + where + " ORDER BY fornecedores.id desc;");
			
	
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Fornecedor> list = new ArrayList();

			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Documento documento = null;
				Endereco endereco = null;

		        Fornecedor fornecedor = new Fornecedor(rs.getLong("fornecedores.id"), rs.getDate("fornecedores.dataCadastro"), rs.getString("fornecedores.nome"), rs.getString("fornecedores.email"), rs.getInt("fornecedores.status"), documento, endereco);

				list.add(fornecedor);
			}
			
			

			/*pst = connection.prepareStatement("select count(fornecedores.id) as resultadosTotal from fornecedores on fornecedores.id = fornecedores.id inner join documentos on documentos.idfornecedores = fornecedores.id " + where + " GROUP BY fornecedores.id");
		    ResultSet rsc = pst.executeQuery();
		    this.countVals = 0;
		    while (rsc.next()) {
		    	this.countVals++;
		    }*/
			
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

	public boolean documentosExistem(Documento[] documentos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from documentos where documentos.codigo = ? and documentos.idTipoDocumento = ?;");

			for (Documento documento : documentos) {		
				pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, documento.getCodigo());
				pst.setLong(2, documento.getTipoDocumento().getId());

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					pst.close();
					connection.close();

					System.out.println("Um dos documentos a ser inseridos ja existe!");

					return true;
				}
			}

			pst.close();
			connection.close();

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	public Fornecedor selectSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from fornecedores " +
					   "inner join documentos on documentos.id = fornecedores.idDocumento " +
					   "inner join tipos_documentos on tipos_documentos.id = documentos.idTipoDocumento " +
					   "inner join enderecos on enderecos.id = fornecedores.idEndereco " +
					   "inner join bairros on bairros.id = enderecos.idBairro " +
					   "inner join cidades on cidades.id = bairros.idCidade " +
					   "inner join estados on estados.id = cidades.idEstado " +
					   "inner join paises on paises.id = estados.idPais " +
					   "inner join tipos_enderecos on tipos_enderecos.id = enderecos.idTipoEndereco " +
					   "inner join funcoes_enderecos on funcoes_enderecos.id = enderecos.idFuncaoEndereco " +
					   "inner join tipos_residencias on tipos_residencias.id = enderecos.idTipoResidencia " +
					   "inner join tipos_logradouros on tipos_logradouros.id = enderecos.idTipoLogradouro " +
					   "where fornecedores.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				TipoDocumento tipoDocumento = new TipoDocumento(
					rs.getLong("documentos.idTipoDocumento"),
					rs.getDate("tipos_documentos.dataCadastro"),
					rs.getString("tipos_documentos.nome"),
					rs.getString("tipos_documentos.descricao")
				);
				Documento documento = new Documento(
					rs.getLong("documentos.id"),
					rs.getDate("documentos.dataCadastro"),
					rs.getString("documentos.codigo"),
					rs.getDate("documentos.validade"),
					tipoDocumento
				);
				TipoResidencia tipoResidencia = new TipoResidencia(
					rs.getLong("enderecos.idTipoResidencia"),
					rs.getDate("tipos_residencias.dataCadastro"),
					rs.getString("tipos_residencias.nome"),
					rs.getString("tipos_residencias.descricao")
				);

				TipoLogradouro tipoLogradouro = new TipoLogradouro(
					rs.getLong("enderecos.idTipoLogradouro"),
					rs.getDate("tipos_logradouros.dataCadastro"),
					rs.getString("tipos_logradouros.nome"),
					rs.getString("tipos_logradouros.descricao")
				);

				FuncaoEndereco funcaoEndereco = new FuncaoEndereco(
					rs.getLong("enderecos.idFuncaoEndereco"),
					rs.getDate("funcoes_enderecos.dataCadastro"),
					rs.getString("funcoes_enderecos.nome"),
					rs.getString("funcoes_enderecos.descricao")
				);

				Pais pais = new Pais(rs.getLong("paises.id"), rs.getDate("paises.dataCadastro"), rs.getString("paises.descricao"));
				Estado estado = new Estado(rs.getLong("estados.id"), rs.getDate("estados.dataCadastro"), rs.getString("estados.descricao"), pais);
				Cidade cidade = new Cidade(rs.getLong("cidades.id"), rs.getDate("cidades.dataCadastro"), rs.getString("cidades.descricao"), estado);
				Bairro bairro = new Bairro(rs.getLong("bairros.id"), rs.getDate("bairros.dataCadastro"), rs.getString("bairros.descricao"), cidade);
				TipoEndereco tipoEndereco = new TipoEndereco(rs.getLong("enderecos.idTipoEndereco"), rs.getDate("tipos_enderecos.dataCadastro"), rs.getString("tipos_enderecos.nome"), rs.getString("tipos_enderecos.descricao"));
				Endereco endereco = new Endereco(
					rs.getLong("enderecos.id"),
					rs.getDate("enderecos.dataCadastro"),
					rs.getString("enderecos.logradouro"),
					rs.getString("enderecos.numero"),
					rs.getString("enderecos.cep"),
					rs.getString("enderecos.complemento"),
					bairro,
					tipoEndereco,
					rs.getString("enderecos.nome"),
					tipoResidencia,
					funcaoEndereco,
					tipoLogradouro,
					rs.getString("enderecos.observacoes")
				);
				//Endereco(x x x x x x x x x TipoResidencia tipoResidencia, FuncaoEndereco funcaoEndereco, TipoLogradouro tipoLogradouro, String observacoes)

				this.selectSingleVal = new Fornecedor(
						rs.getLong("fornecedores.id"),
						rs.getDate("fornecedores.dataCadastro"),
						rs.getString("fornecedores.nome"),
						rs.getString("fornecedores.email"),
						rs.getInt("fornecedores.status"),
						documento,
						endereco);
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
		Fornecedor fornecedor = (Fornecedor) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			salvaDocumento(fornecedor);
			salvaEndereco(fornecedor);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO fornecedores (nome, email, dataCadastro, status, idDocumento, idEndereco) VALUES (?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setString(1, fornecedor.getNome());
			pst.setString(2, fornecedor.getEmail());
			pst.setDate(3, new java.sql.Date(agora.getTime()));
			pst.setInt(4, fornecedor.getStatus());
			pst.setLong(5, fornecedor.getDocumento().getId());
			pst.setLong(6, fornecedor.getEndereco().getId());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				//fornecedor.setId(rs.getInt(1));
				connection.commit();	
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

	private long selectOrInsert(String tableName, String fieldName, String fieldValue, String dependentName, long dependentId) {
		PreparedStatement pst = null;
		PreparedStatement pstInsert = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select id from " + tableName + " where " + fieldName + " = ? and " + dependentName + " = ? limit 1;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, fieldValue);
			pst.setLong(2, dependentId);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) return rs.getLong("id");

			StringBuilder sqlInsert = new StringBuilder();
			sqlInsert.append("INSERT INTO " + tableName + " (dataCadastro, " + fieldName + ", " + dependentName + ") VALUES (?, ?, ?);");

			pstInsert = connection.prepareStatement(sqlInsert.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pstInsert.setDate(1, new java.sql.Date(new Date().getTime()));
			pstInsert.setString(2, fieldValue);
			pstInsert.setLong(3, dependentId);
			
			pstInsert.executeUpdate();

			ResultSet rsInsert = pstInsert.getGeneratedKeys();
			if (rsInsert.next()) return rsInsert.getLong(1);
			
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private void salvaEndereco(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO enderecos(dataCadastro, nome, logradouro, numero, complemento, cep, idTipoEndereco, idBairro, idtipoResidencia, idTipoLogradouro, observacoes, idFuncaoEndereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		
		Endereco endereco = fornecedor.getEndereco();

		long idEstado = this.selectOrInsert("estados", "descricao", endereco.getBairro().getCidade().getEstado().getDescricao(), "idPais", endereco.getBairro().getCidade().getEstado().getPais().getId());
		long idCidade = this.selectOrInsert("cidades", "descricao", endereco.getBairro().getCidade().getDescricao(), "idEstado", idEstado);
		long idBairro = this.selectOrInsert("bairros", "descricao", endereco.getBairro().getDescricao(), "idCidade", idCidade);

		pst = connection.prepareStatement(sql.toString(),
				Statement.RETURN_GENERATED_KEYS);
		pst.setDate(1, new java.sql.Date(new Date().getTime()));
		pst.setString(2, endereco.getNome());			
		pst.setString(3, endereco.getLogradouro());			
		pst.setString(4, endereco.getNumero());
		pst.setString(5, endereco.getComplemento());
		pst.setString(6, endereco.getCep());
		//pst.setLong(7, fornecedor.getId());
		pst.setLong(7, endereco.getTipoEndereco().getId());
		pst.setLong(8, idBairro);
		pst.setLong(9, endereco.getTipoResidencia().getId());
		pst.setLong(10, endereco.getTipoLogradouro().getId());
		pst.setString(11, endereco.getObservacoes());
		pst.setLong(12, endereco.getFuncaoEndereco().getId());

		pst.executeUpdate();

		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next()) {
			fornecedor.getEndereco().setId(rs.getInt(1));
		}
	}
	
	private void salvaDocumento(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO documentos(codigo, validade, idTipoDocumento, dataCadastro) VALUES (?, ?, ?, ?);");
		
		Documento documento = fornecedor.getDocumento();

		pst = connection.prepareStatement(sql.toString(),
				Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, documento.getCodigo());
		pst.setDate(2, new java.sql.Date(documento.getValidade().getTime()));
		pst.setLong(3, documento.getTipoDocumento().getId());
		//pst.setLong(4, fornecedor.getId());
		pst.setDate(4, new java.sql.Date(new Date().getTime()));

		pst.executeUpdate();
		
		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next()) {
			fornecedor.getDocumento().setId(rs.getInt(1));
		}
	}

	public void update(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE fornecedores SET nome = ?, email = ?, status = ? WHERE fornecedores.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, fornecedor.getNome());
			pst.setString(2, fornecedor.getEmail());
			pst.setInt(3, fornecedor.getStatus());
			pst.setLong(4, fornecedor.getId());
			
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
		Fornecedor fornecedor = (Fornecedor) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE fornecedores SET status = ? WHERE fornecedores.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, fornecedor.getStatus());
			pst.setLong(2, fornecedor.getId());
			
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
			sql.append("DELETE FROM fornecedores WHERE id = ?");
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
	
	public void deleteDocument(Documento documento) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		connection = Conexao.getConnectionMySQL();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM documentos WHERE documentos.id = ?;");	

		pst = connection.prepareStatement(
				sql.toString(),
				Statement.RETURN_GENERATED_KEYS);
		pst.setLong(1, documento.getId());

		pst.executeUpdate();
	}

	public void deleteEndereco(Endereco endereco) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		connection = Conexao.getConnectionMySQL();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM enderecos WHERE enderecos.id = ?;");

		pst = connection.prepareStatement(
				sql.toString(),
				Statement.RETURN_GENERATED_KEYS);
		pst.setLong(1, endereco.getId());

		pst.executeUpdate();
	}

	//valida email existente
}
