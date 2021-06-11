package dao;

import utils.Conexao;
import strategies.CriaFiltragem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Pais;
import model.Telefone;
import model.Estado;
import model.Cidade;
import model.Bairro;
import model.Bandeira;
import model.CartaoCredito;
import model.Endereco;
import model.Cliente;
import model.Documento;
import model.TipoCliente;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoTelefone;
import model.EntidadeDominio;
import model.TipoResidencia;
import model.TipoLogradouro;
import model.FuncaoEndereco;
import model.Notificacao;
import utils.Campo;

public class ClienteDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	//public int countVals;
	public Cliente selectSingleVal;
	public Endereco selectSingleEnderecoVal;
	public Documento[] selectDocumentosVal;
	public Endereco[] selectEnderecosVal;
	public CartaoCredito[] selectCartoesCreditoVal;
	private Telefone[] selectTelefonesVal;

	public ArrayList selectNotificacao(long idCliente){
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from notificacoes where idCliente = ?;");
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idCliente);
			ResultSet rs = pst.executeQuery();

			ArrayList<Notificacao> list = new ArrayList();

			while (rs.next()) {
				Notificacao notificacao = new Notificacao(
					rs.getLong("notificacoes.id"),
					rs.getDate("notificacoes.dataCadastro"),
					rs.getString("notificacoes.descricao"),
					rs.getLong("notificacoes.idCliente"),
					rs.getString("notificacoes.cor")
					);
				list.add(notificacao);
			}

			this.selectVals = list;
			return this.selectVals;

		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			CriaFiltragem filtro = new CriaFiltragem();
			String where = filtro.processa(campos);
			if (where == null) where = "";

			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select clientes.id, clientes.dataCadastro, clientes.nome, clientes.genero, clientes.dataNascimento, clientes.status, clientes.email, clientes.idTipoCliente, tipos_clientes.dataCadastro, tipos_clientes.nome, tipos_clientes.descricao, " +
				"COUNT(pedidos.id) as totalPedidos " +
				"from clientes inner join tipos_clientes on clientes.idTipoCliente = tipos_clientes.id inner join documentos on documentos.idCliente = clientes.id " +
				"LEFT JOIN pedidos ON (pedidos.idUsuario = clientes.id and pedidos.status <> 1 and pedidos.status <> 8) " + where + " GROUP BY clientes.id ORDER BY clientes.id DESC ;");
	
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Cliente> list = new ArrayList();

			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Documento[] documentos = new Documento[1];
		        Endereco[] enderecos = new Endereco[1];
		        CartaoCredito[] cartoesCredito = new CartaoCredito[1];
		        Telefone[] telefones = new Telefone[1];

				Cliente cliente = new Cliente(
						rs.getLong("clientes.id"),
						rs.getDate("clientes.dataCadastro"),
						documentos, 
						rs.getString("clientes.nome"),
						rs.getInt("clientes.genero"),
						rs.getDate("clientes.dataNascimento"),
						new TipoCliente(rs.getLong("clientes.idTipoCliente"),
								rs.getDate("tipos_clientes.dataCadastro"),
								rs.getString("tipos_clientes.nome"),
								rs.getString("tipos_clientes.descricao")
						),
						enderecos,
						rs.getInt("clientes.status"),
						cartoesCredito,
						rs.getString("clientes.email"),
						null,
						telefones);

				cliente.setTotalPedidos(rs.getInt("totalPedidos"));

				list.add(cliente);
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

			System.out.println("não tem nenhum documento igual ao que esta sendo inserido");

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	public Cliente selectSingle(long id, boolean checkout) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from clientes inner join tipos_clientes on clientes.idTipoCliente = tipos_clientes.id where clientes.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				this.selectDocumentos(id);
				this.selectEnderecos(id, checkout);
				this.selectCartoesCredito(id);
				this.selectTelefones(id);
				Documento[] documentos = this.selectDocumentosVal;
		        Endereco[] enderecos = this.selectEnderecosVal;
		        CartaoCredito[] cartoesCredito = this.selectCartoesCreditoVal;
		        Telefone[] telefones = this.selectTelefonesVal;

		        for (int i = 0; i < cartoesCredito.length; i++) {
		        	if (cartoesCredito[i].getId() == rs.getLong("clientes.idCartaoPreferencial")) {
		        		cartoesCredito[i].setPreferencial(true);
		        	}
		        }

				this.selectSingleVal = new Cliente(
						rs.getLong("clientes.id"),
						rs.getDate("clientes.dataCadastro"),
						documentos, rs.getString("clientes.nome"),
						rs.getInt("clientes.genero"),
						rs.getDate("clientes.dataNascimento"),
						new TipoCliente(rs.getLong("clientes.idTipoCliente"), new Date(), "", ""),
						enderecos,
						rs.getInt("clientes.status"),
						cartoesCredito,
						rs.getString("clientes.email"),
						null,
						telefones
				);
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

	
	public Cliente recuperaClienteLogin(String email, String senha) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, email, nome from clientes where email = ? and senha = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, email);
			pst.setString(2, senha);

			ResultSet rs = pst.executeQuery();
			Documento[] documentos = new Documento[1];
			Endereco[] enderecos = new Endereco[1];
			CartaoCredito[] cartoesCredito = new CartaoCredito[1];
			Telefone[] telefones = new Telefone[1];

			while (rs.next()) {
				//Cliente(long id, Date dataCadastro, Documento[] documentos, String nome, int genero, Date dataNascimento, TipoCliente tipoCliente, Endereco[] enderecos, int status, CartaoCredito[] cartoesCredito, String email, String senha, Telefone[] telefones)
				this.selectSingleVal = new Cliente(
						rs.getLong("clientes.id"),
						null,
						documentos,
						rs.getString("clientes.nome"),
						0, 
						null,
						null,
						enderecos,
						0,
						cartoesCredito,
						rs.getString("clientes.email"),
						null,
						telefones
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

	private Documento[] selectDocumentos(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from documentos inner join tipos_documentos on documentos.idTipoDocumento = tipos_documentos.id where documentos.idCliente = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);		
			ResultSet rs = pst.executeQuery();

			ArrayList<Documento> lista = new ArrayList();
			while (rs.next()) {
				TipoDocumento tipoDocumento = new TipoDocumento(rs.getLong("documentos.idTipoDocumento"), rs.getDate("tipos_documentos.dataCadastro"), rs.getString("tipos_documentos.nome"), rs.getString("tipos_documentos.descricao"));
				lista.add(new Documento(rs.getLong("documentos.id"), rs.getDate("documentos.dataCadastro"), rs.getString("documentos.codigo"), rs.getDate("documentos.validade"), tipoDocumento));
			}

			Documento[] documentos = new Documento[lista.size()];
			lista.toArray(documentos);

			this.selectDocumentosVal = documentos;
			return documentos;
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

	private Endereco[] selectEnderecos(long id, boolean checkout) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			String queryCheckout = "";

			if (checkout) {
				queryCheckout = "and (enderecos.idFuncaoEndereco = 2 or enderecos.idFuncaoEndereco = 3)";
			}

			sql.append("select * from enderecos inner join tipos_enderecos on enderecos.idTipoEndereco = tipos_enderecos.id " +
				"inner join bairros on enderecos.idBairro = bairros.id inner join cidades on cidades.id = bairros.idCidade " +
				"inner join estados on estados.id = cidades.idEstado inner join paises on paises.id = estados.idPais "+
				"inner join tipos_logradouros on tipos_logradouros.id = enderecos.idTipoLogradouro "+
				"inner join tipos_residencias on tipos_residencias.id = enderecos.idTipoResidencia "+
				"inner join funcoes_enderecos on funcoes_enderecos.id = enderecos.idFuncaoEndereco where enderecos.idCliente = ? " + queryCheckout + ";");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);		
			ResultSet rs = pst.executeQuery();

			ArrayList<Endereco> lista = new ArrayList();
			while (rs.next()) {
				Pais pais = new Pais(rs.getLong("paises.id"), rs.getDate("paises.dataCadastro"), rs.getString("paises.descricao"));
				Estado estado = new Estado(rs.getLong("estados.id"), rs.getDate("estados.dataCadastro"), rs.getString("estados.descricao"), pais);
				Cidade cidade = new Cidade(rs.getLong("cidades.id"), rs.getDate("cidades.dataCadastro"), rs.getString("cidades.descricao"), estado);
				Bairro bairro = new Bairro(rs.getLong("bairros.id"), rs.getDate("bairros.dataCadastro"), rs.getString("bairros.descricao"), cidade);
				TipoEndereco tipoEndereco = new TipoEndereco(rs.getLong("enderecos.idTipoEndereco"), rs.getDate("tipos_enderecos.dataCadastro"), rs.getString("tipos_enderecos.nome"), rs.getString("tipos_enderecos.descricao"));

				TipoResidencia tipoResidencia = new TipoResidencia(rs.getLong("enderecos.idTipoResidencia"), rs.getDate("tipos_residencias.dataCadastro"), rs.getString("tipos_residencias.nome"), rs.getString("tipos_residencias.descricao"));

				FuncaoEndereco funcaoEndereco = new FuncaoEndereco(rs.getLong("enderecos.idFuncaoEndereco"), rs.getDate("funcoes_enderecos.dataCadastro"), rs.getString("funcoes_enderecos.nome"), rs.getString("funcoes_enderecos.descricao"));

				TipoLogradouro tipoLogradouro = new TipoLogradouro(rs.getLong("enderecos.idTipoLogradouro"), rs.getDate("tipos_logradouros.dataCadastro"), rs.getString("tipos_logradouros.nome"), rs.getString("tipos_logradouros.descricao"));

				lista.add(new Endereco(
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
						rs.getString("enderecos.observacoes")));
				//(long id, Date dataCadastro, String logradouro, String numero, String cep, String complemento, Bairro bairro, TipoEndereco tipoEndereco, String nome, TipoResidencia tipoResidencia, FuncaoEndereco funcaoEndereco, TipoLogradouro tipoLogradouro, String observacoes)
			}

			Endereco[] enderecos = new Endereco[lista.size()];
			lista.toArray(enderecos);

			this.selectEnderecosVal = enderecos;
			return enderecos;
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

	private CartaoCredito[] selectCartoesCredito(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from cartoes_credito inner join bandeiras on cartoes_credito.idBandeira = bandeiras.id where cartoes_credito.idUsuario = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);		
			ResultSet rs = pst.executeQuery();

			ArrayList<CartaoCredito> lista = new ArrayList();
			while (rs.next()) {
				lista.add(new CartaoCredito(
					rs.getLong("cartoes_credito.id"),
					rs.getDate("cartoes_credito.dataCadastro"),
					rs.getString("cartoes_credito.nome"),
					rs.getString("cartoes_credito.numero"),
					rs.getString("cartoes_credito.cvv"),
					rs.getDate("cartoes_credito.dataExpiracao"),
					new Bandeira(
						rs.getLong("bandeiras.id"),
						rs.getDate("bandeiras.dataCadastro"),
						rs.getString("bandeiras.nome")
					))
				);

				// String nome, String numero, String cvv, Date dataExpiracao, Bandeira bandeira)
			}

			CartaoCredito[] cartoesCredito = new CartaoCredito[lista.size()];
			lista.toArray(cartoesCredito);

			this.selectCartoesCreditoVal = cartoesCredito;
			return cartoesCredito;
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

	private Telefone[] selectTelefones(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from telefones inner join tipos_telefones on tipos_telefones.id = telefones.idTipoTelefone where telefones.idUsuario = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);		
			ResultSet rs = pst.executeQuery();

			ArrayList<Telefone> lista = new ArrayList();
			while (rs.next()) {
				lista.add(new Telefone(
					rs.getLong("telefones.id"),
					rs.getDate("telefones.dataCadastro"),
					new TipoTelefone(
						rs.getLong("tipos_telefones.id"),
						rs.getDate("tipos_telefones.dataCadastro"),
						rs.getString("tipos_telefones.nome"),
						rs.getString("tipos_telefones.descricao")
					),
					rs.getString("telefones.ddd"),
					rs.getString("telefones.numero")
				));

				//Telefone(long id, Date dataCadastro, TipoTelefone tipoTelefone, String ddd, String numero)
				//(long id, Date dataCadastro, String nome, String descricao)
			}

			Telefone[] telefones = new Telefone[lista.size()];
			lista.toArray(telefones);

			this.selectTelefonesVal = telefones;
			return telefones;
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
		Cliente cliente = (Cliente) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO clientes(nome, genero, dataNascimento, idTipoCliente, dataCadastro, status, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setString(1, cliente.getNome());
			pst.setInt(2, cliente.getGenero());
			pst.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
			pst.setLong(4, cliente.getTipoCliente().getId());
			pst.setDate(5, new java.sql.Date(agora.getTime()));
			pst.setInt(6, cliente.getStatus());
			pst.setString(7, cliente.getEmail());
			pst.setString(8, cliente.getSenha());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int idCliente = 0;
			if (rs.next()) idCliente = rs.getInt(1);
			cliente.setId(idCliente);
			
			salvaDocumentos(cliente);
			salvaEnderecos(cliente);
			salvaCartoesCredito(cliente);
			salvaTelefones(cliente);

			this.selectSingleVal = cliente;
			
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
	
	private void salvaEnderecos(Cliente cliente) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO enderecos(dataCadastro, nome, logradouro, numero, complemento, cep, idCliente, idTipoEndereco, idBairro, idTipoResidencia, idFuncaoEndereco, idTipoLogradouro, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		
		for (Endereco endereco : cliente.getEnderecos()) {

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
			pst.setLong(7, cliente.getId());
			pst.setLong(8, endereco.getTipoEndereco().getId());
			pst.setLong(9, idBairro);

			pst.setLong(10, endereco.getTipoResidencia().getId());
			pst.setLong(11, endereco.getFuncaoEndereco().getId());
			pst.setLong(12, endereco.getTipoLogradouro().getId());
			pst.setString(13, endereco.getObservacoes());

			pst.executeUpdate();

			ResultSet rsInsert = pst.getGeneratedKeys();
			if (rsInsert.next()) endereco.setId(rsInsert.getLong(1));
		}
	}
	
	private void salvaDocumentos(Cliente cliente) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO documentos(codigo, validade, idTipoDocumento, idCliente, dataCadastro) VALUES (?, ?, ?, ?, ?);");
		
		for (Documento documento : cliente.getDocumentos()) {		

			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, documento.getCodigo());
			pst.setDate(2, new java.sql.Date(documento.getValidade().getTime()));
			pst.setLong(3, documento.getTipoDocumento().getId());
			pst.setLong(4, cliente.getId());
			pst.setDate(5, new java.sql.Date(new Date().getTime()));

			pst.executeUpdate();

			ResultSet rsInsert = pst.getGeneratedKeys();
			if (rsInsert.next()) documento.setId(rsInsert.getLong(1));
		}
	}


	private void salvaCartoesCredito(Cliente cliente) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cartoes_credito(cvv, dataExpiracao, nome, idUsuario, dataCadastro, numero, idBandeira) VALUES (?, ?, ?, ?, ?, ?, ?);");
		
		for (CartaoCredito cartaoCredito : cliente.getCartoesCredito()) {		

			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cartaoCredito.getCvv());
			pst.setDate(2, new java.sql.Date(cartaoCredito.getDataExpiracao().getTime()));
			pst.setString(3, cartaoCredito.getNome());
			pst.setLong(4, cliente.getId());
			pst.setDate(5, new java.sql.Date(new Date().getTime()));
			pst.setString(6, cartaoCredito.getNumero());
			pst.setLong(7, cartaoCredito.getBandeira().getId());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				cartaoCredito.setId(rs.getInt(1));
			}
		}
	}

	private void salvaTelefones(Cliente cliente) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO telefones(dataCadastro, ddd, numero, idTipoTelefone, idUsuario) VALUES (?, ?, ?, ?, ?);");
		
		for (Telefone telefone : cliente.getTelefones()) {		

			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, new java.sql.Date(new Date().getTime()));
			pst.setString(2, telefone.getDdd());
			pst.setString(3, telefone.getNumero() );
			pst.setLong(4, telefone.getTipoTelefone().getId() );
			pst.setLong(5, cliente.getId());

			pst.executeUpdate();

			ResultSet rsInsert = pst.getGeneratedKeys();
			if (rsInsert.next()) telefone.setId(rsInsert.getLong(1));
		}
	}

	public void update(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE clientes SET nome = ?, genero = ?, dataNascimento = ?, idTipoCliente = ?, status = ?, email = ? WHERE clientes.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, cliente.getNome());
			pst.setInt(2, cliente.getGenero());
			pst.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
			pst.setLong(4, cliente.getTipoCliente().getId());
			pst.setInt(5, cliente.getStatus());
			pst.setString(6, cliente.getEmail());
			pst.setLong(7, cliente.getId());
			
			pst.executeUpdate();

			if (cliente.getDocumentos() != null) {
				salvaDocumentos(cliente);
			}

			if (cliente.getEnderecos() != null) {
				salvaEnderecos(cliente);
			}

			if (cliente.getCartoesCredito() != null) {
				salvaCartoesCredito(cliente);
			}

			if (cliente.getTelefones() != null) {
				salvaTelefones(cliente);
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

	public void updateStatus(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE clientes SET status = ? WHERE clientes.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, cliente.getStatus());
			pst.setLong(2, cliente.getId());
			
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
			sql.append("DELETE FROM clientes WHERE id = ?");
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

	public void deleteNotificacoes(long idCliente) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM notificacoes WHERE idCliente = ?");
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idCliente);
			pst.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao excluir o registro!");
			e.printStackTrace();
		}
	}
	
	public void deleteDocuments(Documento[] documentos) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		connection = Conexao.getConnectionMySQL();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM documentos WHERE documentos.id = ?;");
		
		for (Documento documento : documentos) {		

			pst = connection.prepareStatement(
					sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, documento.getId());

			pst.executeUpdate();
		}
	}

	public void deleteEnderecos(Endereco[] enderecos) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		connection = Conexao.getConnectionMySQL();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM enderecos WHERE enderecos.id = ?;");
		
		for (Endereco endereco : enderecos) {		

			pst = connection.prepareStatement(
					sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, endereco.getId());

			pst.executeUpdate();
		}
	}

	public void deleteCartoesCredito(CartaoCredito[] cartoesCredito) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		connection = Conexao.getConnectionMySQL();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM cartoes_credito WHERE cartoes_credito.id = ?;");
		
		for (CartaoCredito cartaoCredito : cartoesCredito) {		

			pst = connection.prepareStatement(
					sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, cartaoCredito.getId());

			pst.executeUpdate();
		}
	}

	public void deleteTelefones(Telefone[] telefones) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		connection = Conexao.getConnectionMySQL();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM telefones WHERE telefones.id = ?;");
		
		for (Telefone telefone : telefones) {		

			pst = connection.prepareStatement(
					sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, telefone.getId());

			pst.executeUpdate();
		}
	}

	public Endereco selectEnderecoSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from enderecos where enderecos.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				this.selectSingleEnderecoVal = new Endereco(
					rs.getLong("enderecos.id"),
					rs.getDate("enderecos.dataCadastro"),
					rs.getString("enderecos.logradouro"),
					rs.getString("enderecos.numero"),
					rs.getString("enderecos.cep"),
					rs.getString("enderecos.complemento"),
					null,
					null,
					rs.getString("enderecos.nome"),
					null,
					null,
					null,
					rs.getString("enderecos.observacoes"));
				return this.selectSingleEnderecoVal;
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

	public void updateSenha(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE clientes SET senha = ? WHERE clientes.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, cliente.getSenha());
			pst.setLong(2, cliente.getId());
			
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

	public boolean validarSenhaExistente(String senha, long idCliente) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select id from clientes where clientes.id = ? and clientes.senha = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idCliente);
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

	public boolean validarEmailExistente(String email) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select id from clientes where clientes.email = ?;");
			
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

	public void setCartaoPreferencial(Cliente cliente, CartaoCredito cartaoCredito) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE clientes SET idCartaoPreferencial = ? WHERE clientes.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, cartaoCredito.getId());
			pst.setLong(2, cliente.getId());
			
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

}
