package dao;

import utils.Conexao;
import utils.DadosCalculoFrete;
import strategies.CriaFiltragem;
import strategies.CriaFiltragemUsuario;
import strategies.CriaPaginacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
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
import model.ItemCarrinho;
import model.Bairro;
import model.Carrinho;
import model.CartaoCredito;
import model.Cidade;
import model.Livro;
import model.Pais;
import model.CupomTroca;
import model.Endereco;
import model.Cliente;
import model.CupomDesconto;
import utils.Campo;

public class PedidoDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public Pedido selectSingleVal;
	public Carrinho selectCarrinhoVal;
	public DadosCalculoFrete selectDadosCalculoFreteSingle;

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragem filtro = new CriaFiltragem();
			CriaPaginacao paginacao = new CriaPaginacao();
			String where = filtro.processa(campos);
			String paginacaoStr = "";//paginacao.processa(campos);
			connection = Conexao.getConnectionMySQL();
			//pst = connection.prepareStatement("select * from pedidos inner join carrinhos on carrinhos.id = pedidos.idCarrinho inner join usuarios on usuarios.id = usuarioId " + where + paginacaoStr + ";");
			pst = connection.prepareStatement("select * from pedidos inner join carrinhos on carrinhos.id = pedidos.idCarrinho inner join clientes on clientes.id = pedidos.idUsuario " + where + " order by pedidos.id desc;");
			
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Pedido> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Pedido pedido = new Pedido(
					rs.getLong("pedidos.id"),
					rs.getDate("pedidos.dataCadastro"),
					new Cliente(
						rs.getLong("clientes.id"),
						rs.getDate("clientes.dataCadastro"),
						null,
						rs.getString("clientes.nome"),
						rs.getInt("clientes.sexo"),
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

				//public Cliente(long id, Date dataCadastro, Documento[] documentos, String nome, int sexo, Date dataNascimento, TipoCliente tipoCliente, Endereco[] enderecos, int status) {


				list.add(pedido);
			}

			//pst = connection.prepareStatement("select count(pedidos.id) as resultadosTotal from pedidos " + where + ";");
			pst = connection.prepareStatement("select count(pedidos.id) as resultadosTotal from pedidos;");
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
			//if(pst != null) pst.close();
			//if(connection != null) connection.close();
			return null;
		}
	}

	public ArrayList selectPedidosPorCliente(long id, Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragemUsuario filtro = new CriaFiltragemUsuario();
			CriaPaginacao paginacao = new CriaPaginacao();
			String where = filtro.processa(campos);
			String paginacaoStr = "";//paginacao.processa(campos);
			connection = Conexao.getConnectionMySQL();
			//pst = connection.prepareStatement("select * from pedidos inner join carrinhos on carrinhos.id = pedidos.idCarrinho inner join usuarios on usuarios.id = usuarioId " + where + paginacaoStr + ";");
			pst = connection.prepareStatement("select * from pedidos inner join carrinhos on carrinhos.id = pedidos.idCarrinho inner join clientes on clientes.id = pedidos.idUsuario where pedidos.idUsuario = ? "+ where +" order by pedidos.id desc;");
			
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Pedido> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Pedido pedido = new Pedido(
					rs.getLong("pedidos.id"),
					rs.getDate("pedidos.dataCadastro"),
					new Cliente(
						rs.getLong("clientes.id"),
						rs.getDate("clientes.dataCadastro"),
						null,
						rs.getString("clientes.nome"),
						rs.getInt("clientes.sexo"),
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

				//public Cliente(long id, Date dataCadastro, Documento[] documentos, String nome, int sexo, Date dataNascimento, TipoCliente tipoCliente, Endereco[] enderecos, int status) {


				list.add(pedido);
			}

			//pst = connection.prepareStatement("select count(pedidos.id) as resultadosTotal from pedidos " + where + ";");
			pst = connection.prepareStatement("select count(pedidos.id) as resultadosTotal from pedidos;");
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
			//if(pst != null) pst.close();
			//if(connection != null) connection.close();
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
				CartaoCredito[] cartoesCredito = null; //fazer
				CupomTroca[] cuponsTroca = null; //fazer
				getCarrinhoPorId(rs.getLong("pedidos.idCarrinho"), true);
				Carrinho carrinho = this.selectCarrinhoVal;
				this.selectSingleVal = new Pedido(
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

				//Pedido(long id, Date dataCadastro, Cliente cliente, int status,
				//		 Endereco endereco, double valorFrete, CupomDesconto cupomDesconto,
				//		 CartaoCredito[] cartoesCredito, CupomTroca[] cuponsTroca, Carrinho carrinho, double valorTotal)
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

	public Carrinho selectCarrinho(long idCliente) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from carrinhos where carrinhos.idUsuario = ? and carrinhos.status <= 1;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idCliente);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				int idCarrinho = rs.getInt(1);

				ArrayList<ItemCarrinho> itensCarrinho = new ArrayList();

				StringBuilder sql2 = new StringBuilder();
				sql2.append("select carrinhos_produtos.idCarrinho, carrinhos_produtos.idCarrinhoProduto, carrinhos_produtos.quantidade, livros.id, livros.titulo, livros.preco, livros.capa from carrinhos_produtos inner join livros on carrinhos_produtos.idProduto = livros.id where carrinhos_produtos.idCarrinho = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, idCarrinho);

				ResultSet rs2 = pst.executeQuery();

				LivroDAO livrodao = new LivroDAO();

				while (rs2.next()) {
					Livro livro = new Livro(
						rs2.getLong("livros.id"),
						null,
						rs2.getString("livros.titulo"),
						null,
						null,
						null,
						"",
						"",
						0,
						"",
						0,
						0,
						0,
						rs2.getDouble("livros.preco"),
						"",
						1,
						rs2.getString("livros.capa"),
						null,
						""
					);
					livro.setEstoque(livrodao.contaEstoque(livro, rs2.getLong("carrinhos_produtos.idCarrinho")));
					ItemCarrinho ic = new ItemCarrinho(
						rs2.getLong("carrinhos_produtos.idCarrinhoProduto"),
						null,
						livro,
						rs2.getInt("carrinhos_produtos.quantidade"),
						null
					);

					itensCarrinho.add(ic);
				}

				Carrinho carrinho = new Carrinho((long)idCarrinho, rs.getDate(2), itensCarrinho, rs.getInt(4), null);
				this.selectCarrinhoVal = carrinho;
				return this.selectCarrinhoVal;
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

	public Carrinho getCarrinhoPorId(long id, boolean posCompra) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from carrinhos where carrinhos.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				ArrayList<ItemCarrinho> itensCarrinho = new ArrayList();

				StringBuilder sql2 = new StringBuilder();
				sql2.append("select carrinhos_produtos.precoMomentoCompra, " +
					"carrinhos_produtos.idCarrinhoProduto, carrinhos_produtos.quantidadeItensTrocados, "+
					"carrinhos_produtos.quantidade, livros.id, livros.titulo, livros.preco, livros.capa "+
					"from carrinhos_produtos inner join livros on carrinhos_produtos.idProduto = livros.id where carrinhos_produtos.idCarrinho = ?;");
				//sql2.append("select * from carrinhos_produtos inner join livros on carrinhos_produtos.idProduto = livros.id where carrinhos_produtos.idCarrinho = ?;");

				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, id);

				ResultSet rs2 = pst.executeQuery();

				LivroDAO livrodao = new LivroDAO();

				while (rs2.next()) {
					double precoLivro;

					if (posCompra) {
						precoLivro = rs2.getDouble("carrinhos_produtos.precoMomentoCompra");
					} else {
						precoLivro = rs2.getDouble("livros.preco");
					}

					Livro livro = new Livro(
						rs2.getLong("livros.id"),
						null,
						rs2.getString("livros.titulo"),
						null,
						null,
						null,
						"",
						"",
						0,
						"",
						0,
						0,
						0,
						precoLivro,
						"",
						1,
						rs2.getString("livros.capa"),
						null,
						""
					);

					ItemCarrinho ic = new ItemCarrinho(
						rs2.getLong("carrinhos_produtos.idCarrinhoProduto"),
						null,
						livro,
						rs2.getInt("carrinhos_produtos.quantidade"),
						null
					);

					ic.setQuantidadeItensTrocados(rs2.getInt("carrinhos_produtos.quantidadeItensTrocados"));

					itensCarrinho.add(ic);
				}

				Carrinho carrinho = new Carrinho((long)id, rs.getDate(2), itensCarrinho, rs.getInt(4), null);
				this.selectCarrinhoVal = carrinho;
				return this.selectCarrinhoVal;
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

	public void updateStatus(Pedido pedido) {
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
				//rs.getInt("pedidos.status");

				connection.setAutoCommit(false);
			
				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE pedidos SET status = ? WHERE pedidos.id = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setInt(1, rs.getInt("pedidos.status") + 1);
				pst.setLong(2, pedido.getId());
				
				pst.executeUpdate();



				if (rs.getInt("pedidos.status") + 1 == 2) {
					//faz a baixa no estoque de cada livro comprado
					StringBuilder sql3 = new StringBuilder();
					sql3.append("select * from carrinhos_produtos inner join carrinhos on carrinhos_produtos.idCarrinho = carrinhos.id where carrinhos.id = ?");

					pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

					pst.setLong(1, rs.getLong("pedidos.idCarrinho"));

					ResultSet rs2 = pst.executeQuery();
					
					LivroDAO livrodao = new LivroDAO();

					while (rs2.next()) {
						ItemCarrinho itemCarrinho = new ItemCarrinho(
							rs2.getLong("carrinhos_produtos.idCarrinhoProduto"),
							null,
							new Livro(rs2.getLong("carrinhos_produtos.idProduto"), null),
							rs2.getInt("carrinhos_produtos.quantidade"),
							null
						);

						livrodao.baixaEstoque(itemCarrinho);

						removeBloqueioCarrinhoInteiro(rs.getLong("pedidos.idCarrinho"));
					}
				}

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

			StringBuilder sql3 = new StringBuilder();

			sql3.append("SELECT carrinhos.id FROM carrinhos INNER JOIN pedidos ON carrinhos.id = pedidos.idCarrinho WHERE pedidos.id = ?;");
			pst = connection.prepareStatement(sql3.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setLong(1, pedido.getId());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				removeBloqueioCarrinhoInteiro(rs.getInt(1));
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

	public void processaPedido(Pedido pedido) {
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			conn = Conexao.getConnectionMySQL();
			conn.setAutoCommit(false);
			StringBuilder sql3 = new StringBuilder();

			sql3.append("SELECT * FROM pedidos_cartoes " +
						"INNER JOIN cartoes_credito ON cartoes_credito.id = pedidos_cartoes.idCartao " +
						//"INNER JOIN cartoes_aprovados a ON a.nome = cartoes_credito.nome " +
						"LEFT JOIN cartoes_aprovados b ON b.numero = cartoes_credito.numero and b.limiteDisponivel >= pedidos_cartoes.valor and b.dataExpiracao >= CURDATE()" +
						/*"INNER JOIN cartoes_aprovados c ON c.dataExpiracao = cartoes_credito.dataExpiracao " +
						"INNER JOIN cartoes_aprovados d ON d.cvv = cartoes_credito.cvv " +
						"INNER JOIN cartoes_aprovados e ON e.idBandeira = cartoes_credito.idBandeira " +*/
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

			if (pedidoAprovado) {
				updateStatus(pedido);
			} else {
				conn.rollback();		
				reprovaPedido(pedido);
			}

			conn.commit();						
		} catch (Exception e) {
			try {
				if(conn != null) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public void adicionarCarrinho(ItemCarrinho itemCarrinho) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			//remove o bloqueio do carrinho inativado
			StringBuilder sqlDelb = new StringBuilder();
			sqlDelb.append("DELETE FROM bloqueios_produtos where idCarrinho = (select id from carrinhos WHERE status = 0 AND idUsuario = ? limit 1);");	

			pst = connection.prepareStatement(sqlDelb.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, itemCarrinho.getCliente().getId());

			pst.executeUpdate();

			//remove o carrinho inativado
			StringBuilder sqlDel = new StringBuilder();
			sqlDel.append("DELETE FROM carrinhos WHERE status = 0 AND idUsuario = ?;");	

			pst = connection.prepareStatement(sqlDel.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, itemCarrinho.getCliente().getId());

			pst.executeUpdate();			
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id FROM carrinhos WHERE carrinhos.idUsuario = ? AND carrinhos.status = 1 limit 1;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setLong(1, itemCarrinho.getCliente().getId());
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				//adiciona o produto ao carrinho existente
				adicionaProdutoAoCarrinho(itemCarrinho, rs.getInt(1));
			} else {
				//cria um carrinho novo
				StringBuilder sql2 = new StringBuilder();
				sql2.append("INSERT INTO carrinhos(dataCadastro, idUsuario, status, dataAlteracao) VALUES (?, ?, ?, ?);");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(agora.getTime()));
				pst.setLong(2, itemCarrinho.getCliente().getId());
				pst.setInt(3, 1);
				pst.setDate(4, new java.sql.Date(agora.getTime()));
				
				pst.executeUpdate();
				
				ResultSet rs2 = pst.getGeneratedKeys();
				if (rs2.next()) {
					//adiciona o produto ao carrinho novo
					adicionaProdutoAoCarrinho(itemCarrinho, rs2.getInt(1));
				}
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

	private void adicionaProdutoAoCarrinho(ItemCarrinho itemCarrinho, long id) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT idCarrinhoProduto, quantidade FROM carrinhos_produtos WHERE carrinhos_produtos.idProduto = ? AND carrinhos_produtos.idCarrinho = ? limit 1;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setLong(1, itemCarrinho.getLivro().getId());
			pst.setLong(2, id);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				//pega a quantidade de estoque do livro a ser inserido
				LivroDAO livroDAO = new LivroDAO();
				int estoque = livroDAO.contaEstoque(itemCarrinho.getLivro(), id);
				//altera a quantidade de itens no carrinho
				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE carrinhos_produtos SET quantidade = ? WHERE carrinhos_produtos.idCarrinhoProduto = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setInt(1, Math.min( rs.getInt(2) + itemCarrinho.getQuantidade(), estoque ) );
				pst.setLong(2, rs.getInt(1));
				
				pst.executeUpdate();

				//altera o bloqueio do carrinho
				alteraBloqueio(id, Math.min( rs.getInt(2) + itemCarrinho.getQuantidade(), estoque ));

			} else {
				//adiciona o produto ao carrinho existente
				StringBuilder sql2 = new StringBuilder();
				sql2.append("INSERT INTO carrinhos_produtos(idCarrinho, idProduto, quantidade, quantidadeItensTrocados) VALUES (?, ?, ?, 0);");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, id);
				pst.setLong(2, itemCarrinho.getLivro().getId());
				pst.setInt(3, itemCarrinho.getQuantidade());
				
				pst.executeUpdate();

				ResultSet rs2 =  pst.getGeneratedKeys();
				if(rs2.next()) {
					//insere o bloqueio do carrinho
					insereBloqueio(rs2.getInt(1), itemCarrinho.getQuantidade());
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void insereBloqueio(long idItemCarrinho, int quantidade) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setLong(1, idItemCarrinho);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				StringBuilder sql2 = new StringBuilder();
				sql2.append("INSERT INTO bloqueios_produtos (dataEntrada, quantidade, idLivro, idCarrinho) VALUES (?, ?, ?, ?);");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setInt(2, quantidade);
				pst.setLong(3, rs.getLong("carrinhos_produtos.idProduto"));
				pst.setLong(4, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();

				ResultSet rs2 =  pst.getGeneratedKeys();
				if(rs2.next()) {
					StringBuilder sql3 = new StringBuilder();
					sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
					
					pst = connection.prepareStatement(sql3.toString(),
							Statement.RETURN_GENERATED_KEYS);

					pst.setDate(1, new java.sql.Date(new Date().getTime()));
					pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));
					
					pst.executeUpdate();
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void alteraBloqueio(long idItemCarrinho, int quantidade) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setLong(1, idItemCarrinho);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE bloqueios_produtos SET quantidade = ? WHERE idLivro = ? AND idCarrinho = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setInt(1, quantidade);
				pst.setLong(2, rs.getLong("carrinhos_produtos.idProduto"));
				pst.setLong(3, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();

				StringBuilder sql3 = new StringBuilder();
				sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
				
				pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void removeBloqueio(long idItemCarrinho) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setLong(1, idItemCarrinho);
			
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				StringBuilder sql2 = new StringBuilder();
				sql2.append("DELETE FROM bloqueios_produtos WHERE idLivro = ? AND idCarrinho = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, rs.getLong("carrinhos_produtos.idProduto"));
				pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));

				pst.executeUpdate();

				StringBuilder sql3 = new StringBuilder();
				sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
				
				pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void removeBloqueioCarrinhoInteiro(long idCarrinho) {
		PreparedStatement pst = null;		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM bloqueios_produtos WHERE idCarrinho = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, idCarrinho);
			
			pst.executeUpdate();

			StringBuilder sql3 = new StringBuilder();
			sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
			
			pst = connection.prepareStatement(sql3.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, new java.sql.Date(new Date().getTime()));
			pst.setLong(2, idCarrinho);
			
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(long id) {

	}

	public void alteraQteItemCarrinho(long id, int quantidade) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sqlEstoque = new StringBuilder();
			sqlEstoque.append("SELECT idProduto, idCarrinho from carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sqlEstoque.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, id);
			
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				LivroDAO livrodao = new LivroDAO();
				int estoque = livrodao.contaEstoque(new Livro(rs.getLong("carrinhos_produtos.idProduto"), new Date()), rs.getLong("carrinhos_produtos.idCarrinho"));

				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE carrinhos_produtos SET quantidade = ? WHERE idCarrinhoProduto = ?");
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, Math.min(estoque, quantidade));
				pst.setLong(2, id);
				pst.executeUpdate();

				//altera o bloqueio do produto
				alteraBloqueio(id, quantidade);
				connection.commit();
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao alterar o registro!");
			e.printStackTrace();
		}
	}

	public void removerItemCarrinho(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			//remove o bloqueio do item
			removeBloqueio(id);

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM carrinhos_produtos WHERE idCarrinhoProduto = ?");
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

	public CupomTroca encontraCupomTroca(String nomeCupomTroca, long idUsuario) {	
		PreparedStatement pst = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from cupons_troca where idPedido is null and nome = ? and idUsuario = ?;");
			
			Connection con = Conexao.getConnectionMySQL();
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setString(1, nomeCupomTroca);
			pst.setLong(2, idUsuario);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				CupomTroca cupomTroca = new CupomTroca(
					rs.getLong("cupons_troca.id"),
					rs.getDate("cupons_troca.dataEntrada"),
					rs.getString("cupons_troca.nome"),
					rs.getDouble("cupons_troca.valor"),
					null
				);
				//CupomTroca(long id, Date dataCadastro, String nome, double valor, Pedido pedido)
				con.commit();
				return cupomTroca;
			} else {
				return null;
			}
		}  catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar o cupom!");
			e.printStackTrace();
			return null;
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

			boolean estoqueTudoCerto = true;

			//verifica se os itens do carrinho nao excedem o total nos estoques
			for (ItemCarrinho itemCarrinho : pedido.getCarrinho().getItensCarrinho()) {
				int estoqueLivro = livrodao.contaEstoque(itemCarrinho.getLivro(), pedido.getCarrinho().getId());

				if (estoqueLivro < itemCarrinho.getQuantidade()) {
					estoqueTudoCerto = false;
					alteraQteItemCarrinho(itemCarrinho.getId(), estoqueLivro);
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
				CupomDesconto cupomDesconto = encontraCupomDesconto(pedido.getCupomDesconto().getNome());
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
				
				salvaPrecoItensCarrinho(pedido.getCarrinho().getId());

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

				double excesso = Math.floor((pedido.getValorTotal() + pedido.getValorFrete() - totalCartoes - totalCuponsTroca - totalCupomDesconto) * 100) / 100;
				if (excesso < 0) {
					geraCupomTrocaExcesso(pedido.getCliente().getId(), excesso * (-1));
				}
				connection.commit();
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

	public void usaCuponsTroca(Pedido pedido) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cupons_troca SET idPedido = ? WHERE id = ?;");
		
		for (CupomTroca cupomTroca : pedido.getCuponsTroca()) {
			System.out.println("estou usando o cupom de troca");
			System.out.println(cupomTroca.getId());

			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, pedido.getId());
			pst.setLong(2, cupomTroca.getId());

			pst.executeUpdate();
		}
	}

	public void solicitarTroca(ItemCarrinho itemCarrinho) {
		//cria um registro na tabela solicitacoes_troca
		PreparedStatement pst = null;

		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT quantidade from carrinhos_produtos where idCarrinhoProduto = ?");
		
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setLong(1, itemCarrinho.getId());
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				int quantidadeAtual = rs.getInt(1);
				itemCarrinho.setQuantidadeItensTrocados( Math.min(quantidadeAtual, itemCarrinho.getQuantidadeItensTrocados()) );

				StringBuilder sql2 = new StringBuilder();
				sql2.append("INSERT INTO solicitacoes_troca (idItemCarrinho, quantidade, status, dataCadastro) VALUES (?, ?, 0, ?);");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, itemCarrinho.getId());
				pst.setInt(2, itemCarrinho.getQuantidadeItensTrocados() );
				pst.setDate(3, new java.sql.Date(agora.getTime()));

				pst.executeUpdate();

				StringBuilder sql3 = new StringBuilder();
				sql3.append("UPDATE carrinhos_produtos SET quantidade = ?, quantidadeItensTrocados = ? WHERE carrinhos_produtos.idCarrinhoProduto = ?;");

				pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setInt(1, quantidadeAtual - itemCarrinho.getQuantidadeItensTrocados() );
				pst.setInt(2, itemCarrinho.getQuantidadeItensTrocados() );
				pst.setLong(3, itemCarrinho.getId());

				pst.executeUpdate();


				//altera status do pedido
				StringBuilder sql4 = new StringBuilder();
				sql4.append("SELECT pedidos.id from pedidos "+
				           "INNER JOIN carrinhos on carrinhos.id = pedidos.idCarrinho " +
						   "INNER JOIN carrinhos_produtos on carrinhos_produtos.idCarrinho = carrinhos.id " +
				           "WHERE carrinhos_produtos.idCarrinhoProduto = ?;");
			
				pst = connection.prepareStatement(sql4.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, itemCarrinho.getId());

				ResultSet rs3 = pst.executeQuery();

				if (rs3.next()) {
					long idPedido = rs3.getLong(1);

					StringBuilder sql5 = new StringBuilder();
					sql5.append("UPDATE pedidos SET status = 5 WHERE id = ?;");

					pst = connection.prepareStatement(sql5.toString(),
							Statement.RETURN_GENERATED_KEYS);

					pst.setLong(1, idPedido);

					pst.executeUpdate();
				}


				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}			
		}
	}

	public void selectSolicitacoesTroca() {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragemUsuario filtro = new CriaFiltragemUsuario();
			CriaPaginacao paginacao = new CriaPaginacao();
			String where = "";//filtro.processa(campos);
			String paginacaoStr = "";//paginacao.processa(campos);
			connection = Conexao.getConnectionMySQL();
			//pst = connection.prepareStatement("select * from pedidos inner join carrinhos on carrinhos.id = pedidos.idCarrinho inner join usuarios on usuarios.id = usuarioId " + where + paginacaoStr + ";");
			pst = connection.prepareStatement(
		    "select solicitacoes_troca.id, solicitacoes_troca.dataCadastro, solicitacoes_troca.quantidade, solicitacoes_troca.status, " +
		    "       livros.id, livros.capa, livros.titulo, carrinhos_produtos.precoMomentoCompra, " +
		    "       clientes.id, clientes.nome " +
			"from solicitacoes_troca " +
			"inner join carrinhos_produtos on carrinhos_produtos.idCarrinhoProduto = solicitacoes_troca.idItemCarrinho " +
			"inner join livros on carrinhos_produtos.idProduto = livros.id "+
			"inner join carrinhos on carrinhos.id = carrinhos_produtos.idCarrinho "+
			"inner join clientes on clientes.id = carrinhos.idUsuario "+
			"order by solicitacoes_troca.id desc;");
			
			ResultSet rs = pst.executeQuery();
			
			ArrayList<SolicitacaoTroca> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				SolicitacaoTroca solicitacaoTroca = new SolicitacaoTroca(
					rs.getLong("solicitacoes_troca.id"),
					rs.getDate("solicitacoes_troca.dataCadastro"),
					new ItemCarrinho(
						(long)1,
						null,
						new Livro(
							rs.getLong("livros.id"),
							null,
							rs.getString("livros.titulo"),
							rs.getString("livros.capa"),
							rs.getDouble("carrinhos_produtos.precoMomentoCompra")
						),
						0,
						new Cliente(
							rs.getLong("clientes.id"),
							null,
							null,
							rs.getString("clientes.nome")
						)
					), //itemCarrinho
					//(long id, Date dataCadastro, Livro livro, int quantidade, Cliente cliente)
					rs.getInt("solicitacoes_troca.quantidade"),
					rs.getInt("solicitacoes_troca.status")
					//(long id, Date dataCadastro, ItemCarrinho itemCarrinho, int quantidade, int status)
					/*rs.getLong("pedidos.id"),
					rs.getDate("pedidos.dataCadastro"),
					new Cliente(
						rs.getLong("clientes.id"),
						rs.getDate("clientes.dataCadastro"),
						null,
						rs.getString("clientes.nome"),
						rs.getInt("clientes.sexo"),
						rs.getDate("clientes.dataNascimento"),
						null,
						null,
						rs.getInt("clientes.status"),
						null
					),
					rs.getInt("pedidos.status"),
					null, //endereco
					rs.getDouble("pedidos.valorFrete"),
					null, //cupomDesconto
					null, //cartoesCredito
					null, //cuponsTroca
					null, //carrinho
					rs.getDouble("pedidos.valorTotal")*/
				);

				//public Cliente(long id, Date dataCadastro, Documento[] documentos, String nome, int sexo, Date dataNascimento, TipoCliente tipoCliente, Endereco[] enderecos, int status) {

				list.add(solicitacaoTroca);
			}

			//pst = connection.prepareStatement("select count(pedidos.id) as resultadosTotal from pedidos " + where + ";");			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			//return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
			//if(pst != null) pst.close();
			//if(connection != null) connection.close();
			//return null;
		}		
	}

	public void decidirPedidoTroca(long id, int aprovacao) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE solicitacoes_troca SET status = ? WHERE id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			if (aprovacao == 1) { //troca aprovada
				pst.setInt(1, 1);
			} else { //troca recusada
				pst.setInt(1, 4);
			}

			pst.setLong(2, id);

			pst.executeUpdate();

			if (aprovacao != 1) {
				devolveItensAoItemCarrinho(id);
			}

			//altera status pedido
			StringBuilder sql4 = new StringBuilder();
			sql4.append("SELECT pedidos.id, pedidos.idUsuario from pedidos "+
			           "INNER JOIN carrinhos on carrinhos.id = pedidos.idCarrinho " +
					   "INNER JOIN carrinhos_produtos on carrinhos_produtos.idCarrinho = carrinhos.id " +
					   "INNER JOIN solicitacoes_troca on solicitacoes_troca.idItemCarrinho = carrinhos_produtos.idCarrinhoProduto " +
			           "WHERE solicitacoes_troca.id = ?;");
		
			pst = connection.prepareStatement(sql4.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, id);

			ResultSet rs3 = pst.executeQuery();

			if (rs3.next()) {
				long idPedido = rs3.getLong("pedidos.id");

				StringBuilder sql5 = new StringBuilder();
				sql5.append("UPDATE pedidos SET status = ? WHERE id = ?;");

				pst = connection.prepareStatement(sql5.toString(),
						Statement.RETURN_GENERATED_KEYS);

				if (aprovacao == 1) { //troca aprovada
					pst.setInt(1, 6);
				} else { //troca recusada
					pst.setInt(1, 4);
				}

				pst.setLong(2, idPedido);

				pst.executeUpdate();

				//gera a notificacao
				StringBuilder sql6 = new StringBuilder();
				sql6.append("INSERT INTO notificacoes (idCliente, descricao, cor, dataCadastro) VALUES (?, ?, ?, ?)");

				pst = connection.prepareStatement(sql6.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, rs3.getLong("pedidos.idUsuario"));
				
				if (aprovacao == 1) { //troca aprovada
					pst.setString(2, "Aviso: um de seus pedidos de troca foi aceito. Confira seus pedidos!");
					pst.setString(3, "12a901");
				} else { //troca recusada
					pst.setString(2, "Aviso: um de seus pedidos de troca foi recusado.");
					pst.setString(3, "ff0931");
				}

				pst.setDate(4, new java.sql.Date(new Date().getTime()));

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
		}		
	}

	private void devolveItensAoItemCarrinho(long id) {
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement("select * from solicitacoes_troca inner join carrinhos_produtos on carrinhos_produtos.idCarrinhoProduto = solicitacoes_troca.idItemCarrinho where solicitacoes_troca.id = ?;");
			
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
						
			if (rs.next()) {
				pst = connection.prepareStatement("update carrinhos_produtos set quantidade = ?, quantidadeItensTrocados = ? where idCarrinhoProduto = ?");

				pst.setInt(1, rs.getInt("carrinhos_produtos.quantidade") + rs.getInt("solicitacoes_troca.quantidade"));
				pst.setInt(2, rs.getInt("carrinhos_produtos.quantidadeItensTrocados") - rs.getInt("solicitacoes_troca.quantidade"));
				pst.setLong(3, rs.getLong("carrinhos_produtos.idCarrinhoProduto"));
				
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void confirmarRecebimentoTroca(long id, boolean retornarEstoque) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE solicitacoes_troca SET status = ? WHERE id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			if (retornarEstoque) {
				pst.setInt(1, 3);
			} else {
				pst.setInt(1, 2);
			}
			pst.setLong(2, id);

			pst.executeUpdate();

			if (retornarEstoque) {
				retornaItensEstoque(id);
			}

			//altera status do pedido
			StringBuilder sql4 = new StringBuilder();
			sql4.append("SELECT pedidos.id from pedidos "+
			           "INNER JOIN carrinhos on carrinhos.id = pedidos.idCarrinho " +
					   "INNER JOIN carrinhos_produtos on carrinhos_produtos.idCarrinho = carrinhos.id " +
					   "INNER JOIN solicitacoes_troca on solicitacoes_troca.idItemCarrinho = carrinhos_produtos.idCarrinhoProduto " +
			           "WHERE solicitacoes_troca.idItemCarrinho = ?;");
		
			pst = connection.prepareStatement(sql4.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, id);

			ResultSet rs3 = pst.executeQuery();

			if (rs3.next()) {
				long idPedido = rs3.getLong(1);

				StringBuilder sql5 = new StringBuilder();
				sql5.append("UPDATE pedidos SET status = 7 WHERE id = ?;");

				pst = connection.prepareStatement(sql5.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, idPedido);

				pst.executeUpdate();
			}

			geraCupomTroca(id);

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
	
	public void geraCupomTroca(long id) {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement("select * from solicitacoes_troca "+
				"inner join carrinhos_produtos on carrinhos_produtos.idCarrinhoProduto = solicitacoes_troca.idItemCarrinho "+
				"inner join livros on livros.id = carrinhos_produtos.idProduto "+
				"inner join carrinhos on carrinhos.id = carrinhos_produtos.idCarrinho "+
				"inner join clientes on clientes.id = carrinhos.idUsuario "+
				"where solicitacoes_troca.id = ?;");
			
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
						
			if (rs.next()) {
				long idUsuario = 0;
				double valor = 0;
				
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO cupons_troca (dataEntrada, idUsuario, nome, valor, status) VALUES (?, ?, ?, ?, ?);");

				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				
				DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  
				
				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getLong("clientes.id"));
				pst.setString(3, rs.getString("clientes.nome").replaceAll(" ", "").toUpperCase() + dateFormat.format(new Date()) + String.valueOf(rs.getInt("solicitacoes_troca.quantidade") * rs.getDouble("carrinhos_produtos.precoMomentoCompra")).replaceAll(".", "") );
				pst.setDouble(4, rs.getInt("solicitacoes_troca.quantidade") * rs.getDouble("carrinhos_produtos.precoMomentoCompra") );
				pst.setInt(5, 1);

				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void geraCupomTrocaExcesso(long idCliente, double valor) {
		PreparedStatement pst = null;

		try {				
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cupons_troca (dataEntrada, idUsuario, nome, valor, status) VALUES (?, ?, ?, ?, ?);");

			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  
			
			pst.setDate(1, new java.sql.Date(new Date().getTime()));
			pst.setLong(2, idCliente);
			pst.setString(3, "CUPOMTROCA" + idCliente + dateFormat.format(new Date()) + String.valueOf(valor).replaceAll(".", "") );
			pst.setDouble(4, valor );
			pst.setInt(5, 1);

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void retornaItensEstoque(long idSolicitacaoTroca) {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement("select * from solicitacoes_troca "+
				"inner join carrinhos_produtos on carrinhos_produtos.idCarrinhoProduto = solicitacoes_troca.idItemCarrinho "+
				"inner join livros on livros.id = carrinhos_produtos.idProduto "+
				"inner join carrinhos on carrinhos.id = carrinhos_produtos.idCarrinho "+
				"inner join clientes on clientes.id = carrinhos.idUsuario "+
				"where solicitacoes_troca.id = ?;");
			
			pst.setLong(1, idSolicitacaoTroca);
			ResultSet rs = pst.executeQuery();
						
			if (rs.next()) {
				long idUsuario = 0;
				double valor = 0;
				
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO livros_estoque (dataCadastro, quantidade, custo, dataEntrada, fornecedorId, livroId, tipoMovimentacao, idCliente) VALUES (?, ?, 0, ?, 0, ?, 3, ?);");

				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				
				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getInt("solicitacoes_troca.quantidade"));
				pst.setDate(3, new java.sql.Date(new Date().getTime()));
				pst.setLong(4, rs.getLong("livros.id"));
				pst.setLong(5, rs.getLong("clientes.id"));

				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CupomTroca[] encontraCuponsTroca(CupomTroca[] cuponsTroca) {
		PreparedStatement pst = null;
		try {
			if (cuponsTroca == null) {
				return null;
			}
			CupomTroca[] ct = new CupomTroca[cuponsTroca.length];
			Connection con = Conexao.getConnectionMySQL();

			for (int i = 0; i < ct.length; i++) {
				StringBuilder sql = new StringBuilder();
				sql.append("select * from cupons_troca where idUsuario = ? and nome = ? and idPedido is null");

				pst = con.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
			
				pst.setLong(1, cuponsTroca[i].getPedido().getCliente().getId());
				pst.setString(2, cuponsTroca[i].getNome());

				ResultSet rs = pst.executeQuery();
							
				if (rs.next()) {
					ct[i] = cuponsTroca[i];
					ct[i].setValor(rs.getDouble("cupons_troca.valor"));
					ct[i].setId(rs.getLong("cupons_troca.id"));
				}
			}

			return ct;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void salvaPrecoItensCarrinho(long idCarrinho) {
		PreparedStatement pst = null;
		try {
			Connection con = Conexao.getConnectionMySQL();
			pst = con.prepareStatement(
		    "select carrinhos_produtos.idCarrinhoProduto, livros.preco from carrinhos_produtos inner join livros on livros.id = carrinhos_produtos.idProduto where idCarrinho = ?;");
			
			pst.setLong(1, idCarrinho);

			ResultSet rs = pst.executeQuery();
			
			ArrayList<SolicitacaoTroca> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
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

	public ArrayList listagemCuponsTroca(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			CriaFiltragemUsuario filtro = new CriaFiltragemUsuario();
			CriaPaginacao paginacao = new CriaPaginacao();
			String where = "";
			String paginacaoStr = "";
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from cupons_troca where idUsuario = ?;");

			pst.setLong(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			ArrayList<CupomTroca> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Pedido pedido = new Pedido(rs.getLong("cupons_troca.idPedido"), null);

				CupomTroca cupomTroca = new CupomTroca(
					rs.getLong("cupons_troca.id"),
					rs.getDate("cupons_troca.dataEntrada"),
					rs.getString("cupons_troca.nome"),
					rs.getDouble("cupons_troca.valor"),
					pedido
				);

				list.add(cupomTroca);
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
					totalAlturas,
					totalPesos,
					maiorLargura,
					maiorProfundidade,
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
}
