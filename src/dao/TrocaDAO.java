package dao;

import utils.Conexao;
import strategies.CriaFiltragem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.EntidadeDominio;
import model.ItemCarrinho;
import model.Livro;
import model.LivroEstoque;
import model.Pedido;
import model.SolicitacaoTroca;
import model.Cliente;
import model.CupomDesconto;
import model.CupomTroca;
import utils.Campo;

public class TrocaDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;

	public CupomTroca encontraCupomTroca(String nomeCupomTroca, long idUsuario) {	
		PreparedStatement pst = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from cupons_troca where idPedido is null and nome = ? and idUsuario = ?;");
			
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

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
				connection.close();
				return cupomTroca;
			} else {
				return null;
			}
		}  catch (Exception e) {
			System.out.println("Ocorreu um erro ao recuperar o cupom!");
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return null;
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
				sql3.append("UPDATE carrinhos_produtos set quantidadeItensTrocados = (quantidadeItensTrocados + ?) WHERE carrinhos_produtos.idCarrinhoProduto = ?;");

				pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setInt(1, itemCarrinho.getQuantidadeItensTrocados() );
				pst.setLong(2, itemCarrinho.getId());

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
			pst.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(connection != null) {
					connection.rollback();
					connection.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}			
		}
	}

	public void selectSolicitacoesTroca() {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
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
					rs.getInt("solicitacoes_troca.quantidade"),
					rs.getInt("solicitacoes_troca.status")
				);

				list.add(solicitacaoTroca);
			}

			this.selectVals = list;
			
			pst.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
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
				CarrinhoDAO crdao = new CarrinhoDAO();
				crdao.devolveItensAoItemCarrinho(id);
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
				sql5.append("UPDATE pedidos SET status = ?, dataAlteracao = ? WHERE id = ?;");

				pst = connection.prepareStatement(sql5.toString(),
						Statement.RETURN_GENERATED_KEYS);

				if (aprovacao == 1) { //troca aprovada
					pst.setInt(1, 6);
				} else { //troca recusada
					pst.setInt(1, 4);
				}

				Date agora = new Date();
				pst.setDate(2, new java.sql.Date(agora.getTime()));

				pst.setLong(3, idPedido);

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
			pst.close();
			connection.close();						
		} catch (Exception e) {
			try {
				if(connection != null) {
					connection.rollback();
					connection.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}		
	}

	public EntidadeDominio[] confirmarRecebimentoTroca(long id, boolean retornarEstoque) {
		PreparedStatement pst = null;
		EntidadeDominio[] retorno = new EntidadeDominio[2];
		
		try {
			Connection con = Conexao.getConnectionMySQL();
			con.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE solicitacoes_troca SET status = ? WHERE id = ?;");
			
			pst = con.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			if (retornarEstoque) {
				pst.setInt(1, 3);
			} else {
				pst.setInt(1, 2);
			}
			pst.setLong(2, id);

			pst.executeUpdate();

			if (retornarEstoque) {
				retorno[1] = retornaItensEstoque(id);
			}

			//altera status do pedido
			StringBuilder sql4 = new StringBuilder();
			sql4.append("SELECT pedidos.id from pedidos "+
			           "INNER JOIN carrinhos on carrinhos.id = pedidos.idCarrinho " +
					   "INNER JOIN carrinhos_produtos on carrinhos_produtos.idCarrinho = carrinhos.id " +
					   "INNER JOIN solicitacoes_troca on solicitacoes_troca.idItemCarrinho = carrinhos_produtos.idCarrinhoProduto " +
			           "WHERE solicitacoes_troca.idItemCarrinho = ?;");
		
			pst = con.prepareStatement(sql4.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, id);

			ResultSet rs3 = pst.executeQuery();

			if (rs3.next()) {
				long idPedido = rs3.getLong(1);

				StringBuilder sql5 = new StringBuilder();
				sql5.append("UPDATE pedidos SET status = 7 WHERE id = ?;");

				pst = con.prepareStatement(sql5.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, idPedido);

				pst.executeUpdate();
			}

			retorno[0] = geraCupomTroca(id);

			con.commit();
			pst.close();
			con.close();

			return retorno;
						
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public CupomTroca geraCupomTroca(long id) {
		PreparedStatement pst = null;

		try {
			Connection con = Conexao.getConnectionMySQL();
			con.setAutoCommit(false);
			pst = con.prepareStatement("select * from solicitacoes_troca "+
				"inner join carrinhos_produtos on carrinhos_produtos.idCarrinhoProduto = solicitacoes_troca.idItemCarrinho "+
				"inner join livros on livros.id = carrinhos_produtos.idProduto "+
				"inner join carrinhos on carrinhos.id = carrinhos_produtos.idCarrinho "+
				"inner join pedidos on pedidos.idCarrinho = carrinhos.id "+
				"inner join clientes on clientes.id = carrinhos.idUsuario "+
				"where solicitacoes_troca.id = ?;");
			
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
						
			if (rs.next()) {			
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO cupons_troca (dataEntrada, idUsuario, nome, valor, status) VALUES (?, ?, ?, ?, 1);");

				pst = con.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				
				DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  

				String nome = rs.getString("clientes.nome").replaceAll(" ", "").toUpperCase() + dateFormat.format(new Date()) + String.valueOf(rs.getInt("solicitacoes_troca.quantidade") * rs.getDouble("carrinhos_produtos.precoMomentoCompra")).replaceAll(".", "");
				double valorCT = rs.getInt("solicitacoes_troca.quantidade") * rs.getDouble("carrinhos_produtos.precoMomentoCompra");
				Pedido pedido = new Pedido(rs.getLong("pedidos.id"), null, null, 0, null, 0, null, null, null, null, 0, 0, "");
				
				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getLong("clientes.id"));
				pst.setString(3, nome );
				pst.setDouble(4, valorCT );

				pst.executeUpdate();

				ResultSet rs2 = pst.getGeneratedKeys();
				
				CupomTroca ct = null;

				if(rs2.next()) {
					ct = new CupomTroca(rs2.getLong(1), new Date(), nome, valorCT, pedido);
				}

				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE pedidos SET status = 7 WHERE id = ?;");

				pst = con.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);				
				pst.setLong(1, rs.getLong("pedidos.id"));

				pst.executeUpdate();
				
				con.commit();

				pst.close();
				con.close();

				return ct;
			}

			pst.close();
			con.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	public void geraCupomTrocaExcesso(long idCliente, double valor) {
		PreparedStatement pst = null;

		try {
			Connection con = Conexao.getConnectionMySQL();
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cupons_troca (dataEntrada, idUsuario, nome, valor, status) VALUES (?, ?, ?, ?, ?);");

			pst = con.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  
			
			pst.setDate(1, new java.sql.Date(new Date().getTime()));
			pst.setLong(2, idCliente);
			pst.setString(3, "CUPOMTROCA" + idCliente + dateFormat.format(new Date()) + String.valueOf(valor).replaceAll(".", "") );
			pst.setDouble(4, valor );
			pst.setInt(5, 1);

			pst.executeUpdate();
			con.commit();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public LivroEstoque retornaItensEstoque(long idSolicitacaoTroca) {
		PreparedStatement pst = null;

		try {
			Connection con = Conexao.getConnectionMySQL();
			con.setAutoCommit(false);
			pst = con.prepareStatement("select * from solicitacoes_troca "+
				"inner join carrinhos_produtos on carrinhos_produtos.idCarrinhoProduto = solicitacoes_troca.idItemCarrinho "+
				"inner join livros on livros.id = carrinhos_produtos.idProduto "+
				"inner join carrinhos on carrinhos.id = carrinhos_produtos.idCarrinho "+
				"inner join clientes on clientes.id = carrinhos.idUsuario "+
				"where solicitacoes_troca.id = ?;");
			
			pst.setLong(1, idSolicitacaoTroca);
			ResultSet rs = pst.executeQuery();
						
			if (rs.next()) {				
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO livros_estoque (dataCadastro, quantidade, custo, dataEntrada, fornecedorId, livroId, tipoMovimentacao, idCliente) VALUES (?, ?, 0, ?, 0, ?, 3, ?);");

				pst = con.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				
				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getInt("solicitacoes_troca.quantidade"));
				pst.setDate(3, new java.sql.Date(new Date().getTime()));
				pst.setLong(4, rs.getLong("livros.id"));
				pst.setLong(5, rs.getLong("clientes.id"));

				pst.executeUpdate();

				ResultSet rs2 = pst.getGeneratedKeys();

				if (rs2.next()) {
					LivroEstoque le = new LivroEstoque(rs2.getLong(1), new Date(), rs.getInt("solicitacoes_troca.quantidade"), 0, new Date(), null, new Livro(rs.getLong("livros.id"), null), 3);
					le.setCliente(new Cliente(rs.getLong("clientes.id"), null, null));

					con.commit();
					pst.close();
					con.close();
					return le;
				}
			}

			con.commit();
			pst.close();
			con.close();

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

			pst.close();
			con.close();

			return ct;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			CriaFiltragem filtro = new CriaFiltragem();
			String where = filtro.processa(campos);

			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from cupons_troca " + where + " order by id desc;");
		
			ResultSet rs = pst.executeQuery();
			
			ArrayList<CupomTroca> list = new ArrayList();
			
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
			
	public void insert(EntidadeDominio entidade) {

	}	

	public void update(EntidadeDominio entidade) {
		
	}

	public void delete(long id) {
		
	}
}
