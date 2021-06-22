package dao;

import utils.Conexao;
import utils.DadosCalculoFrete;
import utils.ItemGrafico;
import strategies.CriaFiltragem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Pedido;
import model.SolicitacaoTroca;
import model.TipoEndereco;
import model.TipoLogradouro;
import model.TipoResidencia;
import model.EntidadeDominio;
import model.Estado;
import model.FuncaoEndereco;
import model.Genero;
import model.ItemCarrinho;
import model.Bairro;
import model.Carrinho;
import model.CartaoCredito;
import model.Cidade;
import model.Livro;
import model.LivroEstoque;
import model.Pais;
import model.CupomTroca;
import model.Endereco;
import model.Cliente;
import model.CupomDesconto;
import utils.Campo;

public class PedidoDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public Pedido selectSingleVal;
	public DadosCalculoFrete selectDadosCalculoFreteSingle;

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragem filtro = new CriaFiltragem();
			String where = filtro.processa(campos);
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from pedidos inner join carrinhos on carrinhos.id = pedidos.idCarrinho inner join clientes on clientes.id = pedidos.idUsuario " + where + " order by pedidos.id desc;");
			
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Pedido> list = new ArrayList();
			
			while (rs.next()) {
				Pedido pedido = new Pedido(
					rs.getLong("pedidos.id"),
					rs.getDate("pedidos.dataCadastro"),
					new Cliente(
						rs.getLong("clientes.id"),
						rs.getDate("clientes.dataCadastro"),
						null,
						rs.getString("clientes.nome"),
						new Genero(rs.getInt("clientes.genero"), null, null),
						rs.getDate("clientes.dataNascimento"),
						null,
						null,
						rs.getInt("clientes.status"),
						null,
						null,
						null,
						null
					),
					rs.getInt("pedidos.status"),
					null, //endereco
					rs.getDouble("pedidos.valorFrete"),
					null, //cupomDesconto
					null, //cartoesCredito
					null, //cuponsTroca
					null, //carrinho
					rs.getDouble("pedidos.valorTotal"),
					rs.getInt("pedidos.prazo"),
					rs.getString("pedidos.tipoServico")
					);
				pedido.setDataAlteracao(rs.getDate("pedidos.dataAlteracao"));

				list.add(pedido);
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

	public ArrayList selectPedidosPorCliente(long id, Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragem filtro = new CriaFiltragem();
			String where = filtro.processa(campos);
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from pedidos inner join carrinhos on carrinhos.id = pedidos.idCarrinho inner join clientes on clientes.id = pedidos.idUsuario where pedidos.idUsuario = ? "+ where +" order by pedidos.id desc;");
			
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Pedido> list = new ArrayList();
			
			while (rs.next()) {
				Pedido pedido = new Pedido(
					rs.getLong("pedidos.id"),
					rs.getDate("pedidos.dataCadastro"),
					new Cliente(
						rs.getLong("clientes.id"),
						rs.getDate("clientes.dataCadastro"),
						null,
						rs.getString("clientes.nome"),
						new Genero(rs.getInt("clientes.genero"), null, null),
						rs.getDate("clientes.dataNascimento"),
						null,
						null,
						rs.getInt("clientes.status"),
						null,
						null,
						null,
						null
					),
					rs.getInt("pedidos.status"),
					null, //endereco
					rs.getDouble("pedidos.valorFrete"),
					null, //cupomDesconto
					null, //cartoesCredito
					null, //cuponsTroca
					null, //carrinho
					rs.getDouble("pedidos.valorTotal"),
					rs.getInt("pedidos.prazo"),
					rs.getString("pedidos.tipoServico")
					);
				pedido.setDataAlteracao(rs.getDate("pedidos.dataAlteracao"));

				list.add(pedido);
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

	public Pedido selectSingle(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append(
				"select * from pedidos " +
				"inner join clientes on clientes.id = pedidos.idUsuario " +
				"inner join enderecos on enderecos.id = pedidos.idEndereco " +
				"inner join bairros on bairros.id = enderecos.idBairro " +
				"inner join cidades on cidades.id = bairros.idCidade " +
				"inner join estados on estados.id = cidades.idEstado " +
				"inner join paises on paises.id = estados.idPais " +
				"inner join tipos_enderecos on tipos_enderecos.id = enderecos.idTipoEndereco " +
				"inner join tipos_logradouros on tipos_logradouros.id = enderecos.idTipoLogradouro " +
				"inner join tipos_residencias on tipos_residencias.id = enderecos.idTipoResidencia "+
				"inner join funcoes_enderecos on funcoes_enderecos.id = enderecos.idFuncaoEndereco " +
				"left join cupons_desconto on cupons_desconto.id = pedidos.idCupomDesconto " +
				"where pedidos.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Cliente cliente = new Cliente(
					rs.getLong("clientes.id"),
					rs.getDate("clientes.dataCadastro"),
					null
				);
				
				TipoResidencia tipoResidencia = new TipoResidencia(rs.getLong("enderecos.idTipoResidencia"), rs.getDate("tipos_residencias.dataCadastro"), rs.getString("tipos_residencias.nome"), rs.getString("tipos_residencias.descricao"));
				FuncaoEndereco funcaoEndereco = new FuncaoEndereco(rs.getLong("enderecos.idFuncaoEndereco"), rs.getDate("funcoes_enderecos.dataCadastro"), rs.getString("funcoes_enderecos.nome"), rs.getString("funcoes_enderecos.descricao"));
				TipoLogradouro tipoLogradouro = new TipoLogradouro(rs.getLong("enderecos.idTipoLogradouro"), rs.getDate("tipos_logradouros.dataCadastro"), rs.getString("tipos_logradouros.nome"), rs.getString("tipos_logradouros.descricao"));
				Endereco endereco = new Endereco(
					rs.getLong("enderecos.id"), rs.getDate("enderecos.dataCadastro"),
					rs.getString("enderecos.logradouro"),
					rs.getString("enderecos.numero"),
					rs.getString("enderecos.cep"),
					rs.getString("enderecos.complemento"),
					new Bairro(
						rs.getLong("bairros.id"),
						rs.getDate("bairros.dataCadastro"),
						rs.getString("bairros.descricao"),
						new Cidade(
							rs.getLong("cidades.id"),
							rs.getDate("cidades.dataCadastro"),
							rs.getString("cidades.descricao"),
							new Estado(
								rs.getLong("estados.id"),
								rs.getDate("estados.dataCadastro"),
								rs.getString("estados.descricao"),
								new Pais(
									rs.getLong("paises.id"),
									rs.getDate("paises.dataCadastro"),
									rs.getString("paises.descricao")
								)
							)
						)
					),
					new TipoEndereco(
						rs.getLong("tipos_enderecos.id"),
						rs.getDate("tipos_enderecos.dataCadastro"),
						rs.getString("tipos_enderecos.nome"),
						rs.getString("tipos_enderecos.descricao")
					),
					rs.getString("enderecos.nome"),
					tipoResidencia,
					funcaoEndereco,
					tipoLogradouro,
					rs.getString("enderecos.observacoes"));
				CupomDesconto cupomDesconto = null;
				CartaoCredito[] cartoesCredito = null;
				CupomTroca[] cuponsTroca = null;
				
				CarrinhoDAO crdao = new CarrinhoDAO();
				crdao.getCarrinhoPorId(rs.getLong("pedidos.idCarrinho"), true);
				Carrinho carrinho = crdao.selectCarrinhoVal;
				Pedido pedido = new Pedido(
					rs.getLong("pedidos.id"),
					rs.getDate("pedidos.dataCadastro"),
					cliente, //cliente
					rs.getInt("pedidos.status"),
					endereco, //endereco
					rs.getDouble("pedidos.valorFrete"),
					cupomDesconto, //cupomdesconto
					cartoesCredito, //cartoescredito
					cuponsTroca, //cuponstroca
					carrinho, //carrinho
					rs.getDouble("pedidos.valorTotal"),
					rs.getInt("pedidos.prazo"),
					rs.getString("pedidos.tipoServico"));

				pedido.setDataAlteracao(rs.getDate("pedidos.dataAlteracao"));

				this.selectSingleVal = pedido;

				return this.selectSingleVal;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insert(EntidadeDominio entidade) {

	}

	public void update(EntidadeDominio entidade) {

	}

	public ArrayList updateStatus(Pedido pedido) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();

			StringBuilder sql = new StringBuilder();
			sql.append("select status, idCarrinho from pedidos where pedidos.id = ? and pedidos.status <> 4 and pedidos.status <> 7;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, pedido.getId());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				connection.setAutoCommit(false);
			
				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE pedidos SET status = ?, dataAlteracao = ? WHERE pedidos.id = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				Date agora = new Date();

				pst.setInt(1, rs.getInt("pedidos.status") + 1);
				pst.setDate(2, new java.sql.Date(agora.getTime()));
				pst.setLong(3, pedido.getId());
				
				pst.executeUpdate();

				pedido.setStatus(rs.getInt("pedidos.status") + 1);

				ArrayList<ItemCarrinho> arr = new ArrayList();

				if (rs.getInt("pedidos.status") + 1 == 2) {
					//faz a baixa no estoque de cada livro comprado
					StringBuilder sql3 = new StringBuilder();
					sql3.append("select * from carrinhos_produtos inner join carrinhos on carrinhos_produtos.idCarrinho = carrinhos.id where carrinhos.id = ?");

					pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

					pst.setLong(1, rs.getLong("pedidos.idCarrinho"));

					ResultSet rs2 = pst.executeQuery();
					
					LivroDAO livrodao = new LivroDAO();
					CarrinhoDAO crdao = new CarrinhoDAO();

					while (rs2.next()) {
						ItemCarrinho itemCarrinho = new ItemCarrinho(
							rs2.getLong("carrinhos_produtos.idCarrinhoProduto"),
							null,
							new Livro(rs2.getLong("carrinhos_produtos.idProduto"), null),
							rs2.getInt("carrinhos_produtos.quantidade"),
							null
						);

						arr.add(itemCarrinho);

						livrodao.baixaEstoque(itemCarrinho);
						crdao.removeBloqueioCarrinhoInteiro(rs.getLong("pedidos.idCarrinho"));
					}
				}

				connection.commit();

				return arr;
			}

			return null;
						
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(connection != null) connection.rollback();
				return null;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}

		}
	}

	public void reprovaPedido(Pedido pedido) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();

			connection.setAutoCommit(false);
		
			StringBuilder sql2 = new StringBuilder();
			sql2.append("UPDATE pedidos SET status = 8 WHERE pedidos.id = ?;");
			
			pst = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, pedido.getId());
			
			pst.executeUpdate();

			pedido.setStatus(8);

			StringBuilder sql3 = new StringBuilder();

			sql3.append("SELECT carrinhos.id FROM carrinhos INNER JOIN pedidos ON carrinhos.id = pedidos.idCarrinho WHERE pedidos.id = ?;");
			pst = connection.prepareStatement(sql3.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setLong(1, pedido.getId());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				CarrinhoDAO crdao = new CarrinhoDAO();
				crdao.removeBloqueioCarrinhoInteiro(rs.getInt(1));
			}

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

	public ArrayList processaPedido(Pedido pedido) {
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			conn = Conexao.getConnectionMySQL();
			conn.setAutoCommit(false);
			StringBuilder sql3 = new StringBuilder();

			//checar se a conta nao utiliza apenas cupons

			sql3.append("SELECT * FROM pedidos_cartoes " +
						"INNER JOIN cartoes_credito ON cartoes_credito.id = pedidos_cartoes.idCartao " +
						"LEFT JOIN cartoes_aprovados b ON b.numero = cartoes_credito.numero and b.limiteDisponivel >= pedidos_cartoes.valor and b.dataExpiracao >= CURDATE()" +
						"WHERE pedidos_cartoes.idPedido = ?;");
			
			pst = conn.prepareStatement(sql3.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setLong(1, pedido.getId());
			ResultSet rs = pst.executeQuery();

			boolean pedidoAprovado = false;

			while (rs.next()) {
				if (
				    rs.getString("b.nome") == null
			     || rs.getString("b.numero") == null
				 ||	rs.getString("b.dataExpiracao") == null
				 ||	rs.getString("b.cvv") == null
				 ||	rs.getLong("b.idBandeira") <= 0

				 || !rs.getString("b.nome").equals(rs.getString("cartoes_credito.nome"))
				 || !rs.getString("b.numero").equals(rs.getString("cartoes_credito.numero"))
				 || !rs.getString("b.dataExpiracao").equals(rs.getString("cartoes_credito.dataExpiracao"))
				 || !rs.getString("b.cvv").equals(rs.getString("cartoes_credito.cvv"))
				 || rs.getLong("b.idBandeira") != rs.getLong("cartoes_credito.idBandeira")) {
					pedidoAprovado = false;
					break;
				}
				//decrementa o limite disponivel de cada cartao
				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE cartoes_aprovados SET limiteDisponivel = ? WHERE cartoes_aprovados.id = ?;");
				
				pst = conn.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDouble(1, Math.floor(((rs.getDouble("b.limiteDisponivel") - rs.getDouble("pedidos_cartoes.valor")) * 100)) / 100 );
				pst.setLong(2, rs.getLong("b.id"));
				
				int sucesso = pst.executeUpdate();

				if (sucesso > 0) {
					pedidoAprovado = true;
				} else {
					pedidoAprovado = false;
					break;
				}
			}
			
			ArrayList<ItemCarrinho> arr = new ArrayList();

			if (pedidoAprovado) {
				arr = updateStatus(pedido);

			} else {
				conn.rollback();		
				reprovaPedido(pedido);
			}

			conn.commit();
			
			return arr;
		} catch (Exception e) {
			e.printStackTrace();

			try {
				if(conn != null) conn.rollback();
				return null;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}

		}
	}

	public void delete(long id) {

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

	private void salvaEndereco(Endereco endereco, Cliente cliente) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO enderecos(dataCadastro, nome, logradouro, numero, complemento, cep, idCliente, idTipoEndereco, idBairro, idTipoResidencia, idFuncaoEndereco, idTipoLogradouro, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

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
			
		ResultSet rs = pst.getGeneratedKeys();

		if(rs.next()) {
			endereco.setId(rs.getInt(1));
		}
	}

	public boolean efetuaCompra(Pedido pedido, CartaoCredito[] cartoes, CupomTroca[] cuponsTroca) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			LivroDAO livrodao = new LivroDAO();
			CarrinhoDAO crdao = new CarrinhoDAO();

			boolean estoqueTudoCerto = true;

			//verifica se os itens do carrinho nao excedem o total nos estoques
			for (ItemCarrinho itemCarrinho : pedido.getCarrinho().getItensCarrinho()) {
				int estoqueLivro = livrodao.contaEstoque(itemCarrinho.getLivro(), pedido.getCarrinho().getId());

				if (estoqueLivro < itemCarrinho.getQuantidade()) {
					estoqueTudoCerto = false;
					crdao.alteraQteItemCarrinho(itemCarrinho.getId(), estoqueLivro);
				}
			}

			if (!estoqueTudoCerto) {
				connection.close();

				return false;
			}

			if (pedido.getEndereco().getNovo()) {
				salvaEndereco(pedido.getEndereco(), pedido.getCliente());
			}
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO pedidos(dataCadastro, idUsuario, status, idEndereco, valorFrete, idCupomDesconto, idCarrinho, valorTotal, valorDescontos, prazo, tipoServico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date();

			pst.setDate(1, new java.sql.Date(agora.getTime()));
			pst.setLong(2, pedido.getCliente().getId());
			pst.setInt(3, 1);
			pst.setLong(4, pedido.getEndereco().getId());
			pst.setDouble(5, pedido.getValorFrete());
			
			if (pedido.getCupomDesconto() == null) {
				pst.setNull(6, Types.INTEGER);
				pst.setNull(9, Types.INTEGER);
			} else {
				CupomDescontoDAO cddao = new CupomDescontoDAO();
				CupomDesconto cupomDesconto = cddao.encontraCupomDesconto(pedido.getCupomDesconto().getNome());
				if (cupomDesconto != null) {
					pst.setLong(6, cupomDesconto.getId());
					pst.setDouble(9, cupomDesconto.getValor());
				} else {
					pst.setNull(6, Types.INTEGER);
					pst.setNull(9, Types.INTEGER);
				}
			}

			pst.setLong(7, pedido.getCarrinho().getId());
			pst.setDouble(8, pedido.getValorTotal());
			pst.setInt(10, pedido.getPrazo());
			pst.setString(11, pedido.getTipoServico());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();

			int idPedido = 0;

			if (rs.next()) {
				idPedido = rs.getInt(1);

				pedido.setId(idPedido);			

				if (pedido.getCuponsTroca() != null && pedido.getCuponsTroca().length > 0) {
					usaCuponsTroca(pedido);
				}

				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE carrinhos SET status = 2 WHERE id = ?;");

				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, pedido.getCarrinho().getId());

				pst.executeUpdate();

				//salva os cartoes novos no banco. cartoes que nao foram marcados pra ser associados ao cliente ficam com seu idUsuario = null
				salvaCartoesNovos(pedido);
				//salva a relacao cartao x pedido para cada cartao usado na compra
				salvaRegistroUsoCartoes(pedido);
				
				salvaPrecoItensCarrinhoMomentoCompra(pedido.getCarrinho().getId());

				//cria um cupom de troca caso o valor dos cupons de troca + desconto ultrapasse o valor do pedido
				double totalCartoes = 0;
				double totalCuponsTroca = 0;
				double totalCupomDesconto = 0;

				for(int i = 0; i < pedido.getCartoesCredito().length; i++) {
		            totalCartoes += pedido.getCartoesCredito()[i].getValorPago();
		        }

		        if (pedido.getCuponsTroca() != null) {
			        for(int i = 0; i < pedido.getCuponsTroca().length; i++) {
			            totalCuponsTroca += pedido.getCuponsTroca()[i].getValor();
			        }
		        }

		        if (pedido.getCupomDesconto() != null) {
		        	totalCupomDesconto = pedido.getCupomDesconto().getValor();
		        }

		        connection.commit();
		        connection.close();

				double excesso = Math.floor((pedido.getValorTotal() + pedido.getValorFrete() - totalCartoes - totalCuponsTroca - totalCupomDesconto) * 100) / 100;

				if (excesso < 0) {
					TrocaDAO ctdao = new TrocaDAO();
					ctdao.geraCupomTrocaExcesso(pedido.getCliente().getId(), excesso * (-1));
				}				
			}

			return true;		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(connection != null) connection.rollback();

				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}			
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void salvaRegistroUsoCartoes(Pedido pedido) throws Exception {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO pedidos_cartoes (idPedido, idCartao, valor) VALUES (?, ?, ?);");
		
		for (CartaoCredito cartaoCredito : pedido.getCartoesCredito()) {
			if (cartaoCredito.getValorPago() > 0) {		

				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, pedido.getId());
				pst.setLong(2, cartaoCredito.getId());
				pst.setDouble(3, cartaoCredito.getValorPago());

				pst.executeUpdate();
			}
		}
	}

	private void salvaCartoesNovos(Pedido pedido) throws Exception {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cartoes_credito(cvv, dataExpiracao, nome, idUsuario, dataCadastro, numero, idBandeira) VALUES (?, ?, ?, ?, ?, ?, ?);");
		
		for (CartaoCredito cartaoCredito : pedido.getCartoesCredito()) {

			if (cartaoCredito.getJaExistente() != true) {

				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, cartaoCredito.getCvv());
				pst.setDate(2, new java.sql.Date(cartaoCredito.getDataExpiracao().getTime()));
				pst.setString(3, cartaoCredito.getNome());

				if (cartaoCredito.getRegistrarCartao()) {
					pst.setLong(4, pedido.getCliente().getId());
				} else {
					pst.setNull(4, Types.INTEGER);
				}

				pst.setDate(5, new java.sql.Date(new Date().getTime()));
				pst.setString(6, cartaoCredito.getNumero());
				pst.setLong(7, cartaoCredito.getBandeira().getId());

				pst.executeUpdate();

				ResultSet rs = pst.getGeneratedKeys();

				int idCartao = 0;

				if (rs.next()) {
					idCartao = rs.getInt(1);
					cartaoCredito.setId(idCartao);
				}
			}
		}
		
	}

	public DadosCalculoFrete getDadosCalculoFrete(long idCarrinho) {
		PreparedStatement pst = null;
		DadosCalculoFrete dadosCalculoFrete = null;
		try {
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select valor from configuracoes where id = 5;");			
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {				
				pst = connection.prepareStatement("select livros.largura, livros.profundidade, livros.peso, livros.altura, carrinhos_produtos.quantidade "+
					"from carrinhos_produtos " +
					"inner join livros on livros.id = carrinhos_produtos.idProduto " +
					"where carrinhos_produtos.idCarrinho = ?");

				pst.setLong(1, idCarrinho);

				ResultSet rs2 = pst.executeQuery();

				double totalAlturas = 0;
				double totalPesos = 0;
				double maiorLargura = 0;
				double maiorProfundidade = 0;

				while (rs2.next()) {
					totalAlturas += rs2.getDouble("livros.altura") * rs2.getInt("carrinhos_produtos.quantidade");
					totalPesos += rs2.getDouble("livros.peso") * rs2.getInt("carrinhos_produtos.quantidade");
					maiorLargura = Math.max(maiorLargura, rs2.getDouble("livros.largura"));
					maiorProfundidade = Math.max(maiorProfundidade, rs2.getDouble("livros.profundidade"));
				}

				dadosCalculoFrete = new DadosCalculoFrete(
					Math.max(1, totalAlturas),
					totalPesos,
					Math.max(10, maiorLargura),
					Math.max(15, maiorProfundidade),
					rs.getString("configuracoes.valor"),
					""
				);
			}
		
			pst.close();
			connection.close();

			this.selectDadosCalculoFreteSingle = dadosCalculoFrete;	
			return dadosCalculoFrete;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ItemGrafico> gerarGrafico(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();

			String nomeTipo = "livros.titulo";
			String joinCat = "inner join livros on livros.id = livros_estoque.livroId";

			if (campos[2].getValor().equals("categoria")) {
				nomeTipo = "categorias.nome";
				joinCat = "inner join livros_categorias on livros_categorias.idLivro = livros_estoque.livroId inner join categorias on categorias.id = livros_categorias.idCategoria";
			}
			pst = connection.prepareStatement("SELECT " + nomeTipo + ", livros_estoque.livroId, livros_estoque.dataCadastro, sum(livros_estoque.quantidade) as total FROM livros_estoque " +
				joinCat + 
				" WHERE livros_estoque.tipoMovimentacao = 2 and livros_estoque.dataCadastro >= ? and livros_estoque.dataCadastro <= ? group by livros_estoque.livroId, livros_estoque.dataCadastro order by livros_estoque.dataCadastro;");


			Date dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(campos[0].getValor());
			Date dataFim = new SimpleDateFormat("yyyy-MM-dd").parse(campos[1].getValor());

			pst.setDate(1, new java.sql.Date(dataInicio.getTime()));
			pst.setDate(2, new java.sql.Date(dataFim.getTime()));
			
			ResultSet rs = pst.executeQuery();
			
			ArrayList<ItemGrafico> list = new ArrayList();
			
			while (rs.next()) {
				ItemGrafico ig = new ItemGrafico(
					rs.getInt("total"),
					rs.getDate("livros_estoque.dataCadastro"),
					campos[2].getValor().equals("categoria") ? 2 : 1,
					rs.getString(nomeTipo)
				);

				list.add(ig);
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

	public void salvaPrecoItensCarrinhoMomentoCompra(long idCarrinho) {
		PreparedStatement pst = null;
		try {
			Connection con = Conexao.getConnectionMySQL();
			pst = con.prepareStatement(
		    "select carrinhos_produtos.idCarrinhoProduto, livros.preco from carrinhos_produtos inner join livros on livros.id = carrinhos_produtos.idProduto where idCarrinho = ?;");
			
			pst.setLong(1, idCarrinho);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<SolicitacaoTroca> list = new ArrayList();
			
			while (rs.next()) {
				pst = connection.prepareStatement("update carrinhos_produtos set precoMomentoCompra = ? where carrinhos_produtos.idCarrinhoProduto = ?");

				pst.setDouble(1, rs.getDouble("livros.preco"));
				pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinhoProduto"));
				
				pst.executeUpdate();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void usaCuponsTroca(Pedido pedido) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cupons_troca SET idPedido = ?, status = 0 WHERE id = ?;");
		
		for (CupomTroca cupomTroca : pedido.getCuponsTroca()) {
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, pedido.getId());
			pst.setLong(2, cupomTroca.getId());

			pst.executeUpdate();
		}
	}
}
